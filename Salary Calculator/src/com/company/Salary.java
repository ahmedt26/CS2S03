// Salary Calculator
// Tahseen Ahmed - Wednesday, September 11th, 2019
// Description: A simple calculator which computes the total payout of a person's work week


package com.company;
import java.util.Scanner;


public class Salary {

    public static void main(String[]args){

        Scanner keyboard = new Scanner(System.in);

        int workingHours = keyboard.nextInt(); // Regular working hours
        int normalRate = keyboard.nextInt(); // The regular rate pay
        int otRate = keyboard.nextInt(); // The rate for overtime hours
        int totalHours = keyboard.nextInt(); // The total hours worked in a given week

        if (totalHours > workingHours){
            System.out.println(((totalHours - workingHours) * otRate) + (workingHours * normalRate));
        }
        else{
            System.out.println(totalHours * normalRate);
        }

    }


}




