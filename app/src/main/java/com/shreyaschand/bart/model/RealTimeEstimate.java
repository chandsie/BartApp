package com.shreyaschand.bart.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "root")
public class RealTimeEstimate {

    @ElementList(inline = true)
    public List<StationEstimate> stationEstimates;
}
