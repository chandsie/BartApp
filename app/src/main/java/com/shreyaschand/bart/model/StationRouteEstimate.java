package com.shreyaschand.bart.model;

public class StationRouteEstimate {

    public String station;
    public String destination;

    // TODO: can these two values be different for different trains within a set of estimates?
    public int platform;
    public int color;

    public TrainEstimate[] trainEstimates;

    public final class TrainEstimate {
        public int minutes;
        public int length;
        public boolean bikesAllowed;
    }
}
