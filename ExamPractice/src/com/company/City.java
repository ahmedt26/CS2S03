package com.company;
/*
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class City {
    public static void main(String[] args) {
// (1) Create a city graph:
        boolean[][] cityGraph = {
                {false, true, false, true, true},
                {false, false, true, false, false},
                {false, false, false, false, true},
                {false, true, true, false, false},
                {false, false, false, false, false}
        };
// (2) Read the city from the keyboard:
        System.out.print("Type the number of the city to start from [0-" +
                (cityGraph.length-1) + "]: ");
        Scanner keyboard = new Scanner(System.in);
        int startCityNum = keyboard.nextInt();
        if (startCityNum < 0 || startCityNum >= cityGraph.length) {
            System.out.print("Unknown city number: " + startCityNum);
            return;
        }
// (3) Create a city stack:
        StackByAggregation<Integer> cityStack = new StackByAggregation<>();
// (4) Create a city set:
        Set<Integer> citySet = new HashSet<>();
// (5) Push start city on the stack:
        cityStack.push(startCityNum);
// (6) Handle each city found on the stack:
        while (!cityStack.empty()) {
// Pop current city from the stack.
            int currentCityNum = cityStack.pop();
// Check if the city has already been handled.
            if (!citySet.contains(currentCityNum)) {
                // Insert current city to the city set.
                citySet.add(currentCityNum);
// Push all cities that can be reached directly from
// the current city on to the stack
                for (int j = 0; j < cityGraph[currentCityNum].length; j++) {
                    if (cityGraph[currentCityNum][j])
                        cityStack.push(j);
                }
            }
        }
// Remove start city from the city set, and print the city stack.
        citySet.remove(startCityNum);
        System.out.print("From city no. " + startCityNum +
                ", the following cities can be reached: " + citySet);
    }
}
            }

 */