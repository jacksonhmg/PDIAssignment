import java.util.*;
import java.io.*;

public class UserInterface2{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);



        CovidRecord[] covidRecordArray = readFile("jrc1.csv");
        /*for(int i=0; i<covidRecordArray.length; i++){ //TRYING TO DEBUG AND FIND OUT WHAT THE PROBLEM IS
            //if(covidRecordArray[i] != null){
                System.out.println(covidRecordArray[i].getDate());
            //}
            
        }
        System.out.println(covidRecordArray.length);
        System.out.println(covidRecordArray);
        */
    }

    public static CovidRecord[] readFile(String pFileName){
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum = 1784;
        String line;
        int totalReturnVal = 0;
        CovidRecord[] covidRecordArray = new CovidRecord[1];

        try{
            /*fileStream = new FileInputStream(pFileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0;
            line = bufRdr.readLine();
            while(line != null){
                lineNum++;
                line = bufRdr.readLine();
            }
            fileStream.close();

            */covidRecordArray = new CovidRecord[lineNum];

            fileStream = new FileInputStream(pFileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            for(int i=0; i < (lineNum); i++){
                //lineNum++;
                line = bufRdr.readLine();
                if(line != null){

                
                String [] stringArray = processLine(line);
                /*for(i = 0; i<stringArray.length; i++){
                    System.out.print(stringArray[i]);
                    System.out.println();
                }*/
                for(i = 0; i<stringArray.length; i++){
                    if(stringArray[i].isEmpty()){
                        stringArray[i] = "12112121";
                    }
                }

                Country country;
                country = new Country(stringArray[1], stringArray[2], stringArray[3], stringArray[12], Double.parseDouble(stringArray[4]), Double.parseDouble(stringArray[5]));
                CovidRecord covidRecord;
                covidRecord = new CovidRecord(stringArray[0], Integer.parseInt(stringArray[6]), Integer.parseInt(stringArray[7]), Integer.parseInt(stringArray[8]), Integer.parseInt(stringArray[9]), Integer.parseInt(stringArray[10]), Integer.parseInt(stringArray[11]), country);
                //covidRecordArray[i] = new CovidRecord(stringArray[0], Integer.parseInt(stringArray[6]), Integer.parseInt(stringArray[7]), Integer.parseInt(stringArray[8]), Integer.parseInt(stringArray[9]), Integer.parseInt(stringArray[10]), Integer.parseInt(stringArray[11]), country);

                covidRecordArray[i] = covidRecord;
                //System.out.println(covidRecordArray[i].getDate()); //printed it all out well
                //System.out.println(covidRecord.getCumulativePositive()); //DID NOT WORK, ALL ZEROS
                //System.out.println(stringArray[6]); //worked??

            }
            //fileStream.close();
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
        for(int i = 0; i < lineNum; i++){
            if(covidRecordArray[i] != null){
                System.out.println(covidRecordArray[i].getCountry()); // (output was null) COME BACK TO LATER. CANNOT ACCESS CONTINENT OF COUNTRY
                System.out.println(covidRecordArray[i].getDate()); //didnt work, only got last date
                System.out.println(covidRecordArray[i].getCumulativePositive());
                System.out.println(covidRecordArray[i].getCumulativeDeceased());
                System.out.println(covidRecordArray[i].getCumulativeRecovered());
                System.out.println(covidRecordArray[i].getCurrentlyPositive());
                System.out.println(covidRecordArray[i].getHospitalized());
                System.out.println(covidRecordArray[i].getIntensiveCare());
            }
            
        }
        //System.out.println(covidRecordArray.length); //worked
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

}