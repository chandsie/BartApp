package com.shreyaschand.bart.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shreyaschand.bart.R;
import com.shreyaschand.bart.model.StationRouteEstimate;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.FavoriteCardViewHolder> {
    StationRouteEstimate[] estimatesList;

    public FavoritesListAdapter(StationRouteEstimate[] estimates) {
        estimatesList = estimates;
    }

    @Override
    public FavoriteCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_favorite_card, parent, false);
        return new FavoriteCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavoriteCardViewHolder holder, int position) {
        StationRouteEstimate estimate = estimatesList[position];
        holder.colorStripe.setBackgroundColor(estimate.color);
        holder.title.setText(estimate.station);
        holder.subtitle.setText(estimate.destination + "(" + estimate.platform + ")");
        StationRouteEstimate.TrainEstimate[] trainEstimates = estimate.trainEstimates;
        StringBuilder sb = new StringBuilder(trainEstimates.length * 42);
        for (int i = 0; i < trainEstimates.length; i++) {
            int minutes = trainEstimates[i].minutes;
            sb.append(minutes == -1 ? "Leaving" : minutes);
            sb.append("minute(s). Bikes ");
            sb.append(trainEstimates[i].bikesAllowed ? "" : "Not ");
            sb.append("Allowed. Cars: ");
            sb.append(trainEstimates[i].length);
            sb.append("<br />");
        }
        sb.setLength(sb.length() - 6); // remove the final line break
        holder.estimates.setText(Html.fromHtml(sb.toString()));
    }

    @Override
    public int getItemCount() {
        return estimatesList.length;
    }

    public static class FavoriteCardViewHolder extends RecyclerView.ViewHolder {

        View root;
        @InjectView(R.id.color_stripe) View colorStripe;
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.subtitle) TextView subtitle;
        @InjectView(R.id.estimates) TextView estimates;

        public FavoriteCardViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
            root = v;
        }
    }
}
