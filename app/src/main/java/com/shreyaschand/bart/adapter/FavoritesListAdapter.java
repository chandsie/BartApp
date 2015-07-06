package com.shreyaschand.bart.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shreyaschand.bart.R;
import com.shreyaschand.bart.model.Estimate;
import com.shreyaschand.bart.model.StationDestinationEstimate;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.FavoriteCardViewHolder> {
    List<StationDestinationEstimate> estimatesList;

    public FavoritesListAdapter(List<StationDestinationEstimate> estimates) {
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
        StationDestinationEstimate estimates = estimatesList.get(position);

        holder.colorStripe.setBackgroundColor(Color.DKGRAY);
        holder.title.setText(estimates.getOrigin().full);
        holder.subtitle.setText(estimates.getDestination().full);

        List<Estimate> trainEstimates = estimates.getEstimates();
        StringBuilder sb = new StringBuilder(trainEstimates.size() * 42);
        for (int i = 0; i < trainEstimates.size(); i++) {
            Estimate est = trainEstimates.get(i);
            sb.append(est.minutes);
            sb.append(" minute(s). Platform ");
            sb.append(est.platform);
            sb.append(". Bikes ");
            sb.append(est.bikesAllowed == 1 ? "" : "Not ");
            sb.append("Allowed. ");
            sb.append(est.length);
            sb.append(" cars.<br />");
        }
        sb.setLength(sb.length() - 6); // remove the final line break
        holder.estimates.setText(Html.fromHtml(sb.toString()));
    }

    @Override
    public int getItemCount() {
        return estimatesList.size();
    }

    public static class FavoriteCardViewHolder extends RecyclerView.ViewHolder {

        View root;
        @InjectView(R.id.color_stripe)
        View colorStripe;
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.subtitle)
        TextView subtitle;
        @InjectView(R.id.estimates)
        TextView estimates;

        public FavoriteCardViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
            root = v;
        }
    }
}
