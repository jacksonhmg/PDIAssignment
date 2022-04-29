import java.util.*;
import java.io.*;

public class UserInterface{
    

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);



        CovidRecord[] covidRecordArray = readFile("jrc1.csv");
        for(int i=0; i<covidRecordArray.length; i++){ //TRYING TO DEBUG AND FIND OUT WHAT THE PROBLEM IS
            if(covidRecordArray[i] != null){
                System.out.println(covidRecordArray[i].getDate());
            }
            
        }
        System.out.println(covidRecordArray);



        System.out.println("Welcome to the JRC COVID-19 Analysis Program. There are a total of ‘XYZ’ records loaded. Please make a selection from the Menu below tochoose which area (or date) to analyse: ");
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
        
        //int input = sc.nextInt();
        int input = 1;

        switch(input){
            case 1:
                String input1String = "All Countries";
                int case1input = secondMenu();
                thirdMenu(case1input, input1String);
                //System.out.println(case1input);
            break;
            case 2:
                int case2input = secondMenu();
                //System.out.println(case1input);
            break;
            case 3:
                int case3input = secondMenu();
                //System.out.println(case1input);
            break;
            case 4:
                int case4input = secondMenu();
                //System.out.println(case1input);
            break;
            case 5:
                int case5input = secondMenu();
                //System.out.println(case1input);
            break;
            case 6:
                int case6input = secondMenu();
                //System.out.println(case1input);
            break;
            case 7:
                int case7input = secondMenu();
                //System.out.println(case1input);
            break;
            case 8:
                int case8input = secondMenu();
                //System.out.println(case1input);
            break;
            case 9:
                int case9input = secondMenu();
                //System.out.println(case1input);
            break;






        }





        sc.close();
    }

    public static CovidRecord[] readFile(String pFileName){
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        int totalReturnVal = 0;
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
                //lineNum++;
                line = bufRdr.readLine();
                if(line != null){

                
                String [] stringArray = processLine(line);
                for(i = 0; i<stringArray.length; i++){
                    if(stringArray[i].isEmpty()){
                        stringArray[i] = "0";
                    }
                }

                Country country = new Country(stringArray[1], stringArray[2], stringArray[3], stringArray[12], Double.parseDouble(stringArray[4]), Double.parseDouble(stringArray[5]));
                CovidRecord covidRecord = new CovidRecord(stringArray[0], Integer.parseInt(stringArray[6]), Integer.parseInt(stringArray[7]), Integer.parseInt(stringArray[8]), Integer.parseInt(stringArray[9]), Integer.parseInt(stringArray[10]), Integer.parseInt(stringArray[11]), country);
                
                covidRecordArray[i] = covidRecord;
                //System.out.println(covidRecordArray[i].getDate());

            }

            }
        } catch(IOException errorDetails){
            if(fileStream != null){
                try{
                    fileStream.close();
                }catch(IOException ex2){

                }
            }
            System.out.println("Error brah"+ errorDetails.getMessage());
        }
        return covidRecordArray;
    }
    

    public static String[] processLine(String csvRow){  //reading one row of a csv file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(",", -1);
        int lineLength = splitLine.length;
        return splitLine;
        //int cumPosVal = Integer.parseInt(splitLine[6]);
        //return cumPosVal;
        /*for(int i = 0; i < lineLength; i++){
            System.out.print(splitLine[i] + " ");
        }
        System.out.println(" ");
    */}

    public static int secondMenu(){
        Scanner sc2 = new Scanner(System.in);

        
        System.out.println("> 1 = Total number of cumulatively positive cases");
        System.out.println("> 2 = Total number of cumulatively deceased cases");
        System.out.println("> 3 = Total number of cumulatively recovered cases");
        System.out.println("> 4 = Total number of currently positive cases");
        System.out.println("> 5 = Number and percentage of cumulatively positive cases recovered");
        System.out.println("> 6 = Number and percentage of cumulatively positive cases deceased");
        System.out.println("> 7 = All of the above statistics");

        //int input2 = sc2.nextInt();
        int input2 = 1;
        sc2.close();
        return input2;

    }

    public static void thirdMenu(int pInput, String pInputString){
        //Scanner sc3 = new Scanner(System.in);
        //int finalCalcVal = readFileForNum("jrc1.csv",pInput);
        int finalCalcVal = 0;
        switch(pInput){
            case 1:
                //int finalCalcVal = readFileForNum("jrc1.csv",pInput);
                System.out.println("Cumulative number of positive cases in " + pInputString + ":" + finalCalcVal);
            break;
            case 2:
                System.out.println("Cumulative number of deceased cases in " + pInputString + ":" + finalCalcVal);
            break;
            case 3:
                System.out.println("Cumulative number of recovered cases in " + pInputString + ":" + finalCalcVal);
            break;
            case 4:
                
            break;
            case 5:

            break;
            case 6:

            break;
            case 7:

            break;



        }
        

        //int input3 = sc3.nextInt();
        //sc3.close();
        //return input2;
    }

}



