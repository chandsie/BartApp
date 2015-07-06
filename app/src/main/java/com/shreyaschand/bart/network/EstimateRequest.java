package com.shreyaschand.bart.network;

import android.net.Uri;
import android.util.Log;

import com.android.volley.VolleyError;
import com.shreyaschand.bart.model.RealTimeEstimate;
import com.shreyaschand.bart.model.Station;
import com.shreyaschand.bart.model.StationEstimate;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.util.List;

public class EstimateRequest extends BartApiRequest {
    private static final Uri baseUrl = BartApiRequest.baseUrl.buildUpon()
                                          .appendPath("etd.aspx")
                                          .appendQueryParameter("cmd", "etd")
                                          .build();

    public interface EstimateResponseListener {

        void onError(Exception exception);

        void onSuccess(List<StationEstimate> estimate);
    }

    private static class EstimateResponseListenerWrapper implements BartResponseListener {

        EstimateResponseListener listener;

        public EstimateResponseListenerWrapper(EstimateResponseListener listener) {
            this.listener = listener;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            listener.onError(error);
        }

        @Override
        public void onResponse(String response) {
            try {
                RealTimeEstimate rte = new Persister(new AnnotationStrategy()).read(RealTimeEstimate.class, response, false);
                listener.onSuccess(rte.stationEstimates);
            } catch (Exception ex) {
                listener.onError(ex);
                Log.e("XMLSHIT", ex.toString(), ex);
            }
        }
    }

    private EstimateRequest(String url, BartResponseListener response) {
        super(url, response);
    }

    public static EstimateRequest createRequest(Station station, final EstimateResponseListener response) {
        String url = baseUrl.buildUpon().appendQueryParameter("orig", station.abbrev).build().toString();
        return new EstimateRequest(url, new EstimateResponseListenerWrapper(response));
    }

    public static EstimateRequest createRequest(final EstimateResponseListener response) {
        String url = baseUrl.buildUpon().appendQueryParameter("orig", "ALL").build().toString();
        return new EstimateRequest(url, new EstimateResponseListenerWrapper(response));
    }
}
