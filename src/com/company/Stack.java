package com.company;
import java.util.EmptyStackException;


public class Stack<T> {
    private Node<T> first, last;

    private static class Node<U> {
        private Node next;
        private U data;

        public Node(U data) {
            this.data = data;
        }
    }

    void push (T data) {
        Node<T> n = new Node<>(data);
        if (first == null) {
            first = last = n;
            return;
        }
        last.next = n;
        last = n;
    }

    T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T data = first.data;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return data;
    }

    boolean isEmpty() {
        return first == null;
    }
}
