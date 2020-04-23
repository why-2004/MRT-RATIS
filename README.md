# MRT-RATIS
A feature clone of the RATIS system seen on the platforms of the Singapore MRT. Proof of concept for MRT arrival time.

People have in fact made MRT arrival time systems from trainarrivalweb.smrt.com.sg, but thus far they all seem to be closed source or rely on a defunct API. Other solutions with hardware tracking have also been done, but that does not offer a simple solution for arrival times.

With this repository, I hope to make a simple and open-source way to fetch the arrival data via POST request.

(Please note that only SMRT provides this data, so DTL and NEL are not supported. I will also find a better way to do this than hardcoding the viewState value eventually, since it seems slightly prone to failure.)

For those who wish to use this early code, use GetArrivalTimes.kt (will probably bother to make an actual library once this is stable)

Dependencies are okhttp and javasoup

(Apologies to SMRT for scraping the webpage, please just release a public API so we can do this in an easier way)
