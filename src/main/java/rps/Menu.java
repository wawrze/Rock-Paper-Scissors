package rps;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static List<String> possibleOptions;

    public Menu(){
        possibleOptions = new LinkedList<>();
        possibleOptions.add("s");
//        possibleOptions.add("l");
        possibleOptions.add("x");
    }

    public void start() {
        String o;
        do {
            UserInterface.printMenu();
            o = UserInterface.getMenuOption(possibleOptions);
            this.option(o);
        }while(!o.equals("x"));
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
        int difficulty = UserInterface.getGameDifficulty();
        int pointsToWin = UserInterface.getPointsToWin();
        FiguresSet figuresSet = UserInterface.getFiguresSet();
        new Game(difficulty, pointsToWin, figuresSet);
    }

}