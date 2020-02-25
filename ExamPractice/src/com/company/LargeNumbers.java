// Exam Practice - Adding String Numbers
// Tahseen Ahmed
// Tuesday, December 17th, 2019
// Uses Longs for adding, which are
// pretty large numbers.
// =====================================
package com.company;
import java.util.Scanner;

public class LargeNumbers {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Initialize keyboard scanning for input.
        String givenInput[] = input.nextLine().split(" ");
        String firstValue = givenInput[0];
        String secondValue = givenInput[1];

        System.out.println(Long.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(addLargeNumbers(firstValue, secondValue));

    }

    private static String addLargeNumbers( String x, String y) {
        return Long.toString(Long.parseLong(x) + Long.parseLong(y));
    }

}

