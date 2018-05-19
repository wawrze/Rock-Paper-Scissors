package rps;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FiguresSetConstructor {

    private static List<String> possibleOptions;
    private static List<Figure> figuresList;
    private static String name = "";
    private static int figuresQuantity = 0;

    public static FiguresSet start(){
        figuresList = new ArrayList<>();
        setPossibleOptions();
        String o = "";
        do{
            UserInterface.printFiguresSetConstructorMenu(name, figuresQuantity, figuresList, isListComplete());
            if(name.isEmpty())
                name = UserInterface.getFiguresSetName();
            else if(figuresQuantity == 0)
                figuresQuantity = UserInterface.getFiguresQuantity();
            else {
                o = UserInterface.getMenuOption(possibleOptions);
                option(o);
            }
        }while(!o.equals("x") && !o.equals("s"));
        if(isListComplete())
            return null;
        else
            return new FiguresSet(name,figuresList);
    }

    private static void option(String o) {
        switch (o) {
            case "a":
                figuresList.add(getNewFigure(figuresList.size() + 1));
                break;
            case "r":
                int figureToRemoveIndex = UserInterface.removeFigure(figuresList.size());
                if(!figuresList.isEmpty())
                    figuresList.remove(figureToRemoveIndex - 1);
                break;
            case "p":
                int figureToReplaceIndex = UserInterface.replaceFigure(figuresList.size());
                Figure figure = getNewFigure(figureToReplaceIndex);
                figuresList.set(figureToReplaceIndex - 1, figure);
                break;
            case "x":
                figuresList = null;
                name = "";
                figuresQuantity = 0;
                break;
            default:
                break;
        }
    }

    private static Figure getNewFigure(int number){
        UserInterface.printNewFigureMenu(number);
        String name = UserInterface.getNewFigureName();
        int[] isBeaten;
        int[] beats;
        if(figuresList.size() == figuresQuantity) {
            isBeaten = UserInterface.getNewFigureIsBeaten(figuresList, name);
            for(int i = 0;i < isBeaten.length;i++) {
                Figure figure = figuresList.get(isBeaten[i] - 1);
                int[] tmpArray = addToArray(figure.getBeats(),number);
                figuresList.set(figure.getNumber()-1,new Figure(figure.getNumber(),figure.getName(),tmpArray,figure.getIsBeaten()));
            }
            beats = UserInterface.getNewFigureBeats(figuresList, name);
            for(int i = 0;i < beats.length;i++) {
                Figure figure = figuresList.get(beats[i] - 1);
                int[] tmpArray = addToArray(figure.getIsBeaten(),number);
                figuresList.set(figure.getNumber()-1,new Figure(figure.getNumber(),figure.getName(),figure.getBeats(),tmpArray));
            }
        }
        else {
            isBeaten = null;
            beats = null;
        }
        return new Figure(number, name, beats, isBeaten);
    }

    private static int[] addToArray(int[] tmpArray, int number){
        if(tmpArray == null)
            tmpArray = new int[]{number};
        else{
            boolean test = true;
            for(int j = 0;j < tmpArray.length;j++)
                if(tmpArray[j] == number)
                    test = false;
            if(test){
                int[] moreTmpArray = tmpArray;
                tmpArray = new int[moreTmpArray.length + 1];
                for(int j = 0;j < moreTmpArray.length;j++)
                    tmpArray[j] = moreTmpArray[j];
                tmpArray[moreTmpArray.length] = number;
            }
        }
        return tmpArray;
    }

    private static boolean isListComplete(){
        if(figuresList == null)
            return false;
        if(figuresList.isEmpty())
            return false;
        for(Figure f : figuresList)
            if(f.getBeats() == null || f.getIsBeaten() == null)
                return false;
        int figuresCount = 0;
        boolean result = true;
        for(Figure f : figuresList){
            result = result && f.getIsBeaten().length == f.getBeats().length && f.getBeats().length == ((figuresQuantity - 1) / 2);
            figuresCount++;
        }
        if(figuresCount != figuresQuantity)
            return false;
        return result;
    }

    private static void setPossibleOptions(){
        possibleOptions = new LinkedList<>();
        possibleOptions.add("a");
        possibleOptions.add("r");
        possibleOptions.add("p");
        possibleOptions.add("s");
        possibleOptions.add("x");
    }

}