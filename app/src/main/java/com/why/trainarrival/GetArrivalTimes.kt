package com.why.trainarrival

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements

public class GetArrivalTimes(private val stn:String, private val viewState:String){
    public var trains=ArrayList<Pair<Int,String>>()
    private var times= Elements()
    private var dests=Elements()
    private val client = OkHttpClient()
    private val formBody:FormBody = FormBody.Builder()
        .add("ScriptManager1", "UP1|ddlStation")
        .add("__LASTFOCUS","")
        .add("__EVENTTARGET","ddlStation")
        .add("__EVENTARGUMENT","")
        .add("__VIEWSTATE",this.viewState)
        .add("__VIEWSTATEGENERATOR","CA0B0334")
        .add("stnCode","")
        .add("stnName","")
        .add("ddlStation",this.stn)
        .build()

    private val request = Request.Builder()
            .url("http://trainarrivalweb.smrt.com.sg/default.aspx")
            .header("X-MicrosoftAjax", "Delta=true")
        .addHeader("Host","""trainarrivalweb.smrt.com.sg""")
        .addHeader("Accept-Language","""en-US,en;q=0.5""")
        .addHeader("Accept","""*/*""")
            //.addHeader("Accept-Encoding","gzip, deflate")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Content-Type", """application/x-www-form-urlencoded""")
            .addHeader("Origin","""http://trainarrivalweb.smrt.com.sg""")
            .addHeader("Connection","""keep-alive""")
            .addHeader("Referer","""http://trainarrivalweb.smrt.com.sg/default.aspx""")

        .addHeader("cookie","""ASP.NET_SessionId=umv0ktalceechc3ejkykytvd""")
        .addHeader("User-Agent","""Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0""")
        .post(formBody)
        .build()

    fun getTimes(){
        println(request)
        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("failure")
                } else {
                    val s = response.body!!.string()
                    val doc = Jsoup.parse(s)
                    times = doc.getElementsContainingOwnText("""min(s)""")
                    dests =
                        doc.getElementsMatchingOwnText("^((Pasir Ris)|(Joo Koon)|(Tuas Link)|(Do Not Board))$")
                    if (this.times.size == this.dests.size) {
                        println("size matches")
                        trains = ArrayList()
                        for (i in 0 until this.times.size) {
                            trains.add(
                                Pair(
                                    this.times[i].text().replace("[^\\d.]".toRegex(), "").toInt(),
                                    this.dests[i].text()
                                )
                            )
                        }


                        println(trains)

                    } else {
                        println("size does not match")
                    }
                }
            }
        }catch(e:Exception){
            println("failure")
        }
    }

}