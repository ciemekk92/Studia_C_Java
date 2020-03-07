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
                    saveToFile();
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
                    System.out.println("Brak opcji" + option);
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
        System.out.println("4 - Zapisz notatke do pliku.");
        System.out.println("5 - Odczytaj notatke z pliku.");
        System.out.println("6 - Wyszukaj fragment tekstu w notatkach.");
        System.out.println("w - Wyjscie.");
        System.out.println();
    }

    private static void searchFragment() {
    }

    private static void readFromFile() {
    }

    private static void returnToMenu() {
        System.out.println("Wyswietlic menu? t / n");
        Scanner menuReturn = new Scanner(System.in);
        String input = menuReturn.next();
        if (input == "t" || input == "T") {
            displayMenu();
        }
    }

    private static void saveToFile() {
    }

    private static void exitApp() {
        System.exit(0);
    }

    private static void deleteNote() {
    }

    private static void readNote() {
        System.out.print("Wyswietlanie notatki. Podaj numer notatki (Liczba notatek: " + noteList.size() + ")\n");
        Scanner readingNote = new Scanner(System.in);
        int input = readingNote.nextInt();
        System.out.println(noteList.get(input));
        returnToMenu();
    }

    private static void addNote() {
        Scanner addingNote = new Scanner(System.in);
        System.out.println("Dodawanie notatki:");
        String note;
        note = addingNote.next();
        noteList.add(note);
        System.out.print("Dodano notatke. Liczba notatek: " + noteList.size() + "\n");

    }
}
