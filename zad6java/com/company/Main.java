// Author: Przemysław Reducha
package com.company;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 2);
        m1.set(0, 0, 4);
        m1.set(0, 1, 5);
        m1.set(1, 0, 7);
        m1.set(1, 1, 8);
        m1.set(2, 0, 10);
        m1.set(2, 1, 11);

        Matrix m2 = new Matrix(2, 2);
        m2.set(0, 0, 12);
        m2.set(0, 1, 11);
        m2.set(1, 0, 16);
        m2.set(1, 1, 3);

        Matrix m3 = new Matrix(3, 2);
        m3.set(0, 0, 7);
        m3.set(0, 1, 9);
        m3.set(1, 0, 2);
        m3.set(1, 1, 4);
        m3.set(2, 0, 11);
        m3.set(2, 1, 13);

        Scanner userInput = new Scanner(System.in);
        String option;
        displayMenu();
        do {
            option = userInput.next();
            switch (option) {
                case "1":
                    try {
                        loadMatrixA(m1);
                    } catch (MatrixIndexException ex) {
                    System.out.println("Blad w indeksie macierzy");
                } finally {
                        displayMenu();
                    }
                    break;
                case "2":
                    try {
                        loadMatrixB(m2);
                    } catch (MatrixIndexException ex){
                        System.out.println("Blad w indeksie macierzy");
                } finally {
                        displayMenu();
                    }
                    break;
                case "3":
                    try {
                        loadMatrixC(m3);
                    } catch (MatrixIndexException ex){
                        System.out.println("Blad w indeksie macierzy");
                    } finally {
                        displayMenu();
                    }
                    break;
                case "4":
                    try {
                        addMatrices(m1, m3);
                    } catch (MatrixOperationException ex) {
                        System.out.println("Niedozwolona operacja");
                    } finally {
                        displayMenu();
                    }
                    break;
                case "5":
                    try {
                        multiplyMatrices(m1, m2);
                    } catch (MatrixOperationException ex){
                        System.out.println("Niedozwolona operacja");
                } finally {
                        displayMenu();
                    }
                    break;
                case "w":
                    exitApp();
                    break;
                default:
                    System.out.println("Brak opcji" + option);
                    System.out.println();
                    break;
            }
        } while (!"w".equals(option));
    }

    private static void loadMatrixA(Matrix matrix) {
        System.out.println("Dodano macierz A (3x2):");
        for (double element:matrix) {
            System.out.print(element + " ");
        }
        System.out.println();
        for (double[] row:matrix.rows()) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void loadMatrixB(Matrix matrix) {
        System.out.println("Dodano macierz B (2x2):");
        for (double element:matrix) {
            System.out.print(element + " ");
        }
        System.out.println();
        for (double[] row:matrix.rows()) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void loadMatrixC(Matrix matrix) {
        System.out.println("Dodano macierz C (3x2):");
        for (double element:matrix) {
            System.out.print(element + " ");
        }
        System.out.println();
        for (double[] row:matrix.rows()) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void addMatrices(Matrix m1, Matrix m2) {
        Matrix result = m1.add(m2);
        System.out.println("Dodano macierze. Wynik:");
        System.out.println();
        for (double element:result) {
            System.out.print(element + " ");
        }
        System.out.println();
        for (double[] row:result.rows()) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void multiplyMatrices(Matrix m1, Matrix m2) {
        Matrix result = m1.multiply(m2);
        System.out.println("Przemnozono macierze. Wynik:");
        System.out.println();
        for (double element:result) {
            System.out.print(element + " ");
        }
        System.out.println();
        for (double[] row:result.rows()) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void exitApp() {
        System.exit(0);
    }

    private static void displayMenu() {
        System.out.println("************* MENU *************");
        System.out.println("1 - Wczytaj macierz A (3x2)");
        System.out.println("2 - Wczytaj macierz B (2x2)");
        System.out.println("3 - Wczytaj macierz C (3x2)");
        System.out.println("4 - Dodaj macierze A i C");
        System.out.println("5 - Pomnoz macierze A i B");
        System.out.println("w - Wyjscie");
    }
}
