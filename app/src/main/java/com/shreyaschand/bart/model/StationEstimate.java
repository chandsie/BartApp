package com.shreyaschand.bart.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.util.List;

@Root(name = "station")
public final class StationEstimate {

    @Element(name = "abbr")
    @Convert(Station.StationAbbreviationConverter.class)
    public Station station;

    @ElementList(inline = true)
    public List<DestinationEstimate> destinationEstimates;
}
