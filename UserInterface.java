//REMEMBER GIT REPLACED LF WITH CRLF IN CSV FILE. IF THIS DOESNT WORK ON UNIX, CHANGE THIS BACK




import java.util.*;
import java.io.*;

public class UserInterface{
    
    

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop){

        

        CovidRecord[] covidRecordArray = readFile("jrc2.0.csv");


        System.out.println("Welcome to the JRC COVID-19 Analysis Program. There are a total of 1778 records loaded. Please make a selection from the Menu below tochoose which area (or date) to analyse: ");
        System.out.println("> 1 = All countries");
        System.out.println("> 2 = Countries in South America");
        System.out.println("> 3 = Countries in North America");
        System.out.println("> 4 = Countries in Oceania");
        System.out.println("> 5 = Countries in Asia");
        System.out.println("> 6 = Countries in Africa");
        System.out.println("> 7 = Countries in Europe");
        System.out.println("> 8 = Enter a Country");
        System.out.println("> 9 = Enter a Date");
        System.out.println();
        System.out.println("Enter selection: ");
        
        
        //String x = "getCountryName()";
        
        //System.out.println(covidRecordArray[1].getCountry().x);

        int input = sc.nextInt();
        //int input = 1;
        String inputString;
        int caseInput;
        String caseInputString = "";
        boolean checker = false;
        switch(input){
            case 1:
                inputString = "All countries";
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, "");
                //System.out.println(case1input);
            break;
            case 2:
                inputString = "SA";
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, "");
                //System.out.println(case1input);
            break;
            case 3:
                inputString = "NA";
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, "");
                //System.out.println(case1input);
            break;
            case 4:
                inputString = "OC";
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, "");
                //System.out.println(case1input);
            break;
            case 5:
                inputString = "AS";
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, "");
                //System.out.println(case1input);
            break;
            case 6:
                inputString = "AF";
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, "");
                //System.out.println(case1input);
            break;
            case 7:
                inputString = "EU";
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, "");
                //System.out.println(case1input);
            break;
            case 8:
            sc.nextLine(); //THROWS AWAY THE '\n' (CONSUMES IT SO ITS NOT A PROBLEM)
            inputString = "Enter a Country";
            do {
                caseInputString = sc.nextLine();
                for(int j = 0; j <covidRecordArray.length; j++){
                    if(covidRecordArray[j] != null){
                        if((covidRecordArray[j].getCountry().getCountryName()).equals(caseInputString)){
                            checker = true;
                        }
                    }
                } 
                if(checker == false){
                    System.out.println("Not a valid Country");
                } 
            } while (checker == false);
            caseInput = secondMenu(sc);
            thirdMenu(caseInput, inputString, covidRecordArray, caseInputString);
                //System.out.println(case1input);
            break;
            case 9:
                //System.out.println(case1input);
                inputString = "Enter a Date";
                caseInputString = sc.nextLine();
                caseInput = secondMenu(sc);
                thirdMenu(caseInput, inputString, covidRecordArray, caseInputString);
            break;






        }


    }


        sc.close();
    }

    public static CovidRecord[] readFile(String pFileName){
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum = 0;
        String line;
        //int totalReturnVal = 0;
        CovidRecord[] covidRecordArray = new CovidRecord[1];

        try{
            fileStream = new FileInputStream(pFileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0;
            line = bufRdr.readLine();
            while(line != null){
                lineNum++;
                line = bufRdr.readLine();
            }
            fileStream.close();

            covidRecordArray = new CovidRecord[lineNum];

            fileStream = new FileInputStream(pFileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            for(int i=0; i < (lineNum); i++){
                line = bufRdr.readLine();
                if(line != null){

                String [] stringArray = processLine(line);

                for(int j = 0; j<stringArray.length; j++){
                    if(stringArray[j].isEmpty()){
                        stringArray[j] = "0";
                    }
                }

                Country country = new Country(stringArray[1], stringArray[2], stringArray[3], stringArray[12], Double.parseDouble(stringArray[4]), Double.parseDouble(stringArray[5]));
                CovidRecord covidRecord = new CovidRecord(stringArray[0], Integer.parseInt(stringArray[6]), Integer.parseInt(stringArray[7]), Integer.parseInt(stringArray[8]), Integer.parseInt(stringArray[9]), Integer.parseInt(stringArray[10]), Integer.parseInt(stringArray[11]), country);

                covidRecordArray[i] = covidRecord;

            }

            }
            fileStream.close();
        } catch(IOException errorDetails){
            if(fileStream != null){
                try{
                    fileStream.close();
                }catch(IOException ex2){

                }
            }
            System.out.println("Error brah"+ errorDetails.getMessage());
        }
        /*for(int i = 0; i < lineNum; i++){
            if(covidRecordArray[i] != null){
                System.out.println(covidRecordArray[i].getCountry().getCountryName()); 
                /*System.out.println(covidRecordArray[i].getDate()); 
                System.out.println(covidRecordArray[i].getCumulativePositive());
                System.out.println(covidRecordArray[i].getCumulativeDeceased());
                System.out.println(covidRecordArray[i].getCumulativeRecovered());
                System.out.println(covidRecordArray[i].getCurrentlyPositive());
                System.out.println(covidRecordArray[i].getHospitalized());
                System.out.println(covidRecordArray[i].getIntensiveCare());
            }
            
        }*/
        //System.out.println(covidRecordArray[0].getCumulativePositive());
        return covidRecordArray;
    }
    

    public static String[] processLine(String csvRow){  //reading one row of a csv file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(",", -1);
        return splitLine;
        }

    public static int secondMenu(Scanner pSc){
        //Scanner sc2 = new Scanner(System.in);

        
        System.out.println("> 1 = Total number of cumulatively positive cases");
        System.out.println("> 2 = Total number of cumulatively deceased cases");
        System.out.println("> 3 = Total number of cumulatively recovered cases");
        System.out.println("> 4 = Average daily number of currently positive cases");
        System.out.println("> 5 = Number and percentage of cumulatively positive cases recovered");
        System.out.println("> 6 = Number and percentage of cumulatively positive cases deceased");
        System.out.println("> 7 = All of the above statistics");

        int input2 = pSc.nextInt();
        //int input2 = 1;
        //sc2.close();
        return input2;

    }

    public static void thirdMenu(int pInput, String pInputString, CovidRecord[] pCovidRecordArray, String pCaseInputString){
        int finalCalcVal = 0;
        int[] finalValArr = new int[2];
        int[] otherFinalValArr = new int [3];
        switch(pInput){
            case 1:
                finalCalcVal = firstFourMenuCalcs(pInput, pInputString, pCovidRecordArray, pCaseInputString);
                if(pInputString.equals("Enter a Country")){
                    pInputString = pCaseInputString;
                }
                System.out.println("Cumulative number of positive cases in " + pInputString + ": " + finalCalcVal);
                printEnding();
            break;
            case 2:
                finalCalcVal = firstFourMenuCalcs(pInput, pInputString, pCovidRecordArray, pCaseInputString);
                if(pInputString.equals("Enter a Country")){
                    pInputString = pCaseInputString;
                }
                System.out.println("Cumulative number of deceased cases in " + pInputString + ": " + finalCalcVal);
                printEnding();
            break;
            case 3:
                finalCalcVal = firstFourMenuCalcs(pInput, pInputString, pCovidRecordArray, pCaseInputString);
                if(pInputString.equals("Enter a Country")){
                    pInputString = pCaseInputString;
                }
                System.out.println("Cumulative number of recovered cases in " + pInputString + ": " + finalCalcVal);
                printEnding();
            break;
            case 4:
                finalCalcVal = firstFourMenuCalcs(pInput, pInputString, pCovidRecordArray, pCaseInputString);
                if(pInputString.equals("Enter a Country")){
                    pInputString = pCaseInputString;
                }
                System.out.println("Average daily number of currently positive cases in " + pInputString + ": " + finalCalcVal);
                printEnding();
                
            break;
            case 5:
                finalValArr = nextTwoMenuCalcs(pInput, pInputString, pCovidRecordArray);
                if(pInputString.equals("Enter a Country")){
                    pInputString = pCaseInputString;
                }
                System.out.println("Number and percentage of cumulatively positive cases recovered in " + pInputString + ": " + finalValArr[0] + " = " + (((double) finalValArr[0] / (double) finalValArr[1]) * 100) + "%");
                printEnding();
            break;
            case 6:
                finalValArr = nextTwoMenuCalcs(pInput, pInputString, pCovidRecordArray);
                if(pInputString.equals("Enter a Country")){
                    pInputString = pCaseInputString;
                }
                System.out.println("Number and percentage of cumulatively positive cases deceased in " + pInputString + ": " + finalValArr[0] + " = " + (((double) finalValArr[0] / (double) finalValArr[1]) * 100) + "%");
                printEnding();                
            break;
            case 7:
                otherFinalValArr = lastMenuCalc(pInputString, pCovidRecordArray);
                if(pInputString.equals("Enter a Country")){
                    pInputString = pCaseInputString;
                }
                System.out.println("Cumulative number of positive cases in " + pInputString + ": " + otherFinalValArr[0]);
                System.out.println("Cumulative number of deceased cases in " + pInputString + ": " + otherFinalValArr[1]);
                System.out.println("Cumulative number of recovered cases in " + pInputString + ": " + otherFinalValArr[2]);
                System.out.println("Average daily number of currently positive cases in " + pInputString + ": " + otherFinalValArr[3]);
                System.out.println("Number and percentage of cumulatively positive cases recovered in " + pInputString + ": " + otherFinalValArr[2] + " = " + (((double) otherFinalValArr[2] / (double) otherFinalValArr[0]) * 100) + "%");
                System.out.println("Number and percentage of cumulatively positive cases deceased in " + pInputString + ": " + otherFinalValArr[1] + " = " + (((double) otherFinalValArr[1] / (double) otherFinalValArr[0]) * 100) + "%");
                printEnding();
            break;



        }
        
    }

    public static void printEnding(){
        System.out.println();
        System.out.println("===============================");
        System.out.println();
    }       


    public static int firstFourMenuCalcs(int pInput, String pInputString, CovidRecord[] pCovidRecordArray, String pCaseInputString){
        int totalReturnVal = 0;
        int ticker = 0;
        /*switch(pInputString){
            case "All countries":
                switch(pInput){
                    case 1:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                totalReturnVal += pCovidRecordArray[i].getCumulativePositive();
                            }
                        }
                    break;
                    case 2:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                totalReturnVal += pCovidRecordArray[i].getCumulativeDeceased();
                            }
                        }
                    break;
                    case 3:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                            totalReturnVal += pCovidRecordArray[i].getCumulativeRecovered();
                            }
                        }
                    break;
                    case 4:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                            ticker ++;
                            totalReturnVal += pCovidRecordArray[i].getCurrentlyPositive();
                            
                            }
                        }
                        totalReturnVal = totalReturnVal / ticker;
                    break;
                    default:

                    break;
                }
            break;
            
            case "Enter a Country":
                switch(pInput){
                    case 1:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getCountryName().equals(pCaseInputString)){
                                    totalReturnVal += pCovidRecordArray[i].getCumulativePositive();
                                }
                                
                            }
                        }
                    break;
                    case 2:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getCountryName().equals(pCaseInputString)){
                                    totalReturnVal += pCovidRecordArray[i].getCumulativeDeceased();
                                }
                            }
                        }
                    break;
                    case 3:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getCountryName().equals(pCaseInputString)){
                                    totalReturnVal += pCovidRecordArray[i].getCumulativeRecovered(); //EQUALS ZERO INTENTIONALLY
                                }
                            }
                        }
                    break;
                    case 4:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getCountryName().equals(pCaseInputString)){
                                    ticker ++;
                                    totalReturnVal += pCovidRecordArray[i].getCurrentlyPositive();
                                }
                            }
                        }
                        totalReturnVal = totalReturnVal / ticker;
                    break;
                    default:

                    break;
                }
            break;
            case "Enter a Date":
            
            break;

            default:
                switch(pInput){
                    case 1:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    totalReturnVal += pCovidRecordArray[i].getCumulativePositive();
                                }
                                
                            }
                        }
                    break;
                    case 2:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    totalReturnVal += pCovidRecordArray[i].getCumulativeDeceased();
                                }
                            }
                        }
                    break;
                    case 3:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    totalReturnVal += pCovidRecordArray[i].getCumulativeRecovered(); //EQUALS ZERO INTENTIONALLY
                                }
                            }
                        }
                    break;
                    case 4:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    ticker ++;
                                    totalReturnVal += pCovidRecordArray[i].getCurrentlyPositive();
                                }
                            }
                        }
                        totalReturnVal = totalReturnVal / ticker;
                    break;
                    default:

                    break;
                }
            break;  
        }*/



        boolean notall = false;
        switch(pInputString){
            case "All countries":

            break;
            default:
                notall = true;
            break;
        }

        switch(pInput){
            case 1:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        int x = pCovidRecordArray[i].getCumulativePositive();
                        if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pCaseInputString)) && !(pCovidRecordArray[i].getDate().equals(pCaseInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){
                            x = 0;
                        }
                        totalReturnVal += x;
                    }
                }
            break;
            case 2:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        totalReturnVal += pCovidRecordArray[i].getCumulativeDeceased();
                    }
                }
            break;
            case 3:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                    totalReturnVal += pCovidRecordArray[i].getCumulativeRecovered();
                    }
                }
            break;
            case 4:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                    ticker ++;
                    totalReturnVal += pCovidRecordArray[i].getCurrentlyPositive();
                    
                    }
                }
                totalReturnVal = totalReturnVal / ticker;
            break;
            default:

            break;
        }






        return totalReturnVal;


    }


    public static int[] nextTwoMenuCalcs(int pInput, String pInputString, CovidRecord[] pCovidRecordArray){
        int[] totalReturnValArr = new int[2];
        int totalReturnVal1 = 0;
        int totalReturnVal2 = 0;
        switch(pInputString){
            case "All countries":
                switch(pInput){
                    case 5:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                totalReturnVal1 += pCovidRecordArray[i].getCumulativeRecovered();
                            }
                        }
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                totalReturnVal2 += pCovidRecordArray[i].getCumulativePositive();
                            }
                        }
                    totalReturnValArr[0] = totalReturnVal1;
                    totalReturnValArr[1] = totalReturnVal2;
                    break;
                    case 6:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                totalReturnVal1 += pCovidRecordArray[i].getCumulativeDeceased();
                            }
                        }
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                totalReturnVal2 += pCovidRecordArray[i].getCumulativePositive();
                            }
                        }
                totalReturnValArr[0] = totalReturnVal1;
                totalReturnValArr[1] = totalReturnVal2;
                    break;
                    default:

                    break;
                }
            break;
            
            default:
                switch(pInput){
                    case 5:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    totalReturnVal1 += pCovidRecordArray[i].getCumulativeRecovered();
                                }
                            }
                        }
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    totalReturnVal2 += pCovidRecordArray[i].getCumulativePositive();
                                }
                            }
                        }
                    totalReturnValArr[0] = totalReturnVal1;
                    totalReturnValArr[1] = totalReturnVal2;
                    break;
                    case 6:
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    totalReturnVal1 += pCovidRecordArray[i].getCumulativeDeceased();
                                }
                            }
                        }
                        for(int i = 0; i < pCovidRecordArray.length; i++){
                            if(pCovidRecordArray[i] != null){
                                if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                                    totalReturnVal2 += pCovidRecordArray[i].getCumulativePositive();
                                }
                            }
                        }
                totalReturnValArr[0] = totalReturnVal1;
                totalReturnValArr[1] = totalReturnVal2;
                    break;
                    default:
                    break;
                }
            break;  
        }
        return totalReturnValArr;
    }

    
    public static int[] lastMenuCalc(String pInputString, CovidRecord[] pCovidRecordArray){
        int[] totalReturnValArr = new int[4];
        int cumPosReturnVal = 0;
        int cumDecReturnVal = 0;
        int cumRecReturnVal = 0;
        int aveCurReturnVal = 0;
        int ticker = 0;
        switch(pInputString){
            case "All countries":
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        cumPosReturnVal += pCovidRecordArray[i].getCumulativePositive();
                    }
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        cumDecReturnVal += pCovidRecordArray[i].getCumulativeDeceased();
                    }
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        cumRecReturnVal += pCovidRecordArray[i].getCumulativeRecovered();
                    }
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        ticker ++;
                        aveCurReturnVal += pCovidRecordArray[i].getCurrentlyPositive();
                    
                    }
                }
                aveCurReturnVal = aveCurReturnVal / ticker;
                totalReturnValArr[0] = cumPosReturnVal;
                totalReturnValArr[1] = cumDecReturnVal;
                totalReturnValArr[2] = cumRecReturnVal;
                totalReturnValArr[3] = aveCurReturnVal;
            break;
            
            default:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                            cumPosReturnVal += pCovidRecordArray[i].getCumulativePositive();
                        }
                    }
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                            cumDecReturnVal += pCovidRecordArray[i].getCumulativeDeceased();
                        }
                    }
                    
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                            cumRecReturnVal += pCovidRecordArray[i].getCumulativeRecovered();
                        }
                    }
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        if(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString)){
                            ticker ++;
                            aveCurReturnVal += pCovidRecordArray[i].getCurrentlyPositive();
                        }
                    
                    }
                }
                aveCurReturnVal = aveCurReturnVal / ticker;
                totalReturnValArr[0] = cumPosReturnVal;
                totalReturnValArr[1] = cumDecReturnVal;
                totalReturnValArr[2] = cumRecReturnVal;
                totalReturnValArr[3] = aveCurReturnVal;

            break;  
        }
        return totalReturnValArr;


    }




}



