package com.why.trainarrival

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements

public class GetArrivalTimes(public var stn:String, private val viewState:String){
    public var trains=ArrayList<Pair<Int,String>>()
    private var times= Elements()
    private var dests=Elements()
    private val client = OkHttpClient()
    private var formBody:FormBody = FormBody.Builder()
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

    fun refreshFormBody(stn:String,x:MainActivity){
        this.formBody = FormBody.Builder()
        .add("ScriptManager1", "UP1|ddlStation")
            .add("__LASTFOCUS","")
            .add("__EVENTTARGET","ddlStation")
            .add("__EVENTARGUMENT","")
            .add("__VIEWSTATE",this.viewState)
            .add("__VIEWSTATEGENERATOR","CA0B0334")
            .add("stnCode","")
            .add("stnName","")
            .add("ddlStation",stn)
            .build()

        this.request = Request.Builder()
            .url("http://trainarrivalweb.smrt.com.sg/default.aspx")
            .addHeader("Host","""trainarrivalweb.smrt.com.sg""")
            .addHeader("Accept-Language","""en-US,en;q=0.5""")
            .addHeader("Accept","""*/*""")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Content-Type", """application/x-www-form-urlencoded""")
            .addHeader("Content-Length","7831")
            .addHeader("Origin","""http://trainarrivalweb.smrt.com.sg""")
            .addHeader("Connection","""keep-alive""")
            .addHeader("Referer","""http://trainarrivalweb.smrt.com.sg/default.aspx""")
            .addHeader("cookie","""ASP.NET_SessionId=umv0ktalceechc3ejkykytvd""")
            .post(formBody)
            .build()

        this.stn=stn
        println(stn)
        this.trains=ArrayList()
        
        getTimes()
        x.updateText()

    }

    private var request = Request.Builder()
        .url("http://trainarrivalweb.smrt.com.sg/default.aspx")
        .addHeader("Host","""trainarrivalweb.smrt.com.sg""")
        .addHeader("Accept-Language","""en-US,en;q=0.5""")
        .addHeader("Accept","""*/*""")
        .addHeader("Cache-Control", "no-cache")
        .addHeader("Content-Type", """application/x-www-form-urlencoded""")
        .addHeader("Content-Length","7831")
        .addHeader("Origin","""http://trainarrivalweb.smrt.com.sg""")
        .addHeader("Connection","""keep-alive""")
        .addHeader("Referer","""http://trainarrivalweb.smrt.com.sg/default.aspx""")
        .addHeader("cookie","""ASP.NET_SessionId=umv0ktalceechc3ejkykytvd""")
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
                        doc.getElementsMatchingOwnText("^((Woodlands North)|(Woodlands South)|(Jurong East)|(Marina South Pier)|(Marina Bay)|(HarbourFront)|(Dhoby Ghaut)|(Boon Lay)|(Tanah Merah)|(Changi Airport)|(Pasir Ris)|(Joo Koon)|(Tuas Link)|(Do Not Board))$")
                    if(this.times.size==0){
                        println("what???")
                        println(s)
                    }
                    else if (this.times.size == this.dests.size) {
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
            println(e)
        }
    }

}