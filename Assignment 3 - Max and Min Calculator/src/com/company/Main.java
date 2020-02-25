// Assignment 3 - Max and Min Calculator
// Tahseen Ahmed
// Thursday, October 17th, 2019
// ===============================
package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); // Initialize keyboard scanning for input.
        String givenInput = keyboard.nextLine();

        if (!checkCharacters(givenInput))
            System.out.println("INVALID CHARACTERS"); // Character check is of higher precedence than expression (parenthesis) check so it's checked first
        else if (!checkExpression(givenInput))
            System.out.println("INVALID EXPRESSION"); // If it passes both validity checks then we can go ahead with processing it.
        else
            System.out.println(minMax(givenInput));

    }

    private static boolean checkExpression(String expression) { // Strips the expression down and checks for bracket validity

        if (expression.isEmpty()) // No parenthesis implies it's only one expression, leaving it up to character checks
            return true;

        if (expression.contains("(@)") || expression.contains("(&)")) // Operators within brackets count as invalid expressions.
            return false;

        if (expression.contains("@()") || expression.contains("&()") || expression.contains("()@") || expression.contains("()&")) // Empty parentheses before/after an operator count as an invalid expression.
            return false;

        if (expression.contains("()")) // Empty parentheses anywhere count as an invalid expression
            return false;

        Stack<Character> parenStackCheck = new Stack<Character>(); // The stack used to check if parentheses are in correct groupings and order
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (current == '(') {
                parenStackCheck.push(current);
            }

            if (current == ')')
            {
                if (parenStackCheck.isEmpty())  // If there's nothing else left in the stack then a lone right bracket is automatically invalid
                    return false;

                char last = parenStackCheck.peek(); // Checks if there's a matching bracket in the stack.
                if (last == '(')
                    parenStackCheck.pop();
                else
                    return false;
                // After here it repeats until it's empty or it finds a mismatch
            }

        }
        return parenStackCheck.isEmpty();
    }

    private static boolean checkCharacters(String str) { // Strips the expression of valid characters and sees if there's any invalid ones left.
        String filteredString = str.replaceAll("([\\d@&()])+", ""); // This regex replaces all digits, parentheses, @ and & with nothing.
        return filteredString.equals(""); // An empty filtered string implies there are no invalid characters.
    }

    private static String minMax(String input) { // The main function for this assignment. Utilizes recursion to break down large expressions into the base case.

        List<String> numberList = Arrays.asList(input.split("[@&]")); // To work with more complicated expressions.
        String opString = input.replaceAll("[0-9()]", ""); // The list of operators within the expression. Used to handle chained expressions.

        int firstChunkLength; // The size of the first operation in a chain, 22&12@12 has a size of 5, used to splice the string for recursion.

        if (input.matches("[\\d]+") & !(input.matches(".*([@&()]).*"))) // Base case: No operators, parentheses and only digits implies it's just an integer.
        {
            return input;
        }
        else if ((numberList.size() == 2) & !(input.matches(".*([()]).*")))  // Case One: If the numberList size is two it implies there's only one operator that could have split the expression, leaving two arguments for minMaxBase().
        {
            return minMaxBase(input);
        }
        else if (!(input.matches(".*([()]).*"))) // Case Two: Checking chain expressions.
        {
            firstChunkLength = (numberList.get(0)).length() + (numberList.get(1)).length() + 1; // The length of the first chunk
            return minMax(minMaxBase(numberList.get(0) + opString.charAt(0) + numberList.get(1)) + input.substring(firstChunkLength)); // Repeat after doing the first computation.
        }
        else if (input.contains(")")) // Case Three/Four: Working through parenthesis. Checking either parenthesis works since we've already verified it's a valid expression.
        {
            for (int i = input.indexOf(')'); i > -1; i--) // Start the for loop at the first ) and then find its matching (, and evaluate the expression inside.
            {
                if (input.charAt(i) == '(')
                {   // Replace the bracketed expression with the result and put that into minMax again. This will break down the expressions until the base case is reached.
                    return minMax(input.substring(0, i) + minMax(input.substring(i + 1 , input.indexOf(')'))) + input.substring(input.indexOf(')') + 1));
                }
            }
        }

        return "You're missing a case. This is what's left:\n" + input; // This statement should never occur. It tells the user/programmer that there's a case that has not been covered.
    }

    private static String minMaxBase(String expression) { // The base case for both minimum and maximum computation. Assumes only one operator is contained, like 2@1
        String[] arguments = expression.split("[@&]");
        if (expression.contains("@")) { // Check if it's a min or max computation, and compute the respective outcome.
            return Integer.toString(Math.min(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]))); // Returns the minimum
        } else {
            return Integer.toString(Math.max(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]))); // Returns the maximum
        }

    }
}