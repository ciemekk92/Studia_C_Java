import java.util.Scanner;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Application {
    static class User {
        String login, name, surname, address, birthDate;
        private String password;

        public User() { name = "default"; password="default";}

        public User(String login, String password, String name, String surname, String address, String birthDate) {
            this.login = login;
            this.password = password;
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.birthDate = birthDate;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getLogin() {
            return login;
        }
        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getAddress() {
            return address;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String toString() {
            return login + " " + password.replaceAll(".", "*") + " " + name + " " + surname + " " + address + " " + birthDate;
        }
    }
    static class ComplexNumber {
        int real, imaginary;
        public ComplexNumber() {
            real = 0;
            imaginary = 0;
        }
        public ComplexNumber(int real, int imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public String toString() {
            return real + " + i * " + imaginary;
        }

        public int getReal() {
            return real;
        }

        public void setReal(int real) {
            this.real = real;
        }

        public int getImaginary() {
            return imaginary;
        }

        public void setImaginary(int imaginary) {
            this.imaginary = imaginary;
        }

        public ComplexNumber addComplex() {
            Scanner input = new Scanner(System.in);
            System.out.println("Podaj czesc rzeczywista liczby zespolonej (bez i) do dodania:");
            int newReal = input.nextInt();
            System.out.println("Podaj czesc urojona liczby zespolonej (przy i):");
            int newImaginary = input.nextInt();
            return new ComplexNumber(real + newReal, imaginary + newImaginary);
        }

        public ComplexNumber subtractComplex() {
            Scanner input = new Scanner(System.in);
            System.out.println("Podaj czesc rzeczywista liczby zespolonej (bez i) do odjecia:");
            int newReal = input.nextInt();
            System.out.println("Podaj czesc urojona liczby zespolonej (przy i):");
            int newImaginary = input.nextInt();
            return new ComplexNumber(real - newReal, imaginary - newImaginary);
        }

        public ComplexNumber multiplyComplex() {
            Scanner input = new Scanner(System.in);
            System.out.println("Podaj czesc rzeczywista liczby zespolonej (bez i) do pomnozenia:");
            int newReal = input.nextInt();
            System.out.println("Podaj czesc urojona liczby zespolonej (przy i):");
            int newImaginary = input.nextInt();
            int iSquare = imaginary * newImaginary;
            return new ComplexNumber((real * newReal) + (iSquare * (-1)), (real * newImaginary) + (imaginary * newReal));
        }
    }
    static class Stack {
        private int[] array;
        private int size;
        private int index = 0;

        public Stack(int size) {
            this.size = size;
            array = new int[size];
        }

        public void push(int element) {
            if (isFull()) {
                throw new StackOverflowError("Stos jest pelny");
            }

            array[index] = element;
            index++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return array[--index];
        }

        public boolean isEmpty() {
            return index == 0;
        }

        public boolean isFull() {
            return index == size;
        }
    }

    static ArrayList<User> userList = new ArrayList<>();
    static ArrayList<ComplexNumber> complexList = new ArrayList<>();
    static ArrayList<Stack> stackList = new ArrayList<>();

    public static void main(String[] args) {
        displayMenu();

        Scanner userInput = new Scanner(System.in);
        String option;
        do {
            option = userInput.next();
            switch(option) {
                case "1":
                    addUser();
                    break;
                case "2":
                    addDefaultUser();
                    break;
                case "3":
                    changePassword();
                    break;
                case "4":
                    showUserData();
                    break;
                case "5":
                    addNewComplex();
                    break;
                case "6":
                    viewComplex();
                    break;
                case "7":
                    addToComplex();
                    break;
                case "8":
                    subtractFromComplex();
                    break;
                case "9":
                    multiplyTwoComplexes();
                    break;
                case "a":
                    addNewStack();
                    break;
                case "s":
                    pushToStack();
                    break;
                case "d":
                    popFromStack();
                    break;
                case "f":
                    showStack();
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
    private static void addNewStack() {
        Scanner stack = new Scanner(System.in);
        System.out.println("Tworzenie nowego stosu. Podaj rozmiar:");
        int stackSize = stack.nextInt();
        stackList.add(new Stack(stackSize));
        System.out.print("Dodano nowy stos. Rozmiar listy stosow: " + stackList.size() + "\n");
    }

    private static void pushToStack() {
        if (stackList.size() == 0) {
            System.out.println("Brak stosow.");
        } else {
            Scanner stack = new Scanner(System.in);
            System.out.println("Wybierz stos, do ktorego chcesz dodac liczbe");
            int stackIndex = stack.nextInt();
            System.out.println("Podaj liczbe do dodania");
            int input = stack.nextInt();
            stackList.get(stackIndex).push(input);
            System.out.println("Dodano liczbe.");
        }
    }

    private static void popFromStack() {
        if (stackList.size() == 0) {
            System.out.println("Brak stosow.");
        } else {
            Scanner stack = new Scanner(System.in);
            System.out.println("Wybierz stos, z ktorego chcesz zdjac liczbe");
            int stackIndex = stack.nextInt();
            System.out.print("Zdjeta liczba:" + stackList.get(stackIndex).pop() + "\n");
        }
    }

    private static void showStack() {
        if (stackList.size() == 0) {
            System.out.println("Brak stosow.");
        } else {
            Scanner stack = new Scanner(System.in);
            System.out.println("Wybierz stos, ktory chcesz wyswietlic");
            int stackIndex = stack.nextInt();
            for (int i = 0; i < stackList.get(stackIndex).array.length; i++) {
                System.out.println(stackList.get(stackIndex).array[i]);
            }
        }
    }

    private static void addNewComplex() {
        Scanner complex = new Scanner(System.in);
        System.out.println("Podaj czesc rzeczywista:");
        int realPart = complex.nextInt();
        System.out.println("Podaj czesc urojona:");
        int imaginaryPart = complex.nextInt();
        complexList.add(new ComplexNumber(realPart, imaginaryPart));
        System.out.print("Dodano nowa liczbe. Rozmiar listy liczb zespolonych: " + complexList.size() + "\n");
    }

    private static void viewComplex() {
        if (complexList.size() == 0) {
            System.out.println("Brak zapisanych liczb zespolonych");
        } else {
            System.out.print("Podaj indeks liczby do wyswietlenia (od 0). Rozmiar listy liczb zespolonych: " + complexList.size() + "\n");
            Scanner viewingComplex = new Scanner(System.in);
            int input = viewingComplex.nextInt();
            System.out.print("Liczba zespolona: " + complexList.get(input).real + " + i * " + complexList.get(input).imaginary + "\n");
        }
    }

    private static void addToComplex() {
        if (complexList.size() == 0) {
            System.out.println("Brak zapisanych liczb zespolonych");
        } else {
            System.out.print("Wybierz indeks liczby do której chcesz dodać liczbę zespoloną (od 0). Liczba zapisanych liczb: " + complexList.size() + "\n");
            Scanner selectComplex = new Scanner(System.in);
            int input = selectComplex.nextInt();
            complexList.add(complexList.get(input).addComplex());
            System.out.print("Dodano do listy nowa liczbe zespolona: " + complexList.get(complexList.size() - 1) + "\n");
        }
    }

    private static void subtractFromComplex() {
        if (complexList.size() == 0) {
            System.out.println("Brak zapisanych liczb zespolonych");
        } else {
            System.out.print("Wybierz indeks liczby od której chcesz odjać liczbę zespoloną (od 0). Liczba zapisanych liczb: " + complexList.size() + "\n");
            Scanner selectComplex = new Scanner(System.in);
            int input = selectComplex.nextInt();
            complexList.add(complexList.get(input).subtractComplex());
            System.out.print("Dodano do listy nowa liczbe zespolona: " + complexList.get(complexList.size() - 1) + "\n");
        }
    }

    private static void multiplyTwoComplexes() {
        if (complexList.size() == 0) {
            System.out.println("Brak zapisanych liczb zespolonych");
        } else {
            System.out.print("Wybierz indeks liczby która chcesz pomnozyc liczbę zespoloną (od 0). Liczba zapisanych liczb: " + complexList.size() + "\n");
            Scanner selectComplex = new Scanner(System.in);
            int input = selectComplex.nextInt();
            complexList.add(complexList.get(input).multiplyComplex());
            System.out.print("Dodano do listy nowa liczbe zespolona: " + complexList.get(complexList.size()-1) + "\n");
        }

    }

    private static void showUserData() {
        if (userList.size() == 0) {
            System.out.println("Brak zapisanych uzytkownikow");
        } else {
            System.out.println("Wybierz uzytkownika (indeks od 0)");
            Scanner select = new Scanner(System.in);
            int index = select.nextInt();
            System.out.print("Dane tego uzytkownika to: " + userList.get(index).toString());
        }
    }

    private static void addDefaultUser() {
        userList.add(new User());
        System.out.println("Dodano uzytkownika domyslnego");
    }

    private static void exitApp() {
        System.exit(0);
    }

    private static void changePassword() {
        if (userList.size() == 0) {
            System.out.println("Brak zapisanych uzytkownikow");
        } else {
            System.out.println("Wybierz uzytkownika (indeks od 0)");
            Scanner select = new Scanner(System.in);
            int index = select.nextInt();
            System.out.println("Podaj nowe haslo:");
            String newPassword = select.next();
            userList.get(index).setPassword(newPassword);
            System.out.print("Ustawiono nowe haslo: " + userList.get(index).getPassword());
        }
    }

    private static void addUser() {
        Scanner addingUser = new Scanner(System.in);
        System.out.println("Dodawanie nowego uzytkownika. Podaj nazwe uzytkownika");
        String username = addingUser.next();
        System.out.println("Podaj haslo");
        String password = addingUser.next();
        System.out.println("Podaj imie");
        String name = addingUser.next();
        System.out.println("Podaj nazwisko");
        String surname = addingUser.next();
        System.out.println("Podaj adres");
        addingUser.nextLine();
        String address = addingUser.nextLine();
        System.out.println("Podaj date urodzenia");
        String date = addingUser.next();
        userList.add(new User(username, password, name, surname, address, date));
        System.out.print("Dodano uzytkownika. Liczba uzytkownikow: " + userList.size() + "\n");
        displayMenu();
    }

    private static void displayMenu() {
        System.out.println("************* Czesc 1 *************");
        System.out.println("1 - Dodaj uzytkownika (addUser)");
        System.out.println("2 - Dodaj domyslnego uzytkownika (addDefaultUser)");
        System.out.println("3 - Zmien haslo uzytkownika (setPassword)");
        System.out.println("4 - Wyswietl dane uzytkownika (toString)");
        System.out.println("************* Czesc 2 *************");
        System.out.println("5 - Dodaj nowa liczbe zespolona do listy (addComplex)");
        System.out.println("6 - Wyswietl liczbe zespolona z listy (viewComplex)");
        System.out.println("7 - Dodaj do liczby zespolonej (addToComplex)");
        System.out.println("8 - Odejmij od liczby zespolonej z listy (subtractFromComplex)");
        System.out.println("9 - Pomnoz liczby zespolone (multiplyTwoComplexes)");
        System.out.println("************* Czesc 3 *************");
        System.out.println("a - Dodaj nowy stos");
        System.out.println("s - Dodaj liczbe do stosu (push)");
        System.out.println("d - Zdejmij liczbe ze stosu (pop)");
        System.out.println("f - Wyswietl stos");
        System.out.println("***********************************");
        System.out.println("w - Wyjscie");
    }
}
