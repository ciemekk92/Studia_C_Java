// Author: Przemyslaw Reducha

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

public class Application {
    static abstract class Figure {
        private String label;
        public void move(double dx, double dy) {}
        public String toString() {return "";};
        public double getArea() {return 0;};
        public String getLabel() {
            if (label != null) {
                return label;
            }
            return "Brak etykiety";
        }
        public Point getCentroid() {
            return new Point();
        };

        public void setLabel(String label) {
            this.label = label;
        }
    }

    static class Point extends Figure implements Serializable{
        private double x, y;

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

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
            return "(" + x + " , " + y + ") Etykieta:" + getLabel();
        }

        // Metoda getArea
        public double getArea() {
            return 0;
        }

        public Point getCentroid() {
            return new Point(x, y);
        }
    }

    static class Section extends Figure implements Serializable{
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
            return p1.toString() + " " + p2.toString() + " Etykieta: " + getLabel();
        }

        // Metoda getArea
        public double getArea() {
            return 0;
        }

        public Point getCentroid () {
            return new Point(((p1.x + p2.x)/2), ((p1.y + p2.y)/2));
        }
    }

    static class Circle extends Figure implements Fillable, Scalable, Serializable{
        private Point o;
        private double r;
        private int color;

        public double getPerimeter() {
            return 2 * 3.14 * r;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

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
            return o.toString() + " r = " + r + " Etykieta: " + getLabel() + " Kolor: " + getColor() + " Obwod: " + getPerimeter();
        }

        // Metoda getArea
        public double getArea() {
            return 3.14 * r * r;
        }

        public Point getCentroid() {
            return o;
        }

        @Override
        public void fill(int color) {
            setColor(color);
        }

        @Override
        public void scalePerimeter(double k) {
            r = k * r;
        }
    }

    static class Triangle extends Figure implements Fillable, Scalable, Serializable{
        private Point p1, p2, p3;
        private int color;

        public int getColor() {
            return color;
        }

        public double getPerimeter() {
             double side1 = Math.sqrt((Math.pow(p2.x - p1.x, 2))+(Math.pow(p2.y - p1.y, 2)));
             double side2 = Math.sqrt((Math.pow(p3.x - p2.x, 2))+(Math.pow(p3.y - p2.y, 2)));
             double side3 = Math.sqrt((Math.pow(p3.x - p1.x, 2))+(Math.pow(p3.y - p1.y, 2)));

            return side1 + side2 + side3;
        }

        public void setColor(int color) {
            this.color = color;
        }

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
            return p1.toString() + " " + p2.toString() + " " + p3.toString() + ".  Etykieta: " + getLabel() + " Kolor: " + getColor() + " Obwod: " + getPerimeter();
        }

        // Metoda getArea
        public double getArea() {
            return ((p1.x * (p2.y - p3.y)) + (p2.x * (p3.y - p1.y)) + (p3.x * (p1.y - p2.y))) / 2;
        }

        public Point getCentroid() {
            return new Point((0.33 * (p1.x + p2.x + p3.x)), (0.33 * (p1.y + p2.y + p3.y)));
        }

        @Override
        public void fill(int color) {
            setColor(color);
        }

        @Override
        public void scalePerimeter(double k) {
            p1.x = p1.x*k;
            p1.y = p1.y*k;
            p2.x = p2.x*k;
            p2.y = p2.y*k;
            p3.x = p3.x*k;
            p3.y = p3.y*k;
        }
    }

    static class LabelComparator implements Comparator<Figure> {
        @Override
        public int compare(Figure o1, Figure o2) {
            return o1.getLabel().compareTo(o2.getLabel());
        }
    }

    static class ClassComparator implements Comparator<Figure> {
        @Override
        public int compare(Figure o1, Figure o2) {
            return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
        }
    }

    static class CentroidComparator implements Comparator<Figure> {
        @Override
        public int compare(Figure o1, Figure o2) {
            double testX1 = o1.getCentroid().getX();
            double testX2 = o2.getCentroid().getX();
            double testY1 = o1.getCentroid().getY();
            double testY2 = o2.getCentroid().getY();
            double length1 = Math.sqrt((Math.pow(0 - testX1, 2)) + (Math.pow(0 - testY1, 2)));
            double length2 = Math.sqrt((Math.pow(0 - testX2, 2)) + (Math.pow(0 - testY2, 2)));
            return Double.compare(length1, length2);
        }
    }

    static class Picture implements Serializable {
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

        public String toStringSortedByLabel() {
            List<Figure> list = new ArrayList<>(figureList);
            Collections.sort(list, new LabelComparator());
            return list.toString();
        }

        public String toStringSortedByClassName() {
            List<Figure> list = new ArrayList<>(figureList);
            Collections.sort(list, new ClassComparator());
            return list.toString();
        }

        public String toStringSortedByDistanceFromOrigin() {
            List<Figure> list = new ArrayList<>(figureList);
            Collections.sort(list, new CentroidComparator());
            return list.toString();
        }

        public void fillObjects(int color) {
            for (Figure figure:figureList) {
                if (figure instanceof Fillable) {
                    ((Fillable) figure).fill(color);
                }
            }
        }

        public void scaleObjects(double k) {
            for (Figure figure:figureList) {
                if (figure instanceof Scalable) {
                    ((Scalable) figure).scalePerimeter(k);
                }
            }
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

    public interface Fillable {
        void fill (int color);
    }

    public interface Scalable {
        void scalePerimeter (double k);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
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
                case "12":
                    addColorToPicture(mainPicture);
                    break;
                case "13":
                    scalePicture(mainPicture);
                    break;
                case "14":
                    sortByClass(mainPicture);
                    break;
                case "15":
                    sortByLabel(mainPicture);
                    break;
                case "16":
                    sortByCentroid(mainPicture);
                    break;
                case "17":
                    saveToFile(mainPicture);
                    break;
                case "18":
                    readFromFile();
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
        System.out.println("Podaj etykiete");
        pointInput.nextLine();
        String label = pointInput.nextLine();
        Point point = new Point(pointX, pointY);
        point.setLabel(label);
        picture.add(point);
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
        System.out.println("Podaj etykiete");
        sectionInput.nextLine();
        String label = sectionInput.nextLine();
        Section section = new Section(new Point(pointX1, pointY1), new Point(pointX2, pointY2));
        section.setLabel(label);
        picture.add(section);
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
        System.out.println("Podaj etykiete:");
        triangleInput.nextLine();
        String label = triangleInput.nextLine();
        Triangle triangle = new Triangle(new Point(triangleX1, triangleY1), new Point (triangleX2, triangleY2), new Point(triangleX3, triangleY3));
        triangle.setLabel(label);
        picture.add(triangle);
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

    private static void addColorToPicture(Picture picture) {
        System.out.println("Podaj kod hex koloru np. 000 (bez #)");
        Scanner colorInput = new Scanner(System.in);
        int input = colorInput.nextInt();
        picture.fillObjects(input);
        System.out.println("Dodano kolor");
    }

    private static void scalePicture(Picture picture) {
        System.out.println("Podaj wspolczynnik skalowania k:");
        Scanner scaleInput = new Scanner(System.in);
        double input = scaleInput.nextDouble();
        picture.scaleObjects(input);
        System.out.println("Przeskalowano obwody elementow");
    }

    private static void sortByClass(Picture picture) {
        System.out.println("Lista posortowana po klasach:");
        picture.toStringSortedByClassName();
    }

    private static void sortByLabel(Picture picture) {
        System.out.println("Lista posortowana po etykietach:");
        picture.toStringSortedByLabel();
    }

    private static void sortByCentroid(Picture picture) {
        System.out.println("Lista posortowana po odleglosci od srodka obrazu:");
        picture.toStringSortedByDistanceFromOrigin();
    }

    private static void saveToFile(Picture picture) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("obraz.dat"));
        output.writeObject(picture);
        output.close();
        System.out.println("Zapisano do pliku");
    }

    private static void readFromFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("obraz.dat"));
        Picture picture1 = (Picture) input.readObject();
        input.close();
        System.out.println(picture1);
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
        System.out.println("12 - Dodaj kolor do wszystkich elementow");
        System.out.println("13 - Skaluj wszystkie elementy");
        System.out.println("14 - Sortuj po nazwie klasy");
        System.out.println("15 - Sortuj po etykiecie");
        System.out.println("16 - Sortuj po odleglosci od srodka obrazu");
        System.out.println("17 - Zapisz obraz do pliku");
        System.out.println("18 - Odczytaj obraz z pliku");
        System.out.println("w - Wyjscie");
    }
}

