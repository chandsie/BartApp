package com.shreyaschand.bart.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "estimate")
public final class Estimate {

    @Element(name = "minutes")
    public String minutes; // can be int value or "Leaving"

    @Element(name = "platform")
    public int platform;

    @Element(name = "direction")
    public String direction;

    @Element(name = "length")
    public int length;

    @Element(name = "hexcolor")
    public String color;

    @Element(name = "bikeflag")
    public int bikesAllowed;
}
