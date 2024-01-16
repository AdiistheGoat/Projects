import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the test bench that contains testing methods for the H12CustomApp class.
 * The createTestDataFile and readTestDataFile are private testing methods intended to
 * be used within the test cases.
 *
 * @author Jim Williams
 * @author Aditya Goyal
 */
public class TestH12CustomApp {

    /**
     * This method runs the selected tests.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testH12CustomApp();
    }

    /**
     * This is a testing method to create a file with the specified name and fileContents
     * to be used by other testing methods. On a FileNotFoundException a stack trace is printed and
     * then returns.
     *
     * @param testDataFilename The filename of the testing file to create.
     * @param fileContents     The data to put into the file.
     */
    private static void createTestDataFile(String testDataFilename, String fileContents) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(testDataFilename);
            writer.print(fileContents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    /**
     * This is a testing method to read and return the entire contents of the specified file to
     * be used solely by other testing methods.
     * On a FileNotFoundException a stack trace is printed and then "" returned.
     *
     * @param dataFilename The name of the file to read.
     * @return The contents of the file or "" on error.
     */
    private static String readTestDataFile(String dataFilename) {
        File file = new File(dataFilename);
        Scanner input = null;
        String contents = "";
        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                contents += input.nextLine() + "\n"; //assuming all lines end with newline
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) input.close();
        }
        return contents;
    }

    /**
     * Tests that the H12CustomApp read input and write output methods handle
     * the cases described in their method header comments.
     *
     * @return true for passing all testcases, false otherwise
     */
    public static boolean testH12CustomApp() {
        boolean error = false;


        {  //tests that a file with a small number of university's information can be read.
            //create a data file to be read by the read method.

            int size = 5;

            String fileToRead = "testRead.txt";
            String fileContents = "\tMassachusetts Institute of Technology (MIT)  ;\tUnited" +
                    " States ;\t91\t98.2\t93.7\t97.4\t95.6\n" + "\tCarnegie Mellon University ;" +
                    " United States ;\t100\t 82.3\t95.3\t98.8\t90.2\n" + "\tStanford University" +
                    " ; United States\t; 88.5\t96.3\t99.9\t100 \t74.8\n";

            createTestDataFile(fileToRead, fileContents);

            ArrayList<ArrayList<String>> expectedContents = new ArrayList<>();
            ArrayList<String> firstUniData = new ArrayList<>();
            ArrayList<String> secondUniData = new ArrayList<>();
            ArrayList<String> thirdUniData = new ArrayList<>();

            ArrayList<Double> weightage = new ArrayList<>();
            ArrayList<Integer> rankingFactors = new ArrayList<>();

            firstUniData.add("Massachusetts Institute of Technology (MIT)");
            firstUniData.add("United States");
            firstUniData.add("93.7");
            firstUniData.add("91.0");
            firstUniData.add("97.4");
            firstUniData.add("94.07114");


            secondUniData.add("Carnegie Mellon University");
            secondUniData.add("United States");
            secondUniData.add("95.3");
            secondUniData.add("100.0");
            secondUniData.add("98.8");
            secondUniData.add("98.5527");


            thirdUniData.add("Stanford University");
            thirdUniData.add("United States");
            thirdUniData.add("99.9");
            thirdUniData.add("88.5");
            thirdUniData.add("100.0");
            thirdUniData.add("95.37922");


            expectedContents.add(firstUniData);
            expectedContents.add(secondUniData);
            expectedContents.add(thirdUniData);

            weightage.add(20.78);
            weightage.add(40.0);
            weightage.add(39.22);

            rankingFactors.add(3);
            rankingFactors.add(1);
            rankingFactors.add(4);


            //now read the file using the H12CustomApp read method that we are testing
            ArrayList<ArrayList<String>> actualContents = null;

            try {
                actualContents = H12CustomApp.readUniData(fileToRead, rankingFactors, weightage,5);
            } catch (FileNotFoundException e) {
                System.out.print("Pls input valid text files in the test case 1");
            } catch (IOException e) {
                System.out.println("Unable to read the file in the test case 1");
            } catch (NoSuchElementException e) {
                System.out.println("No more input to scan in the file in the test case 1");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }


            //check if the contents are as described by the read method header comment
            if (!actualContents.equals(expectedContents)) {
                error = true;
                System.out.println("readFile 1) expected:" + expectedContents
                        + " actual: " + actualContents);
            } else {
                System.out.println("readFile 1) success");
                //since the test succeeded, remove the temporary file created with our testing
                // method
                File file = new File(fileToRead);
                file.delete();
            }
        }


        { //test that an invalid file throws the appropriate error and eventually returns "Pls input
            // valid text files in the test case 2" .


            int size = 5;

            ArrayList<Double> weightage = new ArrayList<>();
            ArrayList<Integer> rankingFactors = new ArrayList<>();

            weightage.add(34.99);
            weightage.add(40.0);
            weightage.add(25.01);

            rankingFactors.add(3);
            rankingFactors.add(1);
            rankingFactors.add(4);

            //make sure the file doesn't exist by deleting it if it does.
            String fileToRead = "fileThatShouldNotExist";
            File file = new File(fileToRead);
            if (file.exists()) {
                file.delete();
            }

            String actualContents = "";

            String expectedContents = "Pls input valid text files in the test case 2";
            //now try to read the file using the H12CustomApp method we are testing
            try {
                H12CustomApp.readUniData(fileToRead, rankingFactors, weightage,5);
            } catch (FileNotFoundException e) {
                actualContents = "Pls input valid text files in the test case 2";
            } catch (IOException e) {
                actualContents = "Unable to read the file in the test case 2";
            } catch (NoSuchElementException e) {
                actualContents = "No more input to scan in the file in the test case 2";
            }
            catch(Exception e){
                System.out.print(e.getMessage());
            }


            //check if the contents are as described in the H12CustomApp.readFile method header
            if (!actualContents.equals(expectedContents)) {
                error = true;
                System.out.println("readFile 2) expected:" + expectedContents
                        + " actual: " + actualContents);
            } else {
                System.out.println("readFile 2) success");
            }
        }


        { //tests that the contents are correctly written to the specified file.
            //use our write file method to write some data to a file.

            ArrayList<ArrayList<String>> testArrayList = new ArrayList<>();
            ArrayList<String> firstUniData = new ArrayList<>();
            ArrayList<String> secondUniData = new ArrayList<>();
            ArrayList<String> thirdUniData = new ArrayList<>();

            firstUniData.add("Massachusetts Institute of Technology (MIT)");
            firstUniData.add("United States");
            firstUniData.add("91");
            firstUniData.add("98.2");
            firstUniData.add("95.320");

            secondUniData.add("Carnegie Mellon University");
            secondUniData.add("United States");
            secondUniData.add("100");
            secondUniData.add("82.3");
            secondUniData.add("89.380");

            thirdUniData.add("Stanford University");
            thirdUniData.add("United States");
            thirdUniData.add("88.5");
            thirdUniData.add("96.3");
            thirdUniData.add("93.180");

            testArrayList.add(firstUniData);
            testArrayList.add(secondUniData);
            testArrayList.add(thirdUniData);

            String fileNameToWrite = "testWrite.txt";

            try {
                H12CustomApp.writeRankedUnis(fileNameToWrite, testArrayList);
            } catch (FileNotFoundException e) {
                System.out.println("Pls input valid text files in the test case 3");
            } catch (IOException e) {
                System.out.println("Unable to read the file in the test case 3");
            }

            //use the testing method to read the file
            String expectedContents = "Here are the top 30 universities for Computer Science" +
                    " ranked according to your criteria\n" +
                    "\n" +
                    "\n" +
                    "Rank 1-: \n" +
                    "Name: Massachusetts Institute of Technology (MIT) \n" +
                    "Country: United States \n" +
                    "Overall score: 95.320\n" +
                    "\n" +
                    "Rank 2-: \n" +
                    "Name: Stanford University \n" +
                    "Country: United States \n" +
                    "Overall score: 93.180\n" +
                    "\n" +
                    "Rank 3-: \n" +
                    "Name: Carnegie Mellon University \n" +
                    "Country: United States \n" +
                    "Overall score: 89.380\n\n";

            String actualContents = readTestDataFile(fileNameToWrite);

            //check if the contents are the same
            if (!actualContents.equals(expectedContents)) {
                error = true;
                System.out.println("writeFile 3) expected: " + expectedContents
                        + " actual: " + actualContents);
            } else {
                System.out.println("writeFile 3) success");
                //since the test succeeded, remove the temporary testing file.
                File file = new File(fileNameToWrite);
                file.delete();
            }
        }


        { //test that an invalid file throws the appropriate error and eventually returns "Pls input
            // valid text files in the test case 5".

            String fileNameToWrite = "missingDirectory/fileThatShouldNotExist";
            String expectedContent = "Pls input valid text files in the test case 5";
            String actualContent = "";

            //check that the directory doesn't exist, since we want writeFile to handle the
            // exception when it tries to write the file to that non-existing directory.
            File file = new File(fileNameToWrite);
            if (file.getParentFile().exists()) {
                error = true;
                System.out.println("writeFile 4) The directory: " + file.getParentFile().getName()
                        + " should not exist for this test to run correctly.");
            } else {
                System.out.println("writeFile 4) success");
            }

            ArrayList<ArrayList<String>> testArrayList = new ArrayList<>();
            ArrayList<String> firstUniData = new ArrayList<>();
            ArrayList<String> secondUniData = new ArrayList<>();
            ArrayList<String> thirdUniData = new ArrayList<>();

            firstUniData.add("Massachusetts Institute of Technology (MIT)");
            firstUniData.add("United States");
            firstUniData.add("91");
            firstUniData.add("98.2");
            firstUniData.add("93.7");

            secondUniData.add("Carnegie Mellon University");
            secondUniData.add("United States");
            secondUniData.add("100");
            secondUniData.add("82.3");
            secondUniData.add("95.3");

            thirdUniData.add("Stanford University");
            thirdUniData.add("United States");
            thirdUniData.add("88.5");
            thirdUniData.add("96.3");
            thirdUniData.add("99.9");

            testArrayList.add(firstUniData);
            testArrayList.add(secondUniData);
            testArrayList.add(thirdUniData);

            //now try to write some actual data to the file
            try {
                H12CustomApp.writeRankedUnis(fileNameToWrite, testArrayList);
            } catch (FileNotFoundException e) {
                actualContent = "Pls input valid text files in the test case 5";
            } catch (IOException e) {
                actualContent = "Unable to read the file in the test case 5";
            }


            if (actualContent != expectedContent) {
                error = true;
                System.out.println("writeFile 5) expected:" + expectedContent
                        + " actual: " + actualContent);
            } else {
                System.out.println("writeFile 5) success");
            }
        }


        if (error) {
            System.out.println("\nTestH12CustomApp failed");
            return false;
        } else {
            System.out.println("\nTestH12CustomApp passed");
            System.out.println("There may be output from the methods being tested.");
            return true;
        }
    }
}
