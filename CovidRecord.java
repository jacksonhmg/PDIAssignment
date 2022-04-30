import java.util.*;

public class CovidRecord{
    private String date;
    private int cumulativePositive;
    private int cumulativeDeceased;
    private int cumulativeRecovered;
    private int currentlyPositive;
    private int hospitalized;
    private int intensiveCare;
    private Country country;


    public CovidRecord(String pDate, int pCumulativePositive, int pCumulativeDeceased, int pCumulativeRecovered, int pCurrentlyPositive, int pHospitalized, int pIntensiveCare, Country pCountry){
        //super(piso3, pcontinent, pcountryName, pnuts, plat, plon);
        date = pDate;
        cumulativePositive = pCumulativePositive;
        cumulativeDeceased = pCumulativeDeceased;
        cumulativeRecovered = pCumulativeRecovered;
        currentlyPositive = pCurrentlyPositive;
        hospitalized = pHospitalized;
        intensiveCare = pIntensiveCare;
        country = pCountry;
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
