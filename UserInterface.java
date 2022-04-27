import java.util.*;
import java.io.*;

public class UserInterface{
    

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

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
        
        int input = sc.nextInt();

        switch(input){
            case 1:
                String input1String = "All Countries";
                int case1input = secondMenu();
                thirdMenu(case1input, input1String);
                System.out.println(case1input);
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

    public static int readFile(String pFileName){
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        int totalReturnVal = 0;

        try{
            fileStream = new FileInputStream(pFileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0;
            line = bufRdr.readLine();
            while(lineNum < 4){
                lineNum++;
                while(lineNum >= 1){
                    String[] returnVal = processLine(line);
                    System.out.println(returnVal);
                    //int cumPosVal = Integer.parseInt(returnVal[6]);
                    //return cumPosVal;
                    //totalReturnVal += cumPosVal;
                    //line = bufRdr.readLine();
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
        return totalReturnVal;
    }
    

    public static String[] processLine(String csvRow){  //reading one row of a csv file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(",");
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

        int input2 = sc2.nextInt();
        sc2.close();
        return input2;

    }

    public static void thirdMenu(int pInput, String pInputString){
        //Scanner sc3 = new Scanner(System.in);

        switch(pInput){
            case 1:
                int aaaa = readFile("jrc1.csv");
                System.out.println("Cumulative number of positive cases in " + pInputString + ":" + aaaa);
            break;
            case 2:

            break;
            case 3:

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



