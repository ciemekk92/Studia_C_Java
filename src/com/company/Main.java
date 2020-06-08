package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        Stack<Figure> figureStack = new Stack<>();
        Stack<Point> pointStack = new Stack<>();

        fillStacks(integerStack, pointStack, figureStack);

        Scanner userInput = new Scanner(System.in);
        String option;

        displayMenu();

        do {
            option = userInput.next();
            switch(option) {
                case "1":
                    pushNewInt(integerStack);
                    break;
                case "2":
                    popFromIntStack(integerStack);
                    break;
                case "3":
                    printIntStack(integerStack);
                    break;
                case "4":
                    printFigureStack(figureStack);
                    break;
                case "5":
                    movePointStack(pointStack, figureStack);
                    break;
                case "6":
                    fillStacks(integerStack, pointStack, figureStack);
                case "w":
                    exitApp();
                    break;
                default:
                    System.out.println("Brak opcji " + option);
                    break;
            }
        } while (!"w".equals(option));
    }

    private static void fillStacks(Stack<Integer> intStack, Stack<Point> ptStack, Stack<Figure> fgStack){
        fillIntStack(intStack);
        fillPointStack(ptStack);
        fillFigureStack(fgStack);
    }

    private static void fillIntStack(Stack<Integer> stack) {
        stack.push(5);
        stack.push(11);
        stack.push(10);
        stack.push(0);
        stack.push(2);
    }

    private static void fillPointStack(Stack<Point> stack) {
        Point p1 = new Point(5,5);
        p1.setLabel("Punkt nr 1");
        Point p2 = new Point(7,11);
        p2.setLabel("Punkt nr 2");
        Point p3 = new Point(12,3);
        p3.setLabel("Punkt nr 3");

        stack.push(p1);
        stack.push(p2);
        stack.push(p3);
    }

    private static void fillFigureStack(Stack<Figure> stack) {
        Circle c1 = new Circle((new Point(7,3)), 10);
        Circle c2 = new Circle((new Point(34,12)), 16);
        Circle c3 = new Circle((new Point(7,1)), 6);
        c1.setLabel("Kolko nr 1");
        c2.setLabel("Kolko nr 2");
        c3.setLabel("Kolko nr 3");

        stack.push(c1);
        stack.push(c2);
        stack.push(c3);
    }

    private static void pushNewInt(Stack<Integer> stack) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Podaj liczbe calkowita by dodac ja do kolejki");
        int input = userInput.nextInt();
        stack.push(input);
        System.out.println("Dodano liczbe " + input);
    }

    private static void popFromIntStack(Stack<Integer> stack) {
        stack.pop();
        System.out.println("Usunieto pierwsza liczbe z kolejki");
    }

    private static void printIntStack(Stack<Integer> stack) {
        QueueUtils.printAndClear(stack);
        fillIntStack(stack);
    }

    private static void printFigureStack(Stack<Figure> stack) {
        QueueUtils.printFiguresAndClear(stack);
         fillFigureStack(stack);
    }

    private static void movePointStack(Stack<Point> ptStack, Stack<Figure> fgStack) {
        QueueUtils.move(ptStack, fgStack);
        System.out.println("Nowa kolejka po przeniesieniu: ");
       printFigureStack(fgStack);

         fillPointStack(ptStack);
    }

    private static void displayMenu() {
        System.out.println("************* MENU *************");
        System.out.println("1 - Dodaj liczbe calkowita na koniec kolejki calkowitych");
        System.out.println("2 - Usun ostatnia liczbe calkowita z kolejki");
        System.out.println("3 - Wyswietl kolejke liczb calkowitych");
        System.out.println("4 - Wyswietl kolejke figur (po wyswietleniu wypelniona na nowo)");
        System.out.println("5 - Przenies kolejke punktow do kolejki figur (+ wyswietlenie, po nim kolejka figur wroci do stanu poczatkowego )");
        System.out.println("6 - Wypelnij kolejki");
        System.out.println("w - Wyjscie");
    }

    private static void exitApp() {
        System.exit(0);
    }
}
