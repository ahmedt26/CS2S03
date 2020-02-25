// Exam Practice - Graph Connection
// Tahseen Ahmed
// Tuesday, December 17th, 2019
// Checks if nodes in a graph are
// directly or indirectly connected
// This was done using recursion.
// =====================================
package com.company;
public class NodeConnection {

    public static void main(String[] args) {

        boolean[] visitedPaths = {false, false, false}; // Used to keeping track of which paths have been traversed

        boolean[][] firstGraph = { {false, true, false},
                                   {true, false, true},
                                   {true, true, false}};

        // Test prints
        System.out.println("=============================================================");
        System.out.println("Directed Connection Test for Graphs:");
        System.out.println("=============================================================");
        System.out.println("If 0 is connected to itself:");
        System.out.println(isConnectedGraph(firstGraph, 0,0, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 0 is connected to 1:");
        System.out.println(isConnectedGraph(firstGraph, 0,1, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 0 is connected to 2:");
        System.out.println(isConnectedGraph(firstGraph, 0,2, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 1 is connected to 0:");
        System.out.println(isConnectedGraph(firstGraph, 1,0, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 1 is connected to itself:");
        System.out.println(isConnectedGraph(firstGraph, 1,1, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 1 is connected to 2:");
        System.out.println(isConnectedGraph(firstGraph, 1,2, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 2 is connected to 0:");
        System.out.println(isConnectedGraph(firstGraph, 2,0, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 2 is connected to 1:");
        System.out.println(isConnectedGraph(firstGraph, 2,1, visitedPaths));
        System.out.println("=============================================================");
        System.out.println("If 2 is connected to itself:");
        System.out.println(isConnectedGraph(firstGraph, 2,2, visitedPaths));
        System.out.println("=============================================================");

    }

   static boolean isConnectedGraph(boolean[][] graph, int source, int dest, boolean[] visited) {

        if (source == dest) {    // If source and destination are the same then they are self-connected
            System.out.println("SELF-CONNECTION: SOURCE IS DESTINATION");
            return true;
        }

        visited[source] = true;                          // The source is self-connected.

        if (graph[source][dest]) {                // Check for a direct connection first.
            System.out.println("DIRECT CONNECTION");
            return true;
        }

        for (int i = 0; i < graph[0].length; i++) {    // Traverse through the graph to see if the two nodes are indirectly connected.
            if (graph[source][i] && !visited[i]) {
                visited[i] = true;                     // Add visited graphs to your visited array.
                if (isConnectedGraph(graph, i, dest, visited)) {
                    System.out.println("INDIRECT CONNECTION");
                    return true;
                }

            }
        }
        System.out.println("CONNECTION FAILURE");
        return false;
    }
}

/*
true false true
false true false
true false true
 */

