///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Test cases for H10 Custom App
// Course:          CS 200, Fall 2023
//
// Author:          Aditya Goyal
// Email:           agoyal33@wisc.edu
// Lecturer's Name: Prof Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//No citation
//
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This contains testing methods for the H10CustomApp class.
 * @author Aditya Goyal
 */
public class TestCases {

    /**
     * This calls the testH10CustomApp method and prints out the result.
     * @param args command-line arguments (unused)
     */
    public static void main(String []args) {
        System.out.println("Success: " + testH10CustomApp());
    }

    /**
     * This has various test cases that call the app methods to verify they work
     * according to the descriptions in the app method header comments.  If all the
     * test cases pass, then true is returned, otherwise false. This method is called
     * by a zyBooks test. Note the latest, highest scoring submission will be the one
     * that is human graded.
     *
     * @return true when all test cases pass, false otherwise.
     */

    // include an English description of each specific test case immediately before it.

    public static boolean testH10CustomApp() {
        boolean error = false;


// checks whether the print method works by testing with borderline cases.
        {
            String[] termsOfEquations1 ={"lnx","COTX"};
            String[] powerOfTerms1 ={"-0.13738","2.0"};
            String[] coefficientOfTerms1={"16.78973","-0.729293"};
            String[] otherTerm1 ={"(1/x)","(-(cosecx)^2)"};

            ArrayList<String> termsOfEquations = new ArrayList<>();
            termsOfEquations.addAll(Arrays.asList(termsOfEquations1));

            ArrayList<String> powerOfTerms = new ArrayList<>();
            powerOfTerms.addAll(Arrays.asList(powerOfTerms1));

            ArrayList<String> coefficientOfTerms = new ArrayList<>();
            coefficientOfTerms.addAll(Arrays.asList(coefficientOfTerms1));

            ArrayList<String> otherTerm = new ArrayList<>();
            otherTerm.addAll(Arrays.asList(otherTerm1));

            ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();

            test.add(coefficientOfTerms);
            test.add(termsOfEquations);
            test.add(powerOfTerms);
            test.add(otherTerm);


            String expected  = "(16.79(lnx)^-0.14)*(1/x) + (-0.73(cotx)^2.00)*(-(cosecx)^2)";
            String actual = DifferentiationCalculator.print(test);
            if (!(actual.equals(expected))) {
                error = true;
                System.out.println(
                        "exampleSum 1) Expected: " + expected + " actual: " + actual);
            }
        }





// checks whether the print method works by testing with one usual and one borderline case.
        {
            String[] termsOfEquations1 ={"X","C"};
            String[] powerOfTerms1 ={"100000.00","0.00"};
            String[] coefficientOfTerms1={"1000000.000","0.000"};
            String[] otherTerm1 ={"1",""};

            ArrayList<String> termsOfEquations = new ArrayList<>();
            termsOfEquations.addAll(Arrays.asList(termsOfEquations1));

            ArrayList<String> powerOfTerms = new ArrayList<>();
            powerOfTerms.addAll(Arrays.asList(powerOfTerms1));

            ArrayList<String> coefficientOfTerms = new ArrayList<>();
            coefficientOfTerms.addAll(Arrays.asList(coefficientOfTerms1));

            ArrayList<String> otherTerm = new ArrayList<>();
            otherTerm.addAll(Arrays.asList(otherTerm1));

            ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();

            test.add(coefficientOfTerms);
            test.add(termsOfEquations);
            test.add(powerOfTerms);
            test.add(otherTerm);


            String expected  = "(1000000.00(x)^100000.00)*1 + 0";
            String actual = DifferentiationCalculator.print(test);
            if (!(actual.equals(expected))) {
                error = true;
                System.out.println(
                        "exampleSum 2) Expected: " + expected + " actual: " + actual);
            }
        }






// checks whether the differentiate method works by testing with borderline cases.
        {
            String[] termsOfEquations1 ={"LnX","C"};
            Double[] powerOfTerms1 ={-3.0,0.0};
            Double[] coefficientOfTerms1={2.0,0.0};

            ArrayList<String> termsOfEquations = new ArrayList<>();
            termsOfEquations.addAll(Arrays.asList(termsOfEquations1));

            ArrayList<Double> powerOfTerms = new ArrayList<>();
            powerOfTerms.addAll(Arrays.asList(powerOfTerms1));

            ArrayList<Double> coefficientOfTerms = new ArrayList<>();
            coefficientOfTerms.addAll(Arrays.asList(coefficientOfTerms1));




            ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();

            String[] termsOfEquationsExpected1 ={"LnX","C"};
            String[] powerOfTermsExpected1 ={"-4.0","0.0"};
            String[] coefficientOfTermsExpected1={"-6.0","0.0"};
            String[] otherTermsExpected1={"(1/x)",""};

            ArrayList<String> termsOfEquationsExpected = new ArrayList<>();
            termsOfEquationsExpected.addAll(Arrays.asList(termsOfEquationsExpected1));

            ArrayList<String> powerOfTermsExpected = new ArrayList<>();
            powerOfTermsExpected.addAll(Arrays.asList(powerOfTermsExpected1));

            ArrayList<String> coefficientOfTermsExpected = new ArrayList<>();
            coefficientOfTermsExpected.addAll(Arrays.asList(coefficientOfTermsExpected1));

            ArrayList<String> otherTermsExpected = new ArrayList<>();
            otherTermsExpected.addAll(Arrays.asList(otherTermsExpected1));

            expected.add(coefficientOfTermsExpected);
            expected.add(termsOfEquationsExpected);
            expected.add(powerOfTermsExpected);
            expected.add(otherTermsExpected);




            ArrayList<ArrayList<String>> actual = DifferentiationCalculator.differentiate
                    (termsOfEquations,powerOfTerms,coefficientOfTerms);


            if (!(actual.equals(expected))) {
                error = true;
                System.out.println(
                        "exampleSum 3) Expected: " + expected + " actual: " + actual);
            }

        }




// checks whether the differentiate method works by testing with borderline cases.
        {
            String[] termsOfEquations1 ={"sEcX","X"};
            Double[] powerOfTerms1 ={-789.1,0.05};
            Double[] coefficientOfTerms1={56.1,-0.08};

            ArrayList<String> termsOfEquations = new ArrayList<>();
            termsOfEquations.addAll(Arrays.asList(termsOfEquations1));

            ArrayList<Double> powerOfTerms = new ArrayList<>();
            powerOfTerms.addAll(Arrays.asList(powerOfTerms1));

            ArrayList<Double> coefficientOfTerms = new ArrayList<>();
            coefficientOfTerms.addAll(Arrays.asList(coefficientOfTerms1));




            ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();

            String[] termsOfEquationsExpected1 ={"sEcX","X"};
            String[] powerOfTermsExpected1 ={"-790.1","-0.95"};
            String[] coefficientOfTermsExpected1={"-44268.51","-0.004"};
            String[] otherTermsExpected1={"(secx*tanx)","1"};

            ArrayList<String> termsOfEquationsExpected = new ArrayList<>();
            termsOfEquationsExpected.addAll(Arrays.asList(termsOfEquationsExpected1));

            ArrayList<String> powerOfTermsExpected = new ArrayList<>();
            powerOfTermsExpected.addAll(Arrays.asList(powerOfTermsExpected1));

            ArrayList<String> coefficientOfTermsExpected = new ArrayList<>();
            coefficientOfTermsExpected.addAll(Arrays.asList(coefficientOfTermsExpected1));

            ArrayList<String> otherTermsExpected = new ArrayList<>();
            otherTermsExpected.addAll(Arrays.asList(otherTermsExpected1));

            expected.add(coefficientOfTermsExpected);
            expected.add(termsOfEquationsExpected);
            expected.add(powerOfTermsExpected);
            expected.add(otherTermsExpected);


            ArrayList<ArrayList<String>> actual = DifferentiationCalculator.differentiate
                    (termsOfEquations,powerOfTerms,coefficientOfTerms);

            if (!(actual.equals(expected))) {
                error = true;
                System.out.println(
                        "exampleSum 4) Expected: " + expected + " actual: " + actual);
            }

        }



        if ( error) {
            System.out.println("Error(s) in test cases.");
        } else {
            System.out.println("All test cases passed.");
        }
        return !error;
    }
}
