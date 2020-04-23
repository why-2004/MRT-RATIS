package com.why.trainarrival

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var timeGetter:GetArrivalTimes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val decorView = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }

    }

    override fun onStart() {
        super.onStart()
        timeGetter= GetArrivalTimes("TAM","""/wEPDwUKMTgzMTU4ODI4Mg9kFgICAw9kFgICAw9kFgJmD2QWBAIBDxYCHgdWaXNpYmxlaBYEZg8PFgIeBFRleHQFAzY3MWRkAgEPD2QWBB4Lb25tb3VzZW92ZXIFJHJldHVybiBjaGFuZ2VCdG5JbWFnZSh0aGlzLCAnb3ZlcicpOx4Kb25tb3VzZW91dAUjcmV0dXJuIGNoYW5nZUJ0bkltYWdlKHRoaXMsICdvdXQnKTtkAgMPFgIfAGcWBAIBDxBkEBVXBlNlbGVjdBBBZG1pcmFsdHkgKE5TMTApDkFsanVuaWVkIChFVzkpEUFuZyBNbyBLaW8gKE5TMTYpDkJhcnRsZXkgKENDMTIpD0JheSBGcm9udCAoQ0UyKQtCZWRvayAoRVc1KRJCaXNoYW4gKENDMTUvTlMxNykPQm9vbiBMYXkgKEVXMjcpFkJvdGFuaWMgR2FyZGVucyAoQ0MxOSkPQnJhZGRlbGwgKE5TMTgpEEJyYXMgQmFzYWggKENDMikMQnVnaXMgKEVXMTIpEUJ1a2l0IEJhdG9rIChOUzIpEkJ1a2l0IEdvbWJhayAoTlMzKRdCdW9uYSBWaXN0YSAoQ0MyMi9FVzIxKRBDYWxkZWNvdHQgKENDMTcpD0NhbmJlcnJhIChOUzEyKRRDaGFuZ2kgQWlycG9ydCAoQ0cyKRVDaGluZXNlIEdhcmRlbiAoRVcyNSkTQ2hvYSBDaHUgS2FuZyAoTlM0KRVDaXR5IEhhbGwgKEVXMTMvTlMyNSkPQ2xlbWVudGkgKEVXMjMpE0NvbW1vbndlYWx0aCAoRVcyMCkMRGFrb3RhIChDQzgpFkRob2J5IEdoYXV0IChDQzEvTlMyNCkMRG92ZXIgKEVXMjIpD0VzcGxhbmFkZSAoQ0MzKQtFdW5vcyAoRVc3KQpFeHBvIChDRzEpEkZhcnJlciBSb2FkIChDQzIwKRFHVUwgQ2lyY2xlIChFVzMwKRNIYXJib3VyRnJvbnQgKENDMjkpFEhhdyBQYXIgVmlsbGEgKENDMjUpFkhvbGxhbmQgVmlsbGFnZSAoQ0MyMSkPSm9vIEtvb24gKEVXMjkpFkp1cm9uZyBFYXN0IChFVzI0L05TMSkOS2FsbGFuZyAoRVcxMCkPS2VtYmFuZ2FuIChFVzYpEUtlbnQgUmlkZ2UgKENDMjQpDUtoYXRpYiAoTlMxNCkMS3JhbmppIChOUzYpFExhYnJhZG9yIFBhcmsgKENDMjcpD0xha2VzaWRlIChFVzI2KQ9MYXZlbmRhciAoRVcxMSkTTG9yb25nIENodWFuIChDQzE0KRFNYWNQaGVyc29uIChDQzEwKRVNYXJpbmEgQmF5IChOUzI3L0NFMSkYTWFyaW5hIFNvdXRoIFBpZXIgKE5TMjgpD01hcnNpbGluZyAoTlM3KRBNYXJ5bW91bnQgKENDMTYpEU1vdW50YmF0dGVuIChDQzcpDU5ld3RvbiAoTlMyMSkUTmljb2xsIEhpZ2h3YXkgKENDNSkNTm92ZW5hIChOUzIwKRBPbmUtTm9ydGggKENDMjMpDk9yY2hhcmQgKE5TMjIpEk91dHJhbSBQYXJrIChFVzE2KRRQYXNpciBQYW5qYW5nIChDQzI2KQ9QYXNpciBSaXMgKEVXMSkUUGF5YSBMZWJhciAoQ0M5L0VXOCkOUGlvbmVlciAoRVcyOCkPUHJvbWVuYWRlIChDQzQpEVF1ZWVuc3Rvd24gKEVXMTkpGVJhZmZsZXMgUGxhY2UgKEVXMTQvTlMyNikOUmVkaGlsbCAoRVcxOCkQU2VtYmF3YW5nIChOUzExKRBTZXJhbmdvb24gKENDMTMpC1NpbWVpIChFVzMpD1NvbWVyc2V0IChOUzIzKQ1TdGFkaXVtIChDQzYpD1RhaSBTZW5nIChDQzExKQ5UYW1waW5lcyAoRVcyKRFUYW5haCBNZXJhaCAoRVc0KRRUYW5qb25nIFBhZ2FyIChFVzE1KRRUZWxvayBCbGFuZ2FoIChDQzI4KRJUaW9uZyBCYWhydSAoRVcxNykQVG9hIFBheW9oIChOUzE5KRRUdWFzIENyZXNjZW50IChFVzMxKRBUdWFzIExpbmsgKEVXMzMpFVR1YXMgV2VzdCBSb2FkIChFVzMyKRRXb29kbGFuZHMgKFRFTDIvTlM4KRZXb29kbGFuZHMgTm9ydGggKFRFTDEpFldvb2RsYW5kcyBTb3V0aCAoVEVMMykNWWV3IFRlZSAoTlM1KRNZaW8gQ2h1IEthbmcgKE5TMTUpDVlpc2h1biAoTlMxMykVVwZTZWxlY3QDQURNA0FMSgNBTUsEQ0JMWQRDQkZUA0JESwRDQlNIA0JOTARDQlROA0JETARDQkJTA0JHUwNCQlQDQkdCBENCTlYEQ0NEVANDQlIDQ0dBA0NORwNDQ0sDQ1RIA0NMRQNDT00EQ0RLVARDREJHA0RWUgRDRVBOA0VVTgNYUE8EQ0ZSUgNHQ0wEQ0hCRgRDSFBWBENITFYDSktOA0pVUgNLQUwDS0VNBENLUkcDS1RCA0tSSgRDTEJEA0xLUwNMVlIEQ0xSQwRDTVBTBENNUkIDTVNQA01TTARDTVJNBENNQlQDTkVXBENOQ0gDTk9WBENPTkgDT1JDA09UUARDUFBKA1BTUgRDUFlMA1BOUgRDUE1OA1FVRQNSRlADUkRIA1NCVwRDU0VSA1NJTQNTT00EQ1NETQRDVFNHA1RBTQNUTk0DVFBHBENUTEIDVElCA1RBUANUQ1IDVExLA1RXUgRUV0RMBFRXRE4EVFdEUwNZV1QDWUNLA1lJUxQrA1dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2cWAQJHZAIDDw8WAh8AZ2QWBAIBDw8WBB8BBYEaPGRpdiBjbGFzcz0nZHJkcmFsbCBtYXJnaW50b3AxMCBtYXJnaW5ib3R0MTAgcEIxMCc+PHRhYmxlIGJvcmRlcj0nMHB4JyBjZWxscGFkZGluZz0nMCcgY2VsbHNwYWNpbmc9JzAnIHN0eWxlPSdib3JkZXI6IG5vbmU7Jz48dHI+PHRkPjxwIGNsYXNzPSdib2xkVHh0IGRhcmtncmV5IG1hcmdpbnRvcDEwIG1hcmdpbmJvdHQxMCc+PGI+VGFpIFNlbmcgKENDMTEpIE1SVCBTdGF0aW9uPC9iPjwvcD48L3RkPjwvdHI+PHRyIGNsYXNzPSdub2JyZHInPjx0ZCBhbGlnbj0nY2VudGVyJyBjbGFzcz0nbm9icmRyJz48dGFibGUgYm9yZGVyPScwcHgnIGNlbGxwYWRkaW5nPScwJyBjZWxsc3BhY2luZz0nMCcgY2xhc3M9J25vYnJkcicgc3R5bGU9J3RleHQtYWxpZ246Y2VudGVyO2JhY2tncm91bmQtY29sb3I6V2hpdGU7Jz48dHIgYWxpZ249J2NlbnRlcicgc3R5bGU9J3RleHQtYWxpZ246Y2VudGVyOycgY2xhc3M9J25vYnJkcic+PHRkIGFsaWduPSdjZW50ZXInIGNsYXNzPSdub2JyZHInIHN0eWxlPSd0ZXh0LWFsaWduOmNlbnRlcjtiYWNrZ3JvdW5kLWNvbG9yOldoaXRlOyc+PHAgc3R5bGU9J3RleHQtYWxpZ246Y2VudGVyO2JhY2tncm91bmQtY29sb3I6V2hpdGU7JyBhbGlnbj0nY2VudGVyJyBjbGFzcz0nbm9icmRyJz48ZGl2IHN0eWxlPSJ3aWR0aDoxMDAlOyI+DQoJPHNwYW4gc3R5bGU9ImRpc3BsYXk6aW5saW5lLWJsb2NrO2NvbG9yOkRhcmtPcmFuZ2U7Zm9udC1zaXplOkxhcmdlO2ZvbnQtd2VpZ2h0OmJvbGQ7d2lkdGg6MzkycHg7Ij5DaXJjbGUgTGluZSAoQ0NMKTxiciAvPjxiciAvPjwvc3Bhbj48c3BhbiBzdHlsZT0iZGlzcGxheTppbmxpbmUtYmxvY2s7Y29sb3I6QmxhY2s7Zm9udC13ZWlnaHQ6Ym9sZDt3aWR0aDozOTJweDsiPjxwIGNsYXNzPSdhbGlnbi1sZWZ0Jz4mbmJzcDsgQ0NMIGluIHRoZSBkaXJlY3Rpb24gb2YgSGFyYm91ckZyb250PGJyIC8+PGJyIC8+PC9wPjwvc3Bhbj48ZGl2Pg0KCQk8dGFibGUgY2VsbHNwYWNpbmc9IjAiIGNlbGxwYWRkaW5nPSI0IiBhbGlnbj0iQ2VudGVyIiBydWxlcz0iY29scyIgYm9yZGVyPSIxIiBpZD0iZ3ZUaW1lIiBzdHlsZT0iY29sb3I6QmxhY2s7YmFja2dyb3VuZC1jb2xvcjpXaGl0ZTtib3JkZXItY29sb3I6I0NDQ0NDQztib3JkZXItd2lkdGg6MXB4O2JvcmRlci1zdHlsZTpTb2xpZDtmb250LXNpemU6MTBwdDt3aWR0aDozNTBweDtib3JkZXItY29sbGFwc2U6Y29sbGFwc2U7Ij4NCgkJCTx0ciBzdHlsZT0iY29sb3I6V2hpdGU7YmFja2dyb3VuZC1jb2xvcjojMzMzMzMzO2hlaWdodDozMnB4OyI+DQoJCQkJPHRoIHNjb3BlPSJjb2wiPk5leHQgTVJUPC90aD48dGggc2NvcGU9ImNvbCI+U3Vic2VxdWVudCBNUlQ8L3RoPg0KCQkJPC90cj48dHIgYWxpZ249ImNlbnRlciIgc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQ7aGVpZ2h0OjI4cHg7Ij4NCgkJCQk8dGQgc3R5bGU9IndpZHRoOjE3NXB4OyI+NCBtaW4ocykgKjwvdGQ+PHRkIHN0eWxlPSJ3aWR0aDoxNzVweDsiPjkgbWluKHMpICo8L3RkPg0KCQkJPC90cj48dHIgYWxpZ249ImNlbnRlciIgc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQ7aGVpZ2h0OjI4cHg7Ij4NCgkJCQk8dGQ+SGFyYm91ckZyb250PC90ZD48dGQ+SGFyYm91ckZyb250PC90ZD4NCgkJCTwvdHI+DQoJCTwvdGFibGU+DQoJPC9kaXY+PHNwYW4+PGJyIC8+PC9zcGFuPjxzcGFuIHN0eWxlPSJkaXNwbGF5OmlubGluZS1ibG9jazt3aWR0aDozOTJweDsiPjxwIGNsYXNzPSdhbGlnbi1sZWZ0Jz48YSBocmVmPSdodHRwOi8vam91cm5leS5zbXJ0LmNvbS5zZy9qb3VybmV5L3N0YXRpb25faW5mby90YWkgc2VuZy9tYXAvJyB0YXJnZXQ9J19ibGFuayc+PGI+Q2xpY2sgaGVyZTwvYj48L2E+IGZvciBmaXJzdCB0cmFpbi9sYXN0IHRyYWluIHRpbWluZ3MuPC9wPjwvc3Bhbj48c3BhbiBzdHlsZT0iZGlzcGxheTppbmxpbmUtYmxvY2s7Y29sb3I6TmF2eTtmb250LXdlaWdodDpib2xkO2ZvbnQtc3R5bGU6aXRhbGljO3dpZHRoOjM5MnB4OyI+PGJyIC8+PGJyIC8+PC9zcGFuPjxzcGFuIHN0eWxlPSJkaXNwbGF5OmlubGluZS1ibG9jaztjb2xvcjpCbGFjaztmb250LXdlaWdodDpib2xkO3dpZHRoOjM5MnB4OyI+PHAgY2xhc3M9J2FsaWduLWxlZnQnPiZuYnNwOyBDQ0wgaW4gdGhlIGRpcmVjdGlvbiBvZiBEaG9ieSBHaGF1dDxiciAvPjxiciAvPjwvcD48L3NwYW4+PGRpdj4NCgkJPHRhYmxlIGNlbGxzcGFjaW5nPSIwIiBjZWxscGFkZGluZz0iNCIgYWxpZ249IkNlbnRlciIgcnVsZXM9ImNvbHMiIGJvcmRlcj0iMSIgaWQ9Imd2VGltZSIgc3R5bGU9ImNvbG9yOkJsYWNrO2JhY2tncm91bmQtY29sb3I6V2hpdGU7Ym9yZGVyLWNvbG9yOiNDQ0NDQ0M7Ym9yZGVyLXdpZHRoOjFweDtib3JkZXItc3R5bGU6U29saWQ7Zm9udC1zaXplOjEwcHQ7d2lkdGg6MzUwcHg7Ym9yZGVyLWNvbGxhcHNlOmNvbGxhcHNlOyI+DQoJCQk8dHIgc3R5bGU9ImNvbG9yOldoaXRlO2JhY2tncm91bmQtY29sb3I6IzMzMzMzMztoZWlnaHQ6MzJweDsiPg0KCQkJCTx0aCBzY29wZT0iY29sIj5OZXh0IE1SVDwvdGg+PHRoIHNjb3BlPSJjb2wiPlN1YnNlcXVlbnQgTVJUPC90aD4NCgkJCTwvdHI+PHRyIGFsaWduPSJjZW50ZXIiIHN0eWxlPSJmb250LXdlaWdodDpib2xkO2hlaWdodDoyOHB4OyI+DQoJCQkJPHRkIHN0eWxlPSJ3aWR0aDoxNzVweDsiPjQgbWluKHMpICo8L3RkPjx0ZCBzdHlsZT0id2lkdGg6MTc1cHg7Ij45IG1pbihzKSAqPC90ZD4NCgkJCTwvdHI+PHRyIGFsaWduPSJjZW50ZXIiIHN0eWxlPSJmb250LXdlaWdodDpib2xkO2hlaWdodDoyOHB4OyI+DQoJCQkJPHRkPkRob2J5IEdoYXV0PC90ZD48dGQ+RGhvYnkgR2hhdXQ8L3RkPg0KCQkJPC90cj4NCgkJPC90YWJsZT4NCgk8L2Rpdj48c3Bhbj48YnIgLz48L3NwYW4+PHNwYW4gc3R5bGU9ImRpc3BsYXk6aW5saW5lLWJsb2NrO3dpZHRoOjM5MnB4OyI+PHAgY2xhc3M9J2FsaWduLWxlZnQnPjxhIGhyZWY9J2h0dHA6Ly9qb3VybmV5LnNtcnQuY29tLnNnL2pvdXJuZXkvc3RhdGlvbl9pbmZvL3RhaSBzZW5nL21hcC8nIHRhcmdldD0nX2JsYW5rJz48Yj5DbGljayBoZXJlPC9iPjwvYT4gZm9yIGZpcnN0IHRyYWluL2xhc3QgdHJhaW4gdGltaW5ncy48L3A+PC9zcGFuPjxzcGFuIHN0eWxlPSJkaXNwbGF5OmlubGluZS1ibG9jaztjb2xvcjpOYXZ5O2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zdHlsZTppdGFsaWM7d2lkdGg6MzkycHg7Ij48YnIgLz48cCBjbGFzcz0nYWxpZ24tbGVmdCc+Jm5ic3A7KiBTY2hlZHVsZWQgQXJyaXZhbCBUaW1lPC9wPjxiciAvPjxiciAvPjwvc3Bhbj4NCjwvZGl2PjwvcD48L3RkPjwvdHI+PC90YWJsZT48L3RkPjwvdHI+PC90YWJsZT48L2Rpdj4fAGdkZAIDDw8WAh8AZxYEHwIFJHJldHVybiBjaGFuZ2VCdG5JbWFnZSh0aGlzLCAnb3ZlcicpOx8DBSNyZXR1cm4gY2hhbmdlQnRuSW1hZ2UodGhpcywgJ291dCcpO2QYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFC2lidG5SZWZyZXNo4nbq+QbLL7H2CAIgGxoC1zM2zRo=""")
        Thread {
                while (true) {
                    timeGetter.getTimes()
                    updateText()
                    Thread.sleep(5000)
                }
            }.start()
    }


    var LastTrainTime=9999
    fun updateText(){
        val t = findViewById<TextView>(R.id.TrainArrival)
        if (timeGetter.trains[1].first>LastTrainTime){
            t.post {
                t.text = "departing"
            }
        }else{
            t.post {
                t.text = "wait"
            }
        }
        LastTrainTime=timeGetter.trains[1].first
    }
}
