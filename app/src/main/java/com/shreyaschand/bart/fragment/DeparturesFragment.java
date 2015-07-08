package com.shreyaschand.bart.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shreyaschand.bart.R;
import com.shreyaschand.bart.adapter.FavoritesListAdapter;
import com.shreyaschand.bart.model.StationDestinationEstimate;
import com.shreyaschand.bart.model.StationEstimate;
import com.shreyaschand.bart.network.EstimateRequest;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

public class DeparturesFragment extends BaseFragment {

    @InjectView(R.id.list_view)
    RecyclerView listView;
    RecyclerView.Adapter adapter;
    EstimateRequest request;

    public static DeparturesFragment newInstance() {
        Bundle args = new Bundle();

        DeparturesFragment fragment = new DeparturesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_departures, container, false);
        ButterKnife.inject(this, root);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);

        if (adapter == null) {
            request =
                    EstimateRequest.createRequest(new EstimateRequest.EstimateResponseListener() {
                        @Override
                        public void onError(Exception exception) {
                            Toast.makeText(getActivity(), "Something went wrong. :(", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(List<StationEstimate> estimates) {
                            adapter = new FavoritesListAdapter(StationDestinationEstimate.getEstimateList(estimates));
                            listView.swapAdapter(adapter, false);
                        }
                    });
            requestQueue.get().add(request);
        }
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (request != null) {
            request.cancel();
        }
    }
}
