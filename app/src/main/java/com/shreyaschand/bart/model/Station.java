package com.shreyaschand.bart.model;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.HashMap;

public enum Station {

    twelfth      ("12th", "12th St. Oakland City Center"),
    sixteenth    ("16th", "16th St. Mission (SF)"),
    nineteenth   ("19th", "19th St. Oakland"),
    twentyfourth ("24th", "24th St. Mission (SF)"),
    ashb         ("ashb", "Ashby (Berkeley)"),
    balb         ("balb", "Balboa Park (SF)"),
    bayf         ("bayf", "Bay Fair (San Leandro)"),
    cast         ("cast", "Castro Valley"),
    civc         ("civc", "Civic Center (SF)"),
    cols         ("cols", "Coliseum/Oakland Airport"),
    colm         ("colm", "Colma"),
    conc         ("conc", "Concord"),
    daly         ("daly", "Daly City"),
    dbrk         ("dbrk", "Downtown Berkeley"),
    dubl         ("dubl", "Dublin/Pleasanton"),
    deln         ("deln", "El Cerrito del Norte"),
    plza         ("plza", "El Cerrito Plaza"),
    embr         ("embr", "Embarcadero (SF)"),
    frmt         ("frmt", "Fremont"),
    ftvl         ("ftvl", "Fruitvale (Oakland)"),
    glen         ("glen", "Glen Park (SF)"),
    hayw         ("hayw", "Hayward"),
    lafy         ("lafy", "Lafayette"),
    lake         ("lake", "Lake Merritt (Oakland)"),
    mcar         ("mcar", "MacArthur (Oakland)"),
    mlbr         ("mlbr", "Millbrae"),
    mont         ("mont", "Montgomery St. (SF)"),
    nbrk         ("nbrk", "North Berkeley"),
    ncon         ("ncon", "North Concord/Martinez"),
    orin         ("orin", "Orinda"),
    pitt         ("pitt", "Pittsburg/Bay Point"),
    phil         ("phil", "Pleasant Hill"),
    powl         ("powl", "Powell St. (SF)"),
    rich         ("rich", "Richmond"),
    rock         ("rock", "Rockridge (Oakland)"),
    sbrn         ("sbrn", "San Bruno"),
    sfia         ("sfia", "San Francisco Int'l Airport"),
    sanl         ("sanl", "San Leandro"),
    shay         ("shay", "South Hayward"),
    ssan         ("ssan", "South San Francisco"),
    ucty         ("ucty", "Union City"),
    wcrk         ("wcrk", "Walnut Creek"),
    wdub         ("wdub", "West Dublin"),
    woak         ("woak", "West Oakland");

    public String abbrev;
    public String full;

    private static final HashMap<String, Station> abbrevLookupMap;

    static {
        abbrevLookupMap = new HashMap<>();
        Station[] stations = Station.values();
        for (Station station : stations) {
            abbrevLookupMap.put(station.abbrev, station);
        }
    }

    public static Station findStation(String abbrev) {
        return abbrevLookupMap.get(abbrev);
    }

    Station(String abbrev, String full) {
        this.abbrev = abbrev;
        this.full = full;
    }

    public static final class StationAbbreviationConverter implements Converter<Station> {

        @Override
        public Station read(InputNode node) throws Exception {
            return Station.findStation(node.getValue().toLowerCase());
        }

        @Override
        public void write(OutputNode node, Station value) throws Exception {
            node.setValue(value.abbrev);
        }
    }
}
