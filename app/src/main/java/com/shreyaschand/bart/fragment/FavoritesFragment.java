package com.shreyaschand.bart.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.shreyaschand.bart.R;
import com.shreyaschand.bart.model.Station;
import com.shreyaschand.bart.network.BartApiRequest;
import com.shreyaschand.bart.network.RealTimeEstimateRequest;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FavoritesFragment extends BaseFragment {

    @InjectView(R.id.list_view) RecyclerView listView;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.inject(this, root);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(null);

        RealTimeEstimateRequest.createRequest(Station.dbrk, new BartApiRequest.BartResponse() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "An error ocurred", Toast.LENGTH_SHORT);
            }

            @Override
            public void onResponse(String response) {

            }
        });

        return root;
    }
}
