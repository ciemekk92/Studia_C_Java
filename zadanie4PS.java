// Author: Przemyslaw Reducha
// Pole, przesuwanie, total toString do klasy Picture
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

public class Application {
    static abstract class Figure {
        private String label;
        public void move(double dx, double dy) {}
        public String toString() {return "";};
        public double getArea() {return 0;};
        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    static class Point extends Figure {
        private double x, y;

        // Konstruktor parametrowy
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // Konstruktor bezparametrowy
        public Point() {
            x = 0;
            y = 0;
        }

        // Konstruktor kopiujacy
        public Point(Point p) {
            x = p.x;
            y = p.y;
        }

        // Metoda move
        public void move(double dx, double dy) {
            x += dx;
            y += dy;
        }

        // Metoda toString
        public String toString() {
            return "(" + x + " , " + y + ")";
        }

        // Metoda getArea
        public double getArea() {
            return 0;
        }
    }

    static class Section extends Figure{
        private Point p1, p2;

        // Konstruktor parametrowy
        public Section(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        // Konstruktor bezparametrowy
        public Section() {}

        // Konstruktor kopiujący - kopia gleboka
        public Section(Section s) {
            this.p1 = new Point(s.p1);
            this.p2 = new Point(s.p2);
        }

        // Metoda move
        public void move(double dx, double dy) {
            p1.move(dx, dy);
            p2.move(dx, dy);
        }

        // Metoda toString
        public String toString() {
            return p1.toString() + " " + p2.toString();
        }

        // Metoda getArea
        public double getArea() {
            return 0;
        }
    }

    static class Circle extends Figure{
        private Point o;
        private double r;

        // Konstruktor parametrowy
        public Circle(Point o, double r) {
            this.o = o;
            this.r = r;
        }

        // Konstruktor bezparametrowy
        public Circle() {}

        // Konstruktor kopiujacy - kopia gleboka
        public Circle(Circle c) {
            this.o = new Point(c.o);
            this.r = c.r;
        }

        // Metoda move
        public void move(double dx, double dy) {
            o.move(dx, dy);
        }

        // Metoda toString
        public String toString() {
            return o.toString() + " r = " + r;
        }

        // Metoda getArea
        public double getArea() {
            return 3.14 * r * r;
        }
    }

    static class Triangle extends Figure{
        private Point p1, p2, p3;

        // Konstruktor parametrowy
        public Triangle(Point p1, Point p2, Point p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }

        // Konstruktor bezparametrowy
        public Triangle() {}

        // Konstruktor kopiujacy
        public Triangle(Triangle t) {
            this.p1 = new Point(t.p1);
            this.p2 = new Point(t.p2);
            this.p3 = new Point(t.p3);
        }

        // Metoda move
        public void move(double dx, double dy) {
            p1.move(dx, dy);
            p2.move(dx, dy);
            p3.move(dx, dy);
        }

        // Metoda toString
        public String toString() {
            return p1.toString() + " " + p2.toString() + " " + p3.toString();
        }

        // Metoda getArea
        public double getArea() {
            return ((p1.x * (p2.y - p3.y)) + (p2.x * (p3.y - p1.y)) + (p3.x * (p1.y - p2.y))) / 2;
        }
    }

    static class Picture {
        public ArrayList<Figure> figureList = new ArrayList<>();

        public boolean checkLabel(Figure figure) {
            return !figure.label.equals("");
        }

        public boolean add(Figure figure) {
            if (checkLabel(figure)) {
                figureList.add(figure);
                return true;
            }
            return false;
        }

        public void move(double dx, double dy) {
            for (Figure figure:figureList) {
                figure.move(dx, dy);
            }
        }

        public double getArea() {
            double totalArea = 0;
            for (Figure figure:figureList) {
                totalArea += figure.getArea();
            }
            return totalArea;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Figure figure:figureList) {
                sb.append(figure.toString());
            }
            return sb.toString();
        }
    }

    static class UniquePicture extends Picture {
        public boolean checkLabel(Figure figure) {
            for (Figure figureTest:figureList) {
                if (figure.label.equals(figureTest.label)) {
                    return false;
                }
            }
            return true;
        }
    }

    static class StandarizedPicture extends Picture {
        public boolean checkLabel(Figure figure) {
            return Pattern.matches("^([A-Z])([A-Z0-9]+)", figure.label);
        }
    }

    public static void main(String[] args) {
        Picture mainPicture = new Picture();
        UniquePicture unique = new UniquePicture();
        StandarizedPicture standarized = new StandarizedPicture();

        displayMenu();
        Scanner userInput = new Scanner(System.in);
        String option;
        do {
            option = userInput.next();
            switch(option) {
                case "1":
                    addNewPoint(mainPicture);
                    break;
                case "2":
                    addNewSection(mainPicture);
                    break;
                case "3":
                    addNewCircle(mainPicture);
                    break;
                case "4":
                    addNewTriangle(mainPicture);
                    break;
                case "5":
                    printPicture(mainPicture);
                    break;
                case "6":
                    moveElement(mainPicture);
                    break;
                case "7":
                    totalArea(mainPicture);
                    break;
                case "8":
                    addNewCircle(unique);
                    break;
                case "9":
                    addNewCircle(standarized);
                    break;
                case "10":
                    printPicture(standarized);
                    break;
                case "11":
                    printPicture(unique);
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

    private static void addNewPoint(Picture picture) {
        Scanner pointInput = new Scanner(System.in);
        System.out.println("Podaj wspolrzedna X punktu:");
        double pointX = pointInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y punktu:");
        double pointY = pointInput.nextDouble();
        picture.add(new Point(pointX, pointY));
        System.out.println("Dodano punkt.");
    }

    private static void addNewSection(Picture picture) {
        Scanner sectionInput = new Scanner(System.in);
        System.out.println("Podaj wspolrzedna X punktu 1:");
        double pointX1 = sectionInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y punktu 1:");
        double pointY1 = sectionInput.nextDouble();
        System.out.println("Podaj wspolrzedna X punktu 2:");
        double pointX2 = sectionInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y punktu 2:");
        double pointY2 = sectionInput.nextDouble();
        picture.add(new Section(new Point(pointX1, pointY1), new Point(pointX2, pointY2)));
        System.out.println("Dodano odcinek.");
    }

    private static void addNewCircle(Picture picture) {
        Scanner circleInput = new Scanner(System.in);
        System.out.println("Podaj wspolrzedna X srodka okregu:");
        double circleX = circleInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y srodka okregu:");
        double circleY = circleInput.nextDouble();
        System.out.println("Podaj promien okregu:");
        double radius = circleInput.nextDouble();
        System.out.println("Podaj etykiete");
        circleInput.nextLine();
        String label = circleInput.nextLine();
        Circle circle = new Circle(new Point(circleX, circleY), radius);
        circle.setLabel(label);
        picture.add(circle);
        System.out.println("Dodano okrag.");
    }

    private static void addNewTriangle(Picture picture) {
        Scanner triangleInput = new Scanner(System.in);
        System.out.println("Podaj wspolrzedna X punktu 1:");
        double triangleX1 = triangleInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y punktu 1:");
        double triangleY1 = triangleInput.nextDouble();
        System.out.println("Podaj wspolrzedna X punktu 2:");
        double triangleX2 = triangleInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y punktu 2:");
        double triangleY2 = triangleInput.nextDouble();
        System.out.println("Podaj wspolrzedna X punktu 3:");
        double triangleX3 = triangleInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y punktu 3:");
        double triangleY3 = triangleInput.nextDouble();
        picture.add(new Triangle(new Point(triangleX1, triangleY1), new Point(triangleX2, triangleY2), new Point(triangleX3, triangleY3)));
        System.out.println("Dodano trojkat.");
    }

    private static void printPicture(Picture picture) {
        System.out.println("Punkty:");
        for ( int i = 0; i < picture.figureList.size(); i++) {
            if (picture.figureList.get(i) instanceof Point) {
                System.out.println(picture.figureList.get(i).toString());
            }
        }

        System.out.println("Odcinki:");
        for (int i = 0; i < picture.figureList.size(); i++) {
            if (picture.figureList.get(i) instanceof Section) {
                System.out.println(picture.figureList.get(i).toString());
            }
        }

        System.out.println("Okregi:");
        for (int i = 0; i < picture.figureList.size(); i++) {
            if (picture.figureList.get(i) instanceof Circle) {
                System.out.println(picture.figureList.get(i).toString());
            }
        }

        System.out.println("Trojkaty:");
        for (int i = 0; i < picture.figureList.size(); i++) {
            if (picture.figureList.get(i) instanceof Triangle) {
                System.out.println(picture.figureList.get(i).toString());
            }
        }
    }

    private static void moveElement(Picture picture) {
        System.out.println("Wybierz figure do przesuniecia:");
        for (int i = 0; i < picture.figureList.size(); i++) {
            System.out.println(i + " - " + picture.figureList.get(i).toString());
        }
        Scanner userInput = new Scanner(System.in);
        int option = userInput.nextInt();
        System.out.println("Podaj wspolrzedna X do przesuniecia:");
        double movingX = userInput.nextDouble();
        System.out.println("Podaj wspolrzedna Y do przesuniecia:");
        double movingY = userInput.nextDouble();
        picture.figureList.get(option).move(movingX, movingY);
    }

    private static void totalArea(Picture picture) {
        if (picture.figureList.size() == 0) {
            System.out.println("Brak elementow posiadajacych pole powierzchni");
        } else {
            System.out.println("Suma pol wszystkich elementow obrazu:");
            double totalArea = 0;
            for (int i = 0; i < picture.figureList.size(); i++) {
                totalArea += picture.figureList.get(i).getArea();
            }
        }
    }

    private static void exitApp() {
        System.exit(0);
    }

    private static void displayMenu() {
        System.out.println("************* MENU *************");
        System.out.println("1 - Dodaj punkt do obrazu");
        System.out.println("2 - Dodaj odcinek do obrazu");
        System.out.println("3 - Dodaj okrag do obrazu");
        System.out.println("4 - Dodaj trojkat do obrazu");
        System.out.println("5 - Wyswietl wszystkie elementy obrazu");
        System.out.println("6 - Przesun element obrazu");
        System.out.println("7 - Wyswietl sume pol powierzchni elementow obrazu");
        System.out.println("8 - Dodaj okrag do unikalnego obrazu");
        System.out.println("9 - Dodaj okrag do standaryzowanego obrazu");
        System.out.println("10 - Wyswietl standaryzowany obraz");
        System.out.println("11 - Wyswietl unikalny obraz");
        System.out.println("w - Wyjscie");
    }
}

