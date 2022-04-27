import java.util.*;

public class CovidRecord {
    private String date;
    private int cumulativePositive;
    private int cumulativeDeceased;
    private int cumulativeRecovered;
    private int currentlyPositive;
    private int hospitalized;
    private int intensiveCare;
    private Country country;


    public CovidRecord(String pDate, int pCumulativePositive, int pCumulativeDeceased, int pCumulativeRecovered, int pCurrentlyPositive, int pHospitalized, int pIntensiveCare, Country pCountry){
        date = pDate;
        pCumulativePositive = cumulativePositive;
        pCumulativeDeceased = cumulativeDeceased;
        pCumulativeRecovered = cumulativeRecovered;
        pCurrentlyPositive = currentlyPositive;
        pHospitalized = hospitalized;
        pIntensiveCare = intensiveCare;
        pCountry = country;
    }

    public String getDate(){
        return date;
    }

    public int getCumulativePositive(){
        return cumulativePositive;
    }

    public int getCumulativeDeceased(){
        return cumulativeDeceased;
    }

    public int getCumulativeRecovered(){
        return cumulativeRecovered;
    }

    public int getCurrentlyPositive(){
        return currentlyPositive;
    }

    public int getHospitalized(){
        return hospitalized;
    }

    public int getIntensiveCare(){
        return intensiveCare;
    }

    public Country getCountry(){
        return country;
    }

}
