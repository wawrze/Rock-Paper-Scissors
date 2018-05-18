package rps;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static int getPlayersFigure(FiguresSet figuresSet){
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Your choice: ");
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Press \"Enter\" to continue.");
        sc.nextLine();
    }

    public static void printMenu() {
        cls();
        System.out.println("Choose option:");
        System.out.println("(s) Start new game");
//        System.out.println("(l) Load game");
        System.out.println("(x) Exit");
        System.out.println();
    }

    public static void cls() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static String getMenuOption(List<String> possibleOptions){
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Starting new game:\n");
        System.out.println("Difficulty:");
        System.out.println("(1) Easy");
        System.out.println("(2) Medium");
        System.out.println("(3) Hard");
        System.out.println("Your choice: ");
        try {
            difficulty = sc.nextInt();
        }
        catch(InputMismatchException e){
            difficulty = 0;
        }
        sc.nextLine();
        while(difficulty != 1 && difficulty != 2 && difficulty != 3){
            System.out.println("Incorrect choice!");
            System.out.println("Enter 1, 2 or 3: ");
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
        Scanner sc = new Scanner(System.in);
        int pointsToWin;
        System.out.println("\nPoints to win: ");
        try {
            pointsToWin = sc.nextInt();
        }
        catch(InputMismatchException e){
            pointsToWin = 0;
        }
        sc.nextLine();
        while(pointsToWin <= 0){
            System.out.println("Incorrect choice!");
            System.out.println("Enter positive number: ");
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
        figuresSet.getFigures().stream()
                .forEach(figure -> System.out.println("(" + figure.getNumber() + ") " + figure.getName()));
    }

    public static void printTurnResult(Turn turn){
        System.out.println(turn);
        waitForEnter();
    }

    public static FiguresSet getFiguresSet(){
        return new FiguresSet();
    }

}