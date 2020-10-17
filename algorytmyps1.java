import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		// Odczytanie pliku
		ArrayList<Integer> inputFromFile = new ArrayList<Integer>();
		int operationCount = 0;
		File file = new File("src/in01d.txt");
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
		FileWriter fileWriter = new FileWriter("src/out01d.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		// Not working
		printWriter.print("Wyniki:");
		printWriter.printf("n: %d, k: %d", nFile, kFile );
		printWriter.printf("SN2: %d", calculateSymbol(nFile, kFile));
		
	}
	
	public static int calculateSymbol (int n, int k) {
		
		if (k > (n - k)) {
			k = n - k;
		}
		int symbol = 1;
			for (int i = 0; i < k; i++) {
				symbol = symbol * (n - i);
				symbol = symbol / (i + 1);
			}
		return symbol;
	}
	
	

}
