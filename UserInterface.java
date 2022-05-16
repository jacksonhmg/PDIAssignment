//REMEMBER GIT REPLACED LF WITH CRLF IN CSV FILE. IF THIS DOESNT WORK ON UNIX, CHANGE THIS BACK


import java.util.*;
import java.io.*;

public class UserInterface{
    
    

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop){ //allows the whole program to return to the start after seeing chosen knowledge

        CovidRecord[] covidRecordArray = readFile("jrc2.0.csv");


        System.out.println("Welcome to the JRC COVID-19 Analysis Program. There are a total of 1778 records loaded. Please make a selection from the Menu below to choose which area (or date) to analyse: ");
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

        int input = sc.nextInt();
        String inputString = "";
        int caseInput;
        String caseInputString = "";
        boolean checker = false;
        switch(input){
            case 1:
                inputString = "All countries";
            break;
            case 2:
                inputString = "SA"; //Assigning variables that are easily understood and are used to read through the jrc file
            break;
            case 3:
                inputString = "NA";
            break;
            case 4:
                inputString = "OC";
            break;
            case 5:
                inputString = "AS";
            break;
            case 6:
                inputString = "AF";
            break;
            case 7:
                inputString = "EU";
            break;
            case 8:
            sc.nextLine(); //THROWS AWAY THE '\n' (CONSUMES IT SO ITS NOT A PROBLEM) of the last sc.next intake. Would case an error without this.
            do { //ask at least once
                inputString = sc.nextLine(); 
                for(int j = 0; j <covidRecordArray.length; j++){
                    if(covidRecordArray[j] != null){
                        if((covidRecordArray[j].getCountry().getCountryName()).equals(inputString)){ //check to see if the country inputted by the user is a valid country name in the jrc file
                            checker = true; //if it is a valid country, break out the loop
                        }
                    }
                } 
                if(checker == false){
                    System.out.println("Not a valid Country"); //notify user that they have inputted an incorrect country
                } 
            } while (checker == false); //loop until they input a correct country
            break;
            case 9: //very similar to case 8 except swapped country for date
            sc.nextLine(); 
            do {
                inputString = sc.nextLine();
                for(int j = 0; j <covidRecordArray.length; j++){
                    if(covidRecordArray[j] != null){
                        if((covidRecordArray[j].getDate()).equals(inputString)){
                            checker = true;
                        }
                    }
                } 
                if(checker == false){
                    System.out.println("Not a valid Date");
                } 
            } while (checker == false);
            break;
        }

        caseInput = secondMenu(sc); //used the scanner as a parameter here beccause it gives errors if you use different scanners. i open and close the one scanner within the main method. secondMenu uses it but doesnt open or close
        thirdMenu(caseInput, inputString, covidRecordArray);

    }
        sc.close();
    }

    public static CovidRecord[] readFile(String pFileName){
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum = 0;
        String line;
        CovidRecord[] covidRecordArray = new CovidRecord[1];

        try{
            fileStream = new FileInputStream(pFileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0;
            line = bufRdr.readLine();
            while(line != null){
                lineNum++; //for calculating length of CSV file used (number of lines)
                line = bufRdr.readLine();
            }
            fileStream.close();

            covidRecordArray = new CovidRecord[lineNum];

            fileStream = new FileInputStream(pFileName); //all this is renewed again to start fresh to cycle through file from the top
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            for(int i=0; i < (lineNum); i++){  //keep within length that was found just prior
                line = bufRdr.readLine();
                if(line != null){

                String [] stringArray = processLine(line);

                for(int j = 0; j<stringArray.length; j++){
                    if(stringArray[j].isEmpty()){
                        stringArray[j] = "0"; //dealing with empty cells. if any of the cells in a line were empty, they would just be assigned the value zero
                    }
                }

                Country country = new Country(stringArray[1], stringArray[2], stringArray[3], stringArray[12], Double.parseDouble(stringArray[4]), Double.parseDouble(stringArray[5])); //create a new country object based on the important values parsed from the current line in the CSV file
                CovidRecord covidRecord = new CovidRecord(stringArray[0], Integer.parseInt(stringArray[6]), Integer.parseInt(stringArray[7]), Integer.parseInt(stringArray[8]), Integer.parseInt(stringArray[9]), Integer.parseInt(stringArray[10]), Integer.parseInt(stringArray[11]), country); //create a new CovidRecord object based on current CSV line

                covidRecordArray[i] = covidRecord; //assign the newly created covidRecord object to the current element of the array

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
            System.out.println("An error! " + errorDetails.getMessage());
        }
        
        return covidRecordArray;
    }
    

    public static String[] processLine(String csvRow){  //reading one row of a csv file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(",", -1); //the -1 keeps empty cells in the line rather than remove them. the empty cells are then dealt with in the readFile method, as explained above
        return splitLine;
        }

    public static int secondMenu(Scanner pSc){ //the second menu created into its own function

        System.out.println("> 1 = Total number of cumulatively positive cases");
        System.out.println("> 2 = Total number of cumulatively deceased cases");
        System.out.println("> 3 = Total number of cumulatively recovered cases");
        System.out.println("> 4 = Average daily number of currently positive cases");
        System.out.println("> 5 = Number and percentage of cumulatively positive cases recovered");
        System.out.println("> 6 = Number and percentage of cumulatively positive cases deceased");
        System.out.println("> 7 = All of the above statistics");

        int input2 = pSc.nextInt();

        return input2; //return the choice of the user on the second menu
    }

    public static void thirdMenu(int pInput, String pInputString, CovidRecord[] pCovidRecordArray){ //deals with the final output of this series of choices
        int finalCalcVal = 0;
        int[] finalValArr = new int[2];
        int[] otherFinalValArr = new int [3];
        switch(pInput){ //i have two separate switch cases so that in the first, i can address multiple outcomes as a whole. whereas in the second switch case, i then address each individually
            case 1: case 2: case 3: case 4: //if any of the first four options on second menu
                finalCalcVal = firstFourMenuCalcs(pInput, pInputString, pCovidRecordArray);
            break;
            case 5: case 6: 
                finalValArr = nextTwoMenuCalcs(pInput, pInputString, pCovidRecordArray); //the same. run the method first then change the variable being printed if wanting to print an entered country or date
            break;
        }

        switch(pInput){
            case 1:
                System.out.println("Cumulative number of positive cases in " + pInputString + ": " + finalCalcVal); //pInputString here would now be changed if needed to be. e.g. would now be "Brazil" and not "Enter a Country"
                printEnding(); //see below for what this does. essentially helps aesthetic
            break;
            case 2:
                System.out.println("Cumulative number of deceased cases in " + pInputString + ": " + finalCalcVal);
                printEnding();
            break;
            case 3:
                System.out.println("Cumulative number of recovered cases in " + pInputString + ": " + finalCalcVal);
                printEnding();
            break;
            case 4:
                System.out.println("Average daily number of currently positive cases in " + pInputString + ": " + finalCalcVal);
                printEnding();  
            break;
            case 5:
                System.out.println("Number and percentage of cumulatively positive cases recovered in " + pInputString + ": " + finalValArr[0] + " = " + (((double) finalValArr[0] / (double) finalValArr[1]) * 100) + "%"); //I CALCULATE PERCENTAGE HERE SO THAT I CAN HAVE FIRST VALUE AS AN INTEGER FOR FIRST "NUMBER PART OF PRINT" AND THEN CAN CHANGE IT TO A PERCENTAGE (double) AFTERWARDS.
                printEnding();
            break;
            case 6:
                System.out.println("Number and percentage of cumulatively positive cases deceased in " + pInputString + ": " + finalValArr[0] + " = " + (((double) finalValArr[0] / (double) finalValArr[1]) * 100) + "%");
                printEnding();                
            break;
            case 7: //this essentially runs all the other options in one go
                otherFinalValArr = lastMenuCalc(pInputString, pCovidRecordArray);
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

    public static void printEnding(){ //creating a method for ending the print statements so that it could be used easily. helps the aesthetic when viewing
        System.out.println();
        System.out.println("===============================");
        System.out.println();
    }       


    public static int firstFourMenuCalcs(int pInput, String pInputString, CovidRecord[] pCovidRecordArray){
        int totalReturnVal = 0;
        int ticker = 0;
        
        boolean notall = false;
        switch(pInputString){ 
            case "All countries":

            break;
            default:
                notall = true; //notall if a boolean checker to check if the user did not input "All countries"
            break;
        }

        switch(pInput){
            case 1:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        int x = pCovidRecordArray[i].getCumulativePositive(); //Does it for all possible options
                        if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ //if notall is true(not "All countries" so checking for something specific) and its doesnt match any given country, continent or date then it must not be what we're looking for
                            x = 0; //negate this entry if so
                        }
                        totalReturnVal += x;
                    }
                }
            break;
            case 2:
            for(int i = 0; i < pCovidRecordArray.length; i++){
                if(pCovidRecordArray[i] != null){
                    int x = pCovidRecordArray[i].getCumulativeDeceased();
                    if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){
                        x = 0;
                    }
                    totalReturnVal += x;
                }
            }
            break;
            case 3:
            for(int i = 0; i < pCovidRecordArray.length; i++){
                if(pCovidRecordArray[i] != null){
                    int x = pCovidRecordArray[i].getCumulativeRecovered();
                    if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){
                        x = 0;
                    }
                    totalReturnVal += x;
                }
            }
            break;
            case 4:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        int x = pCovidRecordArray[i].getCurrentlyPositive();
                        ticker ++;
                        if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){
                            x = 0;
                            ticker --; //additional negate needed so that the counter only ticks up on valid lines
                        }
                        totalReturnVal += x;
                    }
                }
                totalReturnVal = totalReturnVal / ticker; //to get average
            break;
            default:

            break;
        }
        return totalReturnVal;
    }


    public static int[] nextTwoMenuCalcs(int pInput, String pInputString, CovidRecord[] pCovidRecordArray){ //see explanation in thirdMenu for why i send an array of two integers back
        int[] totalReturnValArr = new int[2]; 
        int totalReturnVal1 = 0;
        int totalReturnVal2 = 0;

        boolean notall = false;
        switch(pInputString){
            case "All countries":

            break;
            default:
                notall = true;
            break;
        }

        switch(pInput){
            case 5:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        int x = pCovidRecordArray[i].getCumulativeRecovered();
                        if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ //see explanation above in firstFourMenuCalcs
                            x = 0;
                        }
                        totalReturnVal1 += x;
                    }
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        int x = pCovidRecordArray[i].getCumulativePositive();
                        if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ 
                            x = 0;
                        }
                        totalReturnVal2 += x;
                    }
                }
            totalReturnValArr[0] = totalReturnVal1;
            totalReturnValArr[1] = totalReturnVal2;
            break;
            case 6:
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        int x = pCovidRecordArray[i].getCumulativeDeceased();
                        if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ 
                            x = 0;
                        }
                        totalReturnVal1 += x;
                    }
                }
                for(int i = 0; i < pCovidRecordArray.length; i++){
                    if(pCovidRecordArray[i] != null){
                        int x = pCovidRecordArray[i].getCumulativePositive();
                        if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ 
                            x = 0;
                        }
                        totalReturnVal2 += x;
                    }
                }
            totalReturnValArr[0] = totalReturnVal1;
            totalReturnValArr[1] = totalReturnVal2;
            break;
            default:

            break;
        }



        return totalReturnValArr;
    }

    
    public static int[] lastMenuCalc(String pInputString, CovidRecord[] pCovidRecordArray){ //a summary of all other methods put into one succint method
        int[] totalReturnValArr = new int[4];
        int cumPosReturnVal = 0;
        int cumDecReturnVal = 0;
        int cumRecReturnVal = 0;
        int aveCurReturnVal = 0;
        int ticker = 0;

        boolean notall = false;
        switch(pInputString){
            case "All countries":

            break;
            default:
                notall = true;
            break;
        }

        for(int i = 0; i < pCovidRecordArray.length; i++){
            if(pCovidRecordArray[i] != null){
                int x = pCovidRecordArray[i].getCumulativePositive();
                if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ 
                    x = 0;
                }
                cumPosReturnVal += x;
            }
        }
        for(int i = 0; i < pCovidRecordArray.length; i++){
            if(pCovidRecordArray[i] != null){
                int x = pCovidRecordArray[i].getCumulativeDeceased();
                if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ 
                    x = 0;
                }
                cumDecReturnVal += x;
            }
        }
        for(int i = 0; i < pCovidRecordArray.length; i++){
            if(pCovidRecordArray[i] != null){
                int x = pCovidRecordArray[i].getCumulativeRecovered();
                if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ 
                    x = 0;
                }
                cumRecReturnVal += x;
            }
        }
        for(int i = 0; i < pCovidRecordArray.length; i++){
            if(pCovidRecordArray[i] != null){
                int x = pCovidRecordArray[i].getCurrentlyPositive();
                ticker ++;
                if(notall && !(pCovidRecordArray[i].getCountry().getCountryName().equals(pInputString)) && !(pCovidRecordArray[i].getDate().equals(pInputString)) && !(pCovidRecordArray[i].getCountry().getContinent().equals(pInputString))){ 
                    x = 0;
                    ticker --;
                }
                aveCurReturnVal += x;
            }
        }
        aveCurReturnVal = aveCurReturnVal / ticker;
        totalReturnValArr[0] = cumPosReturnVal;
        totalReturnValArr[1] = cumDecReturnVal;
        totalReturnValArr[2] = cumRecReturnVal;
        totalReturnValArr[3] = aveCurReturnVal;





        return totalReturnValArr;


    }




}



