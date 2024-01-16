import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class contains a program which safely takes in user inputs for an equation, differentiates
 * it and then outputs it in an appropriate format.
 * @author Aditya Goyal
 */

public class DifferentiationCalculator{

    /**
     * This method takes in the equations input by the user and converts them into a differentiated
     * format by handling the powers, coefficients and the part of the term to be differentiated,
     *
     * @param origTermsOfEquations This is an Arraylist containing all the original terms of the
     *                             equation that the user inputs.
     * @param powerOfTerms         This is an ArrayList containing all the exponential powers of
     *                             the terms of the equations that the user inputs.
     * @param coefficientOfTerms   This is an ArrayList containing all the coefficients of the terms
     *                             of the equations that the user inputs.
     * @return an ArrayList of 4 ArrayLists containing the power of terms after differentiation,
     * coefficient of terms after differentiation, original terms of the equations, and the original
     * terms of the equations after differentiation.
     */
    public static ArrayList<ArrayList<String>> differentiate(ArrayList<String> origTermsOfEquations
            , ArrayList<Double> powerOfTerms, ArrayList<Double> coefficientOfTerms) {

        ArrayList<String> origTermAfterDiff = new ArrayList<>();
        ArrayList<String> powerOfTermsAfterDiff = new ArrayList<>();
        ArrayList<String> coefficientOfTermsAfterDiff = new ArrayList<>();
        ArrayList<ArrayList<String>> cumulativeArrayLists = new ArrayList<ArrayList<String>>();


        for (Double power : powerOfTerms) {
            powerOfTermsAfterDiff.add(Double.toString(power));
        }


        for (Double coefficient : coefficientOfTerms) {
            coefficientOfTermsAfterDiff.add(Double.toString(coefficient));
        }


        for (int i = 0; i < origTermsOfEquations.size(); i++) {
            switch (origTermsOfEquations.get(i).toLowerCase()) {
                case "sinx":
                    origTermAfterDiff.add(i, "(cosx)");
                    break;

                case "cosx":
                    origTermAfterDiff.add(i, "(-sinx)");
                    break;

                case "tanx":
                    origTermAfterDiff.add(i, "((secx)^2)");
                    break;

                case "secx":
                    origTermAfterDiff.add(i, "(secx*tanx)");
                    break;

                case "lnx":
                    origTermAfterDiff.add(i, "(1/x)");
                    break;

                case "x":
                    origTermAfterDiff.add(i, "1");
                    break;

                case "cotx":
                    origTermAfterDiff.add(i, "(-(cosecx)^2)");
                    break;

                case "cosecx":
                    origTermAfterDiff.add(i, "(-cosecx*cotx)");
                    break;

                case "c":
                    origTermAfterDiff.add(i, "");
                    break;
            }

        }


        for (int i = 0; i < coefficientOfTermsAfterDiff.size(); i++) {
            if (!(Math.abs(Double.valueOf(coefficientOfTermsAfterDiff.get(i)) - 0.0) < 0.001)) {
                coefficientOfTermsAfterDiff.set(i, String.valueOf(Double.valueOf
                        (powerOfTermsAfterDiff.get(i)
                        ) * Double.valueOf(coefficientOfTermsAfterDiff.get(i))));
            }

            if (!(Math.abs(Double.valueOf(powerOfTermsAfterDiff.get(i)) - 0.0) < 0.001)) {
                powerOfTermsAfterDiff.set(i, String.valueOf(Double.valueOf
                        (powerOfTermsAfterDiff.get(i)) - 1.0))
                ;
            }

        }

        cumulativeArrayLists.add(coefficientOfTermsAfterDiff);
        cumulativeArrayLists.add(origTermsOfEquations);
        cumulativeArrayLists.add(powerOfTermsAfterDiff);
        cumulativeArrayLists.add(origTermAfterDiff);

        return cumulativeArrayLists;
    }

    /**
     * This method outputs the differentiated equation in an appropriate format.
     *
     * @param cumulative an ArrayList of 4 ArrayLists containing the power of terms after
     *                   differentiation,coefficient of terms after differentiation,
     *                   original terms of the equations, and the original terms of the equations
     *                   after differentiation.
     * @return a String which contains the equation after differentiation.
     */
    public static String print(ArrayList<ArrayList<String>> cumulative) {
        String str = "";
        for (int i = 0; i < cumulative.get(0).size(); i++) {


            if (!(String.format("%.2f", Double.parseDouble(cumulative.get(0).get(i))).equals("0.00")
            )) {

                if (i != cumulative.get(0).size() - 1) {
                    if (String.format("%.2f", Double.parseDouble(cumulative.get(2).get(i))).equals
                            ("0.00")) {
                        str += String.format("%.2f", Double.parseDouble(cumulative.get(0).get(i))) +
                                cumulative.get(3).get(i) + " + ";
                    } else {
                        str += "(" + String.format("%.2f", Double.parseDouble(cumulative.get(0)
                                .get(i))) + "(" +
                                cumulative.get(1).get(i).toLowerCase() + ")" + "^" +
                                String.format("%.2f", Double.parseDouble(cumulative.get(2).get(i)))
                                + ")" + "*" + cumulative.get(3).get(i) +
                                " + ";
                    }

                } else {
                    if (String.format("%.2f", Double.parseDouble(cumulative.get(2).get(i))).equals
                            ("0.00")) {
                        str += String.format("%.2f", Double.parseDouble(cumulative.get(0).get(i))) +
                                cumulative.get(3).get(i);
                    } else {
                        str += "(" + String.format("%.2f", Double.parseDouble(cumulative.get(0)
                                .get(i))) + "(" +
                                cumulative.get(1).get(i).toLowerCase() + ")" + "^" +
                                String.format("%.2f", Double.parseDouble(cumulative.get(2).get(i)))
                                + ")" + "*" + cumulative.get(3).get(i);
                    }
                }
            } else {
                if (i == cumulative.get(0).size() - 1) {
                    str += "0";
                } else {
                    str += "0 + ";
                }
            }
        }
        return str;
    }

    /**
     * This method safely takes in valid inputs input from the user and calls other functions
     * appropriately.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        TestCases.testH10CustomApp();
        int noOfTerms;
        Scanner scnr = new Scanner(System.in);
        String[] possibleTerms = {"sinx","cosx","tanx","lnx","x","cotx","secx","cosecx","c"};
        ArrayList<String> origTermsOfEquations = new ArrayList<>();
        ArrayList<Double> powerOfTerms = new ArrayList<>();
        ArrayList<Double> coefficientOfTerms = new ArrayList<>();


        System.out.println("How many terms in the equation? ");
        while (true) {
            if (scnr.hasNextInt()) {
                noOfTerms = scnr.nextInt();
                if (noOfTerms > 0) {
                    break;
                }
            } else {
                if (scnr.hasNext()) {
                    scnr.next();
                } else {
                    return;
                }
            }

            System.out.println("Pls input a valid value");
        }


        System.out.println("");
        System.out.println("Input the term and the corresponding power");
        System.out.println("Pls input the following terms only: sinx, cosx, tanx, lnx, x, " +
                "cotx, secx, cosecx");
        System.out.println("Pls use c for constant \n");


        for (int i = 0; i < noOfTerms; i++) {
            boolean exist = false;
            String term;
            double power = 0;
            double coefficient = 0;


            while (true) {
                System.out.println("Term " + (i + 1) + ":");
                if (scnr.hasNext()) {
                    term = scnr.next();
                    for (int j = 0; j < possibleTerms.length; j++) {
                        if (term.toLowerCase().equals(possibleTerms[j])) {
                            exist = true;
                            break;
                        }
                    }

                    if (exist) {
                        break;
                    } else if (!exist) {
                        System.out.println("Pls input a valid value");
                        continue;
                    }
                } else {
                    return;
                }
            }


            if (!(term.toLowerCase().equals("c"))) {
                System.out.println("");
                System.out.println("Pls input only powers that are not equal to 0");
                while (true) {
                    System.out.print("Power of " + term + " : ");
                    if (scnr.hasNextDouble()) {
                        power = scnr.nextDouble();
                        if (!(Math.abs(power - 0.0) < 0.001)) {
                            break;
                        }
                    } else {
                        if (scnr.hasNext()) {
                            scnr.next();
                        } else {
                            return;
                        }
                    }

                    System.out.println("Pls input valid powers.");
                }

                System.out.println("");
                System.out.println("Pls input only coefficients that are not equal to 0");
                while (true) {
                    System.out.print("coefficient of " + term + " : ");
                    if (scnr.hasNextDouble()) {
                        coefficient = scnr.nextDouble();
                        if (!(Math.abs(coefficient - 0.0) < 0.001)) {
                            break;
                        }
                    } else {
                        if (scnr.hasNext()) {
                            scnr.next();
                        } else {
                            return;
                        }
                    }
                    System.out.println("Pls enter valid coefficient");
                }
            }

            origTermsOfEquations.add(term);
            powerOfTerms.add(power);
            coefficientOfTerms.add(coefficient);
            System.out.println("\n");

        }

        System.out.print(print(differentiate(origTermsOfEquations, powerOfTerms,
                coefficientOfTerms)))
        ;
    }
}





