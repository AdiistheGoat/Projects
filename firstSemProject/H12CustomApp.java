///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           University Rankings
// Course:          Fall 2023, CS 200 001
//
// Author:          Aditya Goyal
// Email:           agoyal33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
// No help
// Source or Recipient; Chapter 12 Human Graded Zylabs
//
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Describe the problem you wrote the program to solve:
//
//   Rank the top universities according to the user criteria based on which decision factors matter
//   to the user and  how much the user values each decision factor.
//
//
// 2. Why did you choose the method header for the read file method (e.g., return type,
//    parameters, throws clause)?
//
//    I chose the return type to be an ArrayList of an ArrayList of strings as it was much easier to
//    work with an arraylist the no of universities in the text file which will be scanned is not
//    predefined.
//
//    The throws clause lists the checked exceptions (IOException,NoSuchElementException) that could
//    occur anytime during program execution, letting the main method handle any errors.
//
//    The parameters , which contain the serial numbers of the ranking factors and the weightage for
//    each one of them are used to calculate the overall score , which later helps the write file
//    method to rank the universities in order of user preference.
//
//
//
// 3. Why did you choose the method header for the write file method (e.g., return type,
//    parameters, throws clause)?
//
//     There is no return type of this method as it just writes to the file.
//
//     The throws clause lists the checked exception (IOException) that could occur anytime during
//     program execution, letting the main method handle any errors.
//

// 4. What are the biggest challenges you encountered:
//
//      How to scan the statistics of a particular university and store them in the same order as
//      they were given by the user.
//
// 3. What did you learn from this assignment:
//
//     I learnt that one can't set(replace) an element of the arraylist whose index is greater than
//     the (current size-1)
//
//     There is no pre-defined left trim or right trim in java
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class helps a person rank the top universities in the world for computer science according
 * to his/her ranking criteria. This class contains a program that asks the user for the factors
 * they want to include for their ranking and the weightage they want to allot to each factor.
 *
 * @author Aditya Goyal
 */

public class H12CustomApp {



    private static void printFactors(String[] rankingFactors){
        for(int i =0;i<rankingFactors.length;i++){
            System.out.println( "" + (i+1) + " " + rankingFactors[i]);
        }
    }

    private static boolean existorNot(String factor,ArrayList<String> factors){
        for(int i =0;i< factors.size();i++){
            if(factor.equals(factors.get(i))){
                return false;
            }
        }
        return true;
    }

    private static boolean existorNot(int num,ArrayList<Integer> factors){
        for(int i =0;i< factors.size();i++){
            if(num==factors.get(i)){
                return false;
            }
        }
       return true;
    }

    /**
     * This method reads in valid data from the text file (given as the parameter). It reads in the
     * university's name, country and stats pertaining to the user-chosen ranking factor. It then
     * calculates the overall score of each university, given the specific ranking factors and the
     * weightage.
     *
     * @param fileName       It is the name of the file that contains the list of universities and
     *                       their statistics on academic reputation, H-Index, etc.
     * @param Factors It is an arrayList which consists of the valid serial numbers
     *                       (corresponding to the ranking factor) that the user gave as input.
     * @param weightage      It is an ArrayList which is a parallel ArrayList to rankingFactors
     *                       arrayList. It contains the weightage of the ranking factors that were
     *                       provided by the user.
     * @return The program returns an ArrayList of ArrayList of Strings. Each element in
     * the arraylist contains an ArrayList of strings which contains a university's name,
     * location and statistics.
     * @throws IOException            if there is any problem cosing the InputStream or the file to
     *                                be scanned from does not exist.
     * @throws NoSuchElementException when there is incomplete input in the file or
     *                                there in no input to scan from in the first place.
     */


    public static ArrayList<ArrayList<String>> readUniData(String fileName, ArrayList<Integer>
            Factors, ArrayList<Double> weightage,int size) throws IOException,NoSuchElementException,Exception {

        IOException IOException = new IOException();
        FileNotFoundException FileNotFoundException = new FileNotFoundException();
        NoSuchElementException NoSuchElementException = new NoSuchElementException();
        ArrayList<ArrayList<String>> cumulativeUniData = new ArrayList<>();
        ArrayList<String> singleUniData = new ArrayList<>();

        FileInputStream uniList = null;
        Scanner input = null;

        int j;
        String nextInput;
        double value;


        try {
            uniList = new FileInputStream(fileName);
            input = new Scanner(uniList);

            while (true) {
                String universityName = "";
                String country = "";

                if (input.hasNext()) {
                    nextInput = input.next().trim();

                    try{
                        Double.parseDouble(nextInput);
                        throw new Exception("Data not formatted correctly or...\nThe no of ranking factors in the dataset are more than mentioned\n");
                    }

                    catch(NumberFormatException e){
                        // continue. If I put continue, it will go to the next iteration and The next few statements will be unreachable.
                    }


                } else {
                    throw NoSuchElementException;
                }

                while (!(nextInput.equals(";"))) {

                    universityName += nextInput + " ";

                    if (input.hasNext()) {
                        nextInput = input.next().trim();
                    } else {
                        System.out.println("6");
                        throw NoSuchElementException;
                    }
                }

                universityName = universityName.substring(0, universityName.length() - 1);
                singleUniData.add(universityName);

                if (input.hasNext()) {
                    nextInput = input.next().trim();

                   try{
                        Double.parseDouble(nextInput);
                        throw new Exception("\nData not formatted correctly or...\nThe no of ranking factors in the dataset are more than mentioned\n");
                    }

                    catch(NumberFormatException e){
                       // continue. If I put continue, it will go to the next iteration and The next few statements will be unreachable.
                    }


                } else {
                    throw NoSuchElementException;
                }


                while (!(nextInput.equals(";"))) {

                    country += nextInput + " ";

                    if (input.hasNext()) {
                        nextInput = input.next().trim();
                    } else {
                        System.out.println("2");
                        throw NoSuchElementException;
                    }
                }

                country = country.substring(0, country.length() - 1);
                singleUniData.add(country);


                for (int i = 0; i < size; i++) {

                    for (j = 0; j < Factors.size(); j++) {

                        if (i + 1 == Factors.get(j)) {
                            break;
                        }
                    }

                    if (j == Factors.size()) {
                        input.next();
                        continue;
                    }


                    if (input.hasNext()) {

                        while (true) {

                            if (input.hasNextDouble()) {

                                value = input.nextDouble();

                                if ((value > 100.00) || (value < 0)) {
                                    continue;
                                } else {
                                    for (int t = 0; t <= (j + 1 - (singleUniData.size() - 1));
                                         t++) {
                                        singleUniData.add("0");
                                    }
                                    singleUniData.set(j + 2, Double.toString(value).trim());
                                    break;
                                }
                            } else {
                                if (input.hasNext()) {
                                    throw new Exception("Data not formatted correctly");
                                    //input.next();
                                    //i--;
                                    //continue;
                                } else {
                                    System.out.println("3");
                                    throw NoSuchElementException;
                                }
                            }
                        }
                    } else {
                        System.out.println("4");
                        throw NoSuchElementException;
                    }
                }


                double score = 0.0;
                for (int i = 2; i < singleUniData.size(); i++) {
                    score += Double.parseDouble(singleUniData.get(i)) *
                            (weightage.get(i - 2) / 100.00);
                }
                singleUniData.add(Double.toString(score).trim());

                cumulativeUniData.add(singleUniData);

                singleUniData = new ArrayList<>();

                if (input.hasNext()) {
                    continue;
                } else {
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            throw FileNotFoundException;
        } finally {
            if (input != null) {
                input.close();
            }

            if (uniList != null) {
                uniList.close();
            }
        }
        return cumulativeUniData;
    }

    /**
     * This method contains a program that outputs the universities, ranked from highest to lowest
     * (on the overall score). It outputs the name, country, and overall score for each university.
     *
     * @param fileName          It is the name of the file to which data is to be written to.
     * @param cumulativeUniData It is an ArrayList of ArrayList of Strings. Each element in
     *                          the arraylist contains an ArrayList of strings which contains the
     *                          university's name,location and statistics.
     * @throws IOException when there is an error in closing the output stream or there in no input
     *                     to scan from in the first place.
     */

    public static void writeRankedUnis(String fileName,
                                       ArrayList<ArrayList<String>> cumulativeUniData)
            throws IOException {

        IOException IOException = new IOException();
        FileNotFoundException FileNotFoundException = new FileNotFoundException();


        int arraySize = cumulativeUniData.get(0).size();
        int times = cumulativeUniData.size();
        FileOutputStream uniList = null;
        PrintWriter writer = null;

        try {

            uniList = new FileOutputStream(fileName);
            writer = new PrintWriter(uniList);
            writer.println("Here are the top 30 universities for Computer Science ranked " +
                    "according to your criteria\n\n");


            for (int i = 0; i < times; i++) {
                double max = 0.0;
                int indexOfMax = 0;
                int j;

                for (j = 0; j < cumulativeUniData.size(); j++) {
                    if (Double.parseDouble(cumulativeUniData.get(j).get(arraySize - 1)) >= max) {
                        max = Double.parseDouble(cumulativeUniData.get(j).get(arraySize - 1));
                        indexOfMax = j;
                    }
                }

                writer.println("Rank " + (i + 1) + "-: ");
                writer.println("Name: " + cumulativeUniData.get(indexOfMax).get(0) + " ");
                writer.println("Country: " + cumulativeUniData.get(indexOfMax).get(1) + " ");
                writer.printf("Overall score: %.3f\n\n", max);
                cumulativeUniData.remove(indexOfMax);
            }
        } catch (FileNotFoundException e) {
            throw FileNotFoundException;
        } finally {
            if (writer != null) {
                writer.close();
            }

            if (uniList != null) {
                uniList.close();
            }
        }


    }

    /**
     * This method is used to safely take in valid user input like the ranking factors and weightage
     * It is used to call methods and handle errors which are thrown from those methods.
     *
     * @param args unused
     */
    public static void main(String[] args) throws Exception {

        ArrayList<String> rankingFactors1 = new ArrayList<String>();


        int number = 0 ;
        Scanner scnr = new Scanner(System.in);


        while(true){
            System.out.println("How many ranking factors are present in the given dataSet");
            if(scnr.hasNextInt()){
                number = scnr.nextInt();
                if(number<=0){
                    System.out.println("Pls input a valid number\n");
                }
                else{
                    break;
                }
            }

            else{
                if(scnr.hasNextDouble()){
                    scnr.next();
                    System.out.println("Pls input an integer number\n");
                }

                else{
                    if(scnr.hasNext()){
                        scnr.next();
                        System.out.println("Pls input a valid value\n");
                    }
                }
            }
        }

        System.out.println("\nWhat ranking factors are present in the given dataSet?");
        System.out.println("Pls provide the factors in order of the statistics given");


        String input = "";

        for(int i =0;i<number;i++) {
            System.out.print("\nPls input ranking factor " + (i + 1) + ": ");
            String rankingFactor = "";

            while (true) {

                    if (i == 0) {
                        scnr.nextLine();
                    }

                    while(true) {
                        if (scnr.hasNextLine()) {
                            String element = scnr.nextLine().trim();

                            if ( (!(element.equals(""))) && (existorNot(element,rankingFactors1))) {
                                rankingFactor += element;
                                rankingFactors1.add(rankingFactor) ;
                                break;
                            } else {

                                if(element.equals("")){
                                    System.out.println("Pls provide a valid value");
                                }

                                else {
                                    System.out.println("Pls provide a ranking factor that has not been given previously");
                                }
                            }
                        }
                    }
                    break;
            }
        }

        System.out.println(rankingFactors1.toString());   // how to print an arrayList


// scnr.hasNext() returns false when "\n" is there.
// scnr.hasNextLine() returns true when "\n" is there.


       String[] rankingFactors = new String[rankingFactors1.size()];

       // String[] rankingFactors = {"Academic Reputation", "Employer reputation", "Citations",
       //"H-Index", "International student diversity"};

        for(int i =0;i<rankingFactors1.size();i++){
            rankingFactors[i]=rankingFactors1.get(i);
        }

        String readFileName = null;
        String writeFileName = null;
        ArrayList<Integer> userFactors = new ArrayList<>();
        ArrayList<Double> weightageOfFactors = new ArrayList<>();




        System.out.println("What factors do you want to base your decision on? " +
                "(pls select the serial no of the factors you want to choose) ");

        System.out.println("Type \"done\" when finished listing out the factors");


        printFactors(rankingFactors);

        /*System.out.println("1 Academic Reputation");
        System.out.println("2 Employer reputation");
        System.out.println("3 Citations");
        System.out.println("4 H-Index");
        System.out.println("5 International student diversity\n");
        */



        while (true) {
            int serNum = 0;
            try {
                if (scnr.hasNext()) {
                    serNum = scnr.nextInt();
                } else {
                    return;
                }
            } catch (InputMismatchException e) {
                if (scnr.next().equals("done")) {
                    break;
                } else {
                    System.out.println("Serial number should be an integer. Try again! ");
                    continue;
                }
            } catch (NoSuchElementException e) {
                return;
            }


            if ((serNum > 0) && (serNum <= rankingFactors.length )&&(existorNot(serNum,userFactors))) {
                userFactors.add(serNum);
            } else {

                if(!((serNum > 0) && (serNum <= rankingFactors.length))){
                    System.out.println("Serial number is out of bounds. Try again! ");
                }

                if(!(existorNot(serNum,userFactors))) {
                    System.out.println("Serial number already exists");
                }

            }
        }




        double unaccountedWeightage = 100.00;
        double weightage = 0;

        for (int i = 0; i < userFactors.size(); i++) {

            while (true) {
                if (i != userFactors.size() - 1) {
                    System.out.println("\n" + "Weightage of " +
                            rankingFactors[userFactors.get(i) - 1] + ": ");
                    if (scnr.hasNextDouble()) {
                        weightage = scnr.nextDouble();
                        if ((Math.abs(weightage - unaccountedWeightage) < 0.01) ||
                                (weightage > unaccountedWeightage) || (weightage < 0.0)) {
                            System.out.println("Weightage is invalid");
                        } else {
                            weightageOfFactors.add(weightage);
                            break;
                        }
                    } else {
                        if (scnr.hasNext()) {
                            System.out.println("Weightage has to be a number");
                            scnr.next();
                        } else {
                            return;
                        }
                    }
                } else {
                    System.out.println("\n" + "Weightage of " +
                            rankingFactors[userFactors.get(i) - 1] + " is " + unaccountedWeightage);
                    weightageOfFactors.add(unaccountedWeightage);
                    break;
                }
            }

            unaccountedWeightage -= weightage;
        }




        while (true) {
            System.out.println("\n" + "What is the name of the text file you are reading from? ");
            if (scnr.hasNext()) {
                readFileName = scnr.next().trim();
            } else {
                return;
            }


            System.out.println("\n" + "What is the name of the text file you are writing to? ");
            if (scnr.hasNext()) {
                writeFileName = scnr.next().trim();
            } else {
                return;
            }


            try {
                writeRankedUnis(writeFileName, readUniData(readFileName, userFactors,
                        weightageOfFactors,rankingFactors1.size() ));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Pls input valid text files");
                return;
            } catch (IOException e) {
                System.out.println("Unable to read the file");
                return;
            }

            catch (NoSuchElementException e) {
                System.out.println("No more input to scan");
                return;
            }

            catch(Exception e){
                System.out.println(e.getMessage());
                return;
                //System.out.print(e.printStackTrace());
            }
        }
    }
}


// InputMismatchException is a type of NoSuchElementException