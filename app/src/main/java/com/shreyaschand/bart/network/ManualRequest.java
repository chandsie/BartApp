package com.shreyaschand.bart.network;

import android.net.Uri;

public class ManualRequest extends BartApiRequest {

    private  ManualRequest(String url, BartResponse response) {
        super(url, response);
    }

    public static BartApiRequest createRequest(String path, String paramList, BartResponse response) {
        Uri.Builder urlBuilder = baseUrl.buildUpon().appendPath(String.format("%s.aspx", path));
        if (paramList != null && !paramList.isEmpty()) {
            String[] kvPairs = paramList.split(",");
            for (String pair : kvPairs) {
                String[] kv = pair.split("=");
                urlBuilder = urlBuilder.appendQueryParameter(kv[0], kv[1]);
            }
        }
        return new ManualRequest(urlBuilder.build().toString(), response);
    }
}
