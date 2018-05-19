package rps;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static Scanner sc = new Scanner(System.in);

    public static int getPlayersFigure(FiguresSet figuresSet){
        int choice;
        System.out.println("Your choice (enter figure number): ");
        try {
            choice = sc.nextInt();
        }
        catch(InputMismatchException e){
            choice = 0;
        }
        sc.nextLine();
        while(choice <= 0 || choice > figuresSet.getFigures().size()){
            System.out.println("Incorrect choice, choose again: ");
            try {
                choice = sc.nextInt();
            }
            catch(InputMismatchException e){
                choice = 0;
            }
            sc.nextLine();
        }
        return choice;
    }

    public static void waitForEnter() {
        System.out.println("Press \"Enter\" to continue.");
        sc.nextLine();
    }

    public static void printMenu() {
        cls();
        System.out.println("Main menu");
        System.out.println("Choose option:");
        System.out.println("(s) Start new game");
//        System.out.println("(l) Load game");
        System.out.println("(c) Figures set constructor");
        System.out.println("(x) Exit");
        System.out.println();
    }

    public static void cls() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static String getMenuOption(List<String> possibleOptions){
        String option;
        System.out.print("Type letter and press Enter: ");
        option = sc.nextLine();
        while (!possibleOptions.contains(option)){
            System.out.print("\nIncorrect choice! Type letter and press Enter: ");
            option = sc.nextLine();
        }
        return option;
    }

    public static int getGameDifficulty(){
        int difficulty;
        System.out.println("Starting new game:\n");
        System.out.println("Difficulty:");
        System.out.println("(1) Easy");
        System.out.println("(2) Medium");
        System.out.println("(3) Hard");
        System.out.print("Your choice: ");
        try {
            difficulty = sc.nextInt();
        }
        catch(InputMismatchException e){
            difficulty = 0;
        }
        sc.nextLine();
        while(difficulty != 1 && difficulty != 2 && difficulty != 3){
            System.out.println("Incorrect choice!");
            System.out.print("Enter 1, 2 or 3: ");
            try {
                difficulty = sc.nextInt();
            }
            catch(InputMismatchException e){
                difficulty = 0;
            }
            sc.nextLine();
        }
        return difficulty;
    }

    public static int getPointsToWin(){
        int pointsToWin;
        System.out.print("\nPoints to win: ");
        try {
            pointsToWin = sc.nextInt();
        }
        catch(InputMismatchException e){
            pointsToWin = 0;
        }
        sc.nextLine();
        while(pointsToWin <= 0){
            System.out.println("Incorrect choice!");
            System.out.print("Enter positive number: ");
            try {
                pointsToWin = sc.nextInt();
            }
            catch(InputMismatchException e){
                pointsToWin = 0;
            }
            sc.nextLine();
        }
        return pointsToWin;
    }

    public static void printWaitForMove(int playerPoints, int computerPoints, FiguresSet figuresSet){
        cls();
        System.out.print("Player: " + playerPoints + "\t\t");
        System.out.println("Computer: " + computerPoints);
        System.out.println("Choose figure:");
        printFiguresList(figuresSet.getFigures());
    }

    public static void printTurnResult(Turn turn){
        System.out.println(turn);
        waitForEnter();
    }

    public static void printFiguresSetConstructorMenu(String name, int size,List<Figure> figures,boolean isListComplete){
        cls();
        System.out.println("FIGURES SET CONSTRUCTOR");
        if(name.isEmpty())
            System.out.println("You have to enter figures set name:");
        else if(size == 0)
            System.out.println("You have to enter figures quantity:");
        else {
            String s = isListComplete ? " (complete)" : " (incomplete)";
            System.out.println("Figures set \"" + name + "\"" + s);
            System.out.println("Figures quantity: " + size);
            System.out.println("Figures: ");
            printFiguresList(figures);
            System.out.println("(a) add new figure");
            System.out.println("(r) remove figure");
            System.out.println("(p) replace existing figure");
            System.out.println("(s) save figures set and exit to main menu");
            System.out.println("(x) exit to main menu without changes");
        }
    }

    private static void printFiguresList(List<Figure> figures){
        if(figures.isEmpty()){
            System.out.println("\tNo figures");
            return;
        }
        figures.stream()
                .forEach(figure -> System.out.println(printFigure(figure,figures)));
    }

    private static String printFigure(Figure figure, List<Figure> figures){
        String isBeaten = "";
        if(figure.getIsBeaten() != null && figure.getIsBeaten().length > 0) {
            isBeaten += figures.get(figure.getIsBeaten()[0] - 1);
            if(figure.getIsBeaten().length > 1) {
                for (int i = 1; i < figure.getIsBeaten().length; i++) {
                    isBeaten += ", " + figures.get(figure.getIsBeaten()[i] - 1);
                }
            }
        }
        String beats = "";
        if(figure.getBeats() != null && figure.getBeats().length > 0) {
            beats += figures.get(figure.getBeats()[0] - 1);
            if(figure.getBeats().length > 1) {
                for (int i = 1; i < figure.getBeats().length; i++) {
                    beats += ", " + figures.get(figure.getBeats()[i] - 1);
                }
            }
        }
        return "\t" + figure.getNumber() + ". " + figure.getName() + " (is beaten by: " +
                isBeaten + "; beats: " + beats + ")";
    }

    public static int getFiguresQuantity(){
        int figuresQuantity;
        System.out.print("\nEnter figures quantity (odd number greater than 1): ");
        try {
            figuresQuantity = sc.nextInt();
        }
        catch(InputMismatchException e){
            figuresQuantity = 0;
        }
        sc.nextLine();
        while(figuresQuantity < 3 || (figuresQuantity % 2) == 0){
            System.out.println("Incorrect choice!");
            System.out.print("Enter odd number greater than 1: ");
            try {
                figuresQuantity = sc.nextInt();
            }
            catch(InputMismatchException e){
                figuresQuantity = 0;
            }
            sc.nextLine();
        }
        return figuresQuantity;
    }

    public static String getFiguresSetName(){
        String s;
        System.out.println("Enter figures set name: ");
        s = sc.nextLine();
        while(s.isEmpty()){
            System.out.println("You have to enter some name!");
            System.out.print("Enter figures set name: ");
            s = sc.nextLine();
        }
        return s;
    }

    public static int removeFigure(int size){
        if(size == 0){
            System.out.println("No figures to remove!");
            waitForEnter();
            return 0;
        }
        System.out.print("\nWhich figure you want to remove? Enter number: ");
        return getFigureIndex(size);
    }

    public static int replaceFigure(int size){
        if(size == 0){
            System.out.println("No figures to replace!");
            waitForEnter();
            return 0;
        }
        System.out.print("\nWhich figure you want to replace? Enter number: ");
        return getFigureIndex(size);
    }

    private static int getFigureIndex(int size){
        int figureIndex;
        try {
            figureIndex = sc.nextInt();
        }
        catch(InputMismatchException e){
            figureIndex = 0;
        }
        sc.nextLine();
        while(figureIndex <= 0 || figureIndex > size){
            System.out.println("Incorrect choice!");
            System.out.print("Enter number from 1 to " + size + ": ");
            try {
                figureIndex = sc.nextInt();
            }
            catch(InputMismatchException e){
                figureIndex = 0;
            }
            sc.nextLine();
        }
        return figureIndex;
    }

    public static void printNewFigureMenu(int number){
        cls();
        System.out.println("Adding new figure (number " + number + "):");
    }

    public static String getNewFigureName(){
        String s;
        System.out.print("Enter name of new figure: ");
        s = sc.nextLine();
        while(s.isEmpty()){
            System.out.println("You have to enter some name!");
            System.out.print("Enter new figure name: ");
            s = sc.nextLine();
        }
        return s;
    }

    public static int[] getNewFigureIsBeaten(List<Figure> figuresList, String name){
        System.out.println("Enter which figures can beat " + name + ":");
        printFiguresList(figuresList);
        return getFiguresArray(figuresList.size());
    }

    public static int[] getNewFigureBeats(List<Figure> figuresList, String name){
        System.out.println("Enter which figures can be beaten by " + name + ":");
        printFiguresList(figuresList);
        return getFiguresArray(figuresList.size());
    }

    private static int[] getFiguresArray(int size){
        int[] array = new int[(size - 1) / 2];
        int counter = 1;
        for(int i = 0;i < ((size - 1) / 2);i++){
            System.out.print("Figure " + counter + "/" + ((size - 1) / 2) + ": ");
            try {
                array[i] = sc.nextInt();
                counter++;
            }
            catch(InputMismatchException e){
                array[i] = 0;
            }
            sc.nextLine();
            while(array[i] < 1 || array[i] > size){
                System.out.println("Incorrect choice!");
                System.out.print("Enter figure number from 1 to " + size + ": ");
                try {
                    array[i] = sc.nextInt();
                    counter++;
                }
                catch(InputMismatchException e){
                    array[i] = 0;
                }
                sc.nextLine();
            }
        }
        return array;
    }

    public static FiguresSet getFiguresSet(){
        return new FiguresSet();
    }

}