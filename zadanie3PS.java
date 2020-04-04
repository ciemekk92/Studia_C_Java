// Author: Przemyslaw Reducha
import java.util.Scanner;
import java.util.ArrayList;

public class Application {
    static class Point {
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
        public int getArea() {
            return 0;
        }
    }

    static class Section {
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
        public int getArea() {
            return 0;
        }
    }

    static class Circle {
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

    static class Triangle {
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
        private ArrayList<Point> pointList = new ArrayList<>();
        private ArrayList<Section> sectionList = new ArrayList<>();
        private ArrayList<Circle> circleList = new ArrayList<>();
        private ArrayList<Triangle> triangleList = new ArrayList<>();

        public void addPoint(double x, double y) {
            pointList.add(new Point(x, y));
        }

        public void addSection(double x1, double y1, double x2, double y2) {
            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            sectionList.add(new Section(p1, p2));
        }

        public void addCircle(double x, double y, double r) {
            Point p1 = new Point(x, y);
            circleList.add(new Circle(p1, r));
        }

        public void addTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
            Point p1 = new Point (x1, y1);
            Point p2 = new Point (x2, y2);
            Point p3 = new Point (x3, y3);
            triangleList.add(new Triangle(p1, p2, p3));
        }
    }

    public static void main(String[] args) {
        Picture mainPicture = new Picture();
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
        picture.addPoint(pointX, pointY);
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
        picture.addSection(pointX1, pointY1, pointX2, pointY2);
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
        picture.addCircle(circleX, circleY, radius);
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
        picture.addTriangle(triangleX1, triangleY1, triangleX2, triangleY2, triangleX3, triangleY3);
        System.out.println("Dodano trojkat.");
    }

    private static void printPicture(Picture picture) {
        System.out.println("Punkty:");
        for ( int i = 0; i < picture.pointList.size(); i++) {
            System.out.println(picture.pointList.get(i).toString());
        }
        System.out.println("Odcinki:");
        for (int i = 0; i < picture.sectionList.size(); i++) {
            System.out.println(picture.sectionList.get(i).toString());
        }
        System.out.println("Okregi:");
        for (int i = 0; i < picture.circleList.size(); i++) {
            System.out.println(picture.circleList.get(i).toString());
        }
        System.out.println("Trojkaty:");
        for (int i = 0; i < picture.triangleList.size(); i++) {
            System.out.println(picture.triangleList.get(i).toString());
        }
    }

    private static void moveElement(Picture picture) {
        Scanner userInput = new Scanner(System.in);
        String option;
        displayMoveMenu();
        do {
            option = userInput.next();
            switch(option) {
                case "1":
                    movePoint(picture);
                    break;
                case "2":
                    moveSection(picture);
                    break;
                case "3":
                    moveCircle(picture);
                    break;
                case "4":
                    moveTriangle(picture);
                    break;
                case "w":
                    displayMenu();
                    break;
                default:
                    System.out.println("Brak opcji " + option);
                    break;
            }
        } while (!"w".equals(option));
    }

    private static void displayMoveMenu() {
        System.out.println("Wybierz rodzaj elementu do przesuniecia:");
        System.out.println("1 - Punkt");
        System.out.println("2 - Odcinek");
        System.out.println("3 - Okrag");
        System.out.println("4 - Trojkat");
        System.out.println("w - Wroc do poprzedniego menu");
    }

    private static void movePoint(Picture picture) {
        if (picture.pointList.size() == 0) {
            System.out.println("Brak punktow w obrazie.");
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Wybierz punkt do przesuniecia:");
            for (int i = 0; i < picture.pointList.size(); i++) {
                System.out.println(i + " - " + picture.pointList.get(i).toString());
            }
            int index = userInput.nextInt();
            System.out.println("Podaj wartosc X do przesuniecia:");
            double movingX = userInput.nextDouble();
            System.out.println("Podaj wartosc Y do przesuniecia:");
            double movingY = userInput.nextDouble();
            picture.pointList.get(index).move(movingX, movingY);
            System.out.println("Przesunieto punkt.");
            displayMoveMenu();
        }
    }

    private static void moveSection(Picture picture) {
        if (picture.sectionList.size() == 0) {
            System.out.println("Brak odcinkow w obrazie.");
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Wybierz odcinek do przesuniecia:");
            for (int i = 0; i < picture.sectionList.size(); i++) {
                System.out.println(i + " - " + picture.sectionList.get(i).toString());
            }
            int index = userInput.nextInt();
            System.out.println("Podaj wartosc X przesuniecia:");
            double movingX = userInput.nextDouble();
            System.out.println("Podaj wartosc Y przesuniecia:");
            double movingY = userInput.nextDouble();
            picture.sectionList.get(index).move(movingX, movingY);
            System.out.println("Przesunieto odcinek.");
            displayMoveMenu();
        }
    }

    private static void moveCircle(Picture picture) {
        if (picture.circleList.size() == 0) {
            System.out.println("Brak okregow w obrazie.");
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Wybierz okrag do przesuniecia:");
            for (int i = 0; i < picture.circleList.size(); i++) {
                System.out.println(i + " - " + picture.circleList.get(i).toString());
            }
            int index = userInput.nextInt();
            System.out.println("Podaj wartosc X przesuniecia:");
            double movingX = userInput.nextDouble();
            System.out.println("Podaj wartosc Y przesuniecia:");
            double movingY = userInput.nextDouble();
            picture.circleList.get(index).move(movingX, movingY);
            System.out.println("Przesunieto okrag.");
            displayMoveMenu();
        }
    }

    private static void moveTriangle(Picture picture) {
        if (picture.triangleList.size() == 0) {
            System.out.println("Brak trojkatow w obrazie.");
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Wybierz trojkat do przesuniecia:");
            for (int i = 0; i < picture.triangleList.size(); i++) {
                System.out.println(i + " - " + picture.triangleList.get(i).toString());
            }
            int index = userInput.nextInt();
            System.out.println("Podaj wartosc X przesuniecia:");
            double movingX = userInput.nextDouble();
            System.out.println("Podaj wartosc Y przesuniecia:");
            double movingY = userInput.nextDouble();
            picture.triangleList.get(index).move(movingX, movingY);
            System.out.println("Przesunieto trojkat.");
            displayMoveMenu();
        }
    }

    private static void totalArea(Picture picture) {
        if ((picture.circleList.size() == 0) && (picture.triangleList.size() == 0)) {
            System.out.println("Brak elementow posiadajacych pole powierzchni");
        } else {
            System.out.println("Suma pol wszystkich elementow obrazu:");
            double circleArea = 0;
            double triangleArea = 0;
            for (int i = 0; i < picture.circleList.size(); i++) {
                circleArea = circleArea + picture.circleList.get(i).getArea();
            }
            for (int i = 0; i < picture.triangleList.size(); i++) {
                triangleArea = triangleArea + picture.triangleList.get(i).getArea();
            }
            System.out.println(triangleArea + circleArea);
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
        System.out.println("w - Wyjscie");
    }
}
