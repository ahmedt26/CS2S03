/*
// This needs the other classes from the lecture to work.
package com.company;

import java.util.LinkedList;

public class StackByAggregation<E> {
    // Field
    private LinkedList<E> stackList; // (1)
    // Constructor
    public StackByAggregation() { // (2)
        stackList = new LinkedList<E>();
    }
    // Instance methods
    public void push(E data) { // (3)
        stackList.insertAtHead(data);
    }
    public E pop() { // (4)
        if (empty())
            return null;
        return stackList.removeFromHead();
    }
    public E peek() { // (5)
        if (empty())
            return null;
        return stackList.first().getData();
    }
    public boolean empty() { // (6)
        return stackList.isEmpty();
    }
}
*/