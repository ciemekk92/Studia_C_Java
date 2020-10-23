package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        // Zad. 1, zestaw D, algorytm II
        // Odczytanie pliku
        ArrayList<Integer> inputFromFile = new ArrayList<Integer>();
        int operationCount = 0;
        File file = new File("src/in0101.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextInt()) {
            inputFromFile.add(scanner.nextInt());
        }
        scanner.close();

        // Algorytm - sprawdzic czy uproszczony przez wiekszy czynnik mianownika
        // Symbol Newtona: n! / (k!*(n-k)!)
        int nFile = inputFromFile.get(0);
        int kFile = inputFromFile.get(1);

        // Zapis do pliku
        FileWriter fileWriter = new FileWriter("src/out0101.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int[] newtonSymbol = calculateSymbol(nFile, kFile, operationCount);

        printWriter.print("Wyniki:\n");
        printWriter.printf("n: %d, k: %d\n", nFile, kFile );
        printWriter.printf("SN2: %d, licz: %d", newtonSymbol[0], newtonSymbol[1]);
        printWriter.close();
    }

    public static int[] calculateSymbol (int n, int k, int count) {

        if (k > (n - k)) {
            k = n - k;
        }
        int symbol = 1;
        for (int i = 0; i < k; i++) {
            symbol = symbol * (n - i);
            symbol = symbol / (i + 1);
            count++;
        }
        return new int[] {symbol, count};
    }
}
