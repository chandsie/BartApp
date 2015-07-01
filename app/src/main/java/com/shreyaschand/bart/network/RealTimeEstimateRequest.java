package com.shreyaschand.bart.network;

import android.net.Uri;

import com.shreyaschand.bart.model.Station;

public class RealTimeEstimateRequest extends BartApiRequest {
    private static final Uri.Builder urlBuilder = baseUrl.buildUpon()
            .appendPath("etc.aspx")
            .appendQueryParameter("cmd", "etd");

    private RealTimeEstimateRequest(String url, BartResponse response) {
        super(url, response);
    }

    public static BartApiRequest createRequest(Station station, BartResponse response) {
        urlBuilder.appendQueryParameter("orig", station.abbrev);
        return new RealTimeEstimateRequest(urlBuilder.build().toString(), response);
    }
}
