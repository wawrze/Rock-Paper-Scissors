package rps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Menu {

    private List<String> possibleOptions;
    private List<FiguresSet> figuresSets;

    public Menu(){
        possibleOptions = new LinkedList<>();
        possibleOptions.add("s");
//        possibleOptions.add("l");
        possibleOptions.add("c");
        possibleOptions.add("x");
        figuresSets = new ArrayList<>();
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
            case "c":
                FiguresSet figuresSet = FiguresSetConstructor.start();
                if(figuresSet != null)
                    figuresSets.add(figuresSet);
                break;
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