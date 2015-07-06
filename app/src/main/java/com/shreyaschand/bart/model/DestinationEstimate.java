package com.shreyaschand.bart.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.util.List;

@Root(name = "etd")
public final class DestinationEstimate {

    @Element(name = "abbreviation")
    @Convert(Station.StationAbbreviationConverter.class)
    public Station destination;

    @ElementList(inline = true)
    public List<Estimate> estimates;
}
