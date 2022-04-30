import java.util.*;


public class Country {
    private String iso3;
    private String continent;
    private String countryName;
    private String nuts;
    private double lat;
    private double lon;

    //Parameters Constructor
    public Country(String piso3, String pcontinent, String pcountryName, String pnuts, double plat, double plon){
        iso3 = piso3;
        continent = pcontinent;
        countryName = pcountryName;
        nuts = pnuts;
        lat = plat;
        lon = plon;
    }

    public String getIso3(){
        return iso3;
    }

    public String getContinent(){
        return continent;
    }

    public String getCountryName(){
        return countryName;
    }

    public String getNuts(){
        return nuts;
    }

    public double getLat(){
        return lat;
    }

    public double getLon(){
        return lon;
    }

}
