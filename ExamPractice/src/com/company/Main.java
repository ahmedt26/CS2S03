// Exam Practice - Parenthesis Matcher
// Tahseen Ahmed
// Tuesday, December 17th, 2019
// =====================================
package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Initialize keyboard scanning for input.
        String givenInput = input.nextLine();

        if (!checkCharacters(givenInput))
            System.out.println("Error! There are characters other than parentheses in the input!"); // Character check is of higher precedence than expression (parenthesis) check so it's checked first
        else if (!checkExpression(givenInput))
            System.out.println("Error! Parentheses do not match!"); // If it passes both validity checks then we can go ahead with processing it.
        else
            System.out.println("PASS: All parentheses match!");
    }

    private static boolean checkExpression(String expression) { // Strips the expression down and checks for bracket validity

            if (expression.isEmpty())
                return true;
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < expression.length(); i++)
            {
                char current = expression.charAt(i);
                if (current == '(')
                {
                    stack.push(current);
                }
                if (current == ')')
                {
                    if (stack.isEmpty())
                        return false;

                    char last = stack.peek();
                    if (current == ')' && last == '(')
                        stack.pop();
                    else
                        return false;
                }
            }
            return stack.isEmpty();
    }

    private static boolean checkCharacters(String str) { // Strips the expression of valid characters and sees if there's any invalid ones left.
        String filteredString = str.replaceAll("([()])+", ""); // This regex replaces all digits, parentheses, @ and & with nothing.
        return filteredString.equals(""); // An empty filtered string implies there are no invalid characters.
    }

}

