package com.shreyaschand.bart.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.volley.RequestQueue;
import com.shreyaschand.bart.AppComponent;
import com.shreyaschand.bart.BartApplication;

import javax.inject.Inject;

import dagger.Lazy;

public abstract class BaseFragment extends Fragment {

    @Inject Lazy<RequestQueue> requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected AppComponent getApplicationComponent() {
        return ((BartApplication) getActivity().getApplication()).getAppComponent();
    }

}
