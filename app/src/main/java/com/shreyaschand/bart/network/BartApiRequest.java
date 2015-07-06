package com.shreyaschand.bart.network;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.shreyaschand.bart.BuildConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class BartApiRequest extends StringRequest {
    protected static final Uri baseUrl = new Uri.Builder()
                                                .scheme("http")
                                                .authority("api.bart.gov")
                                                .appendPath("api")
                                                .appendQueryParameter("key","Z2VS-JG57-IXMQ-DT35")
                                                .build();

    public interface BartResponseListener extends Response.ErrorListener, Response.Listener<String> {
    }

    public BartApiRequest(String url, BartResponseListener response) {
        super(Method.GET, url, response, response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME + "/" + "shreyas.chand@gmail.com");
        return headers;
    }

}
