// Salary Calculator
// Tahseen Ahmed - Thursday, September 12th, 2019
// Description: A simple calculator which computes the total payout of a person's work week with arbitrary input of variables.

package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class SophisticatedSalary {

    public static void main(String[]args){

        Scanner keyboard = new Scanner(System.in); // Initialize keyboard scanning for input.
        String givenInput = keyboard.nextLine();

        String [] inputList = givenInput.split( " ");
        Arrays.sort(inputList); // Sort the list so it is no longer in arbitrary order. Will order by D,T,d,t for easy access of values.

        //  String[] workingHours = givenInput.split("T=[^\S]*"); <- A failed attempt at using regex and string splitting to extract integers from the arbitrary values.

        // New t, d, D and T will be extracted from the sorted list by use of string manipulation.
        int workingHours = Integer.parseInt(inputList[3].substring(2));  // Regular working hours
        int normalRate = Integer.parseInt(inputList[2].substring(2)); // The regular rate pay
        int otRate = Integer.parseInt(inputList[0].substring(2)); // The rate for overtime hours
        int totalHours = Integer.parseInt(inputList[1].substring(2)); // The total hours worked in a given week
        int computedSalary = 0; // Used to compute number of asterisks in display, also created for readability

        if (workingHours < 0 | normalRate < 0 | otRate < 0 | totalHours < 0){ // Sanity check for negative numbers.
            System.out.println("Error! Negative numbers not permitted for this calculation!");

         } else if (totalHours > workingHours){ // Overtime calculation
            computedSalary = ((totalHours - workingHours) * otRate) + (workingHours * normalRate);

            System.out.println("*".repeat(String.valueOf(computedSalary).length() + 8)); // The number of asterisks is the length of the number plus the constant 8 for " Dollars"
            System.out.println( computedSalary + " Dollars");
            System.out.println( "*".repeat(String.valueOf(computedSalary).length() + 8));

        }
        else { // No overtime calculation
            computedSalary = totalHours * normalRate;
            System.out.println( "*".repeat(String.valueOf(computedSalary).length() + 8));
            System.out.println(computedSalary + " Dollars");
            System.out.println( "*".repeat(String.valueOf(computedSalary).length() + 8));
        }

        // System.out.println(givenInput);
        // System.out.println(inputList[0]);
        // System.out.println(inputList[1]);
        // System.out.println(inputList[2]);
        // System.out.println(inputList[3]);
        // System.out.print("Working Hours should be after");
        // System.out.println(workingHours);
    }


}



