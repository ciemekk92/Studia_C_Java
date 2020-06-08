package com.company;

public class QueueUtils {
    // wyswietlanie + usuwanie z kolejki
    public static <T> void printAndClear(Stack<T> stack) {
        while(!stack.isEmpty()) {
            System.out.println(stack.pop().toString());
        }
    }

    // wyswietlanie i usuwanie figur
    public static void printFiguresAndClear(Stack<Figure> stack) {
        while(!stack.isEmpty()) {
            System.out.println(stack.pop().getLabel());
        }
    }

    // przenoszenie jednej kolejki do drugiej
    public static <T> void move(Stack<? extends T> source, Stack<T> dest) {
        while(!source.isEmpty()) {
            dest.push(source.pop());
        }
    }
}
