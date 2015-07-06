package com.shreyaschand.bart.model;

import java.util.ArrayList;
import java.util.List;

public class StationDestinationEstimate {
    private Station origin;
    private Station destination;

    private List<Estimate> estimates;

    public StationDestinationEstimate(Station origin, Station destination, List<Estimate> estimates) {
        this.origin = origin;
        this.destination = destination;
        this.estimates = estimates;
    }

    public static List<StationDestinationEstimate> getEstimateList(List<StationEstimate> rawEstimates) {
        ArrayList<StationDestinationEstimate> estimates = new ArrayList<>();
        for (StationEstimate stationEstimate : rawEstimates) {
            Station orig = stationEstimate.station;
            for (DestinationEstimate destinationEstimate : stationEstimate.destinationEstimates) {
                estimates.add(new StationDestinationEstimate(orig,
                                                             destinationEstimate.destination,
                                                             destinationEstimate.estimates));
            }
        }
        return estimates;
    }

    public Station getOrigin() {
        return origin;
    }

    public Station getDestination() {
        return destination;
    }

    public List<Estimate> getEstimates() {
        return estimates;
    }

}
