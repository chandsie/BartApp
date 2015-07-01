package com.shreyaschand.bart.model;

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

    Station(String abbrev, String full) {
        this.abbrev = abbrev;
        this.full = full;
    }
}
