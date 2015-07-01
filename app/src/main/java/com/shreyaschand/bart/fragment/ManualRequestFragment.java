package com.shreyaschand.bart.fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.shreyaschand.bart.R;
import com.shreyaschand.bart.network.BartApiRequest;
import com.shreyaschand.bart.network.ManualRequest;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ManualRequestFragment extends BaseFragment {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.description) TextView description;
    @InjectView(R.id.endpoint) EditText endpoint;
    @InjectView(R.id.params) EditText params;

    BartApiRequest bartApiRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.manual_request_fragment, container, false);
        ButterKnife.inject(this, root);
        title.setText("Enter a query to execute");
        description.setMovementMethod(new ScrollingMovementMethod());
        description.setTextIsSelectable(true);
        return root;
    }

    @OnClick(R.id.execute)
    public void executeClicked() {
        if (bartApiRequest != null) {
            bartApiRequest.cancel();
        }
        String endpointPath = endpoint.getText().toString().trim();
        String paramList = params.getText().toString().trim();

        bartApiRequest = ManualRequest.createRequest(endpointPath, paramList, new BartApiRequest.BartResponse() {
            @Override
            public void onResponse(String response) {
                description.setText(response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                description.setText(error.getLocalizedMessage());
            }
        });
        title.setText("URL: " + bartApiRequest.getUrl());
        requestQueue.get().add(bartApiRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bartApiRequest != null) {
            bartApiRequest.cancel();
        }
    }
}
