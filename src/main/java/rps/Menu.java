package rps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void start() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            this.printMenu();
            o = sc.nextLine();
            this.option(o);
        } while (!o.equals("x"));
    }

    private void printMenu() {
        this.cls();
        System.out.println("Choose option:");
        System.out.println("(s) Start new game");
//        System.out.println("(l) Load game");
        System.out.println("(x) Exit");
        System.out.println();
        System.out.print("Type letter and press Enter: ");
    }

    private void option(String o) {
        switch (o) {
            case "s":
                newGame();
                break;
//            case "l":
//                break;
            case "x":
                break;
            default:
                break;
        }
    }

    private void newGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Starting new game:\n");
        System.out.println("Difficulty:");
        System.out.println("(1) Easy");
        System.out.println("(2) Medium");
        System.out.println("(3) Hard");
        System.out.println("Your choice: ");
        int difficulty;
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
        System.out.println("\nPoints to win: ");
        int pointsToWin;
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
        new Game(difficulty, pointsToWin);
    }

    public static void cls() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static void waitForEnter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press \"Enter\" to continue.");
        sc.nextLine();
    }

}
