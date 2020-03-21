import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Application {
    static ArrayList<String> noteList = new ArrayList<String>();
    public static void main(String[] args) {
        displayMenu();
        Scanner userInput = new Scanner(System.in);
        String option;
        do {
            option = userInput.next();
            switch (option) {
                case "1":
                    addNote();
                    break;
                case "2":
                    readNote();
                    break;
                case "3":
                    deleteNote();
                    break;
                case "4":
                    try {
                        saveToFile();
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                    break;
                case "5":
                    readFromFile();
                    break;
                case "6":
                    searchFragment();
                    break;
                case "w":
                    exitApp();
                    break;
                default:
                    System.out.println("Brak opcji " + option);
                    System.out.println();
                    break;

            }
        } while (!"w".equals(option));


    }

    private static void displayMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1 - Dodaj notatke.");
        System.out.println("2 - Wyswietl notatke.");
        System.out.println("3 - Usun notatke.");
        System.out.println("-----------------------------------------");
        System.out.println("4 - Zapisz notatke do pliku notatki.txt.");
        System.out.println("5 - Odczytaj notatke z pliku notatki.txt.");
        System.out.println("6 - Wyszukaj fragment tekstu w zapisanych notatkach.");
        System.out.println("w - Wyjscie.");
        System.out.println();
    }

    private static void searchFragment() {
        System.out.println("Wyszukiwanie fragmentu w zapisanych notatkach. Podaj fragment do wyszukania: ");
        Scanner fragment = new Scanner(System.in);
        String input = fragment.next();
        System.out.println("Znalezione notatki z fragmentem:");
        for (String test:noteList) {
            if (test.toLowerCase().contains(input.toLowerCase())) {
                System.out.println(test);
            }
        }
        returnToMenu();
    }

    private static void readFromFile() {
        try {
            Scanner readFile = new Scanner(new File("notatki.txt"));
            Scanner userInput = new Scanner(System.in);
            System.out.println("Odczyt notatki z pliku. Podaj numer linii do odczytu: ");
            int input = userInput.nextInt();
            int i = 0;
            while (readFile.hasNextLine() && i <= input) {
                readFile.next();
                i++;
                if (i == input-1) {
                    System.out.println(readFile.next());
                }
            }
            readFile.close();
            returnToMenu();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }
    }

    private static void returnToMenu() {
        System.out.println("Wyswietlic menu? t / n");
        Scanner menuReturn = new Scanner(System.in);
        String input = menuReturn.next();
        if (input.equals("t") || input.equals("T")) {
            displayMenu();
        }
    }

    private static void saveToFile() throws FileNotFoundException, IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("notatki.txt", true)));
        System.out.println("Zapis do pliku. Podaj tresc notatki: ");
        Scanner addingNoteToFile = new Scanner(System.in);
        String note = addingNoteToFile.next();
        writer.println(note);
        System.out.println("Zapisano notatke do pliku notatki.txt");
        writer.close();
        returnToMenu();
    }

    private static void exitApp() {
        System.out.println("Zamykanie aplikacji.");
        System.exit(0);
    }

    private static void deleteNote() {
        if (noteList.size() == 0) {
            System.out.println("Brak zapisanych notatek. Powrot do menu.");
            displayMenu();
        } else {
            System.out.print("Usuwanie notatki. Podaj numer notatki do usunięcia (od 1). Liczba zapisanych notatek: "+ noteList.size() + "\n");
            Scanner deletingNote = new Scanner(System.in);
            int input = deletingNote.nextInt();
            noteList.remove(input - 1);
            System.out.print("Notatka usunięta. Pozostało notatek: " + noteList.size() + "\n");
            returnToMenu();
        }
    }

    private static void readNote() {
        if (noteList.size() == 0) {
            System.out.println("Brak zapisanych notatek. Powrot do menu.");
            displayMenu();
        } else {
            System.out.print("Wyswietlanie notatki. Podaj numer notatki (od 1). Liczba zapisanych notatek: "+ noteList.size() + "\n");
            Scanner readingNote = new Scanner(System.in);
            int input = readingNote.nextInt();
            System.out.println(noteList.get(input-1));
            returnToMenu();
        }
    }

    private static void addNote() {
        Scanner addingNote = new Scanner(System.in);
        System.out.println("Dodawanie notatki:");
        String note;
        note = addingNote.next();
        noteList.add(note);
        System.out.print("Dodano notatke. Liczba notatek: " + noteList.size() + "\n");
        returnToMenu();
    }
}
