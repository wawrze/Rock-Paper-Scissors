package rps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Figures {

    private final List<Figure> figuresList;

    public Figures(){
        this.figuresList = new ArrayList<>();
        this.figuresList.add(new Figure(1,"Rock",new int[] {3},new int [] {2}));
        this.figuresList.add(new Figure(2,"Paper",new int[] {1},new int [] {3}));
        this.figuresList.add(new Figure(3,"Scissors",new int[] {2},new int [] {1}));
    }

    public boolean isPlayerWinning(Figure playersFigure, Figure computersFigure){
        long counter = Arrays.stream(computersFigure.getIsBeaten())
                .filter(isBeaten -> isBeaten == playersFigure.getNumber())
                .count();
        return counter == 1 ? true : false;
    }

    public ArrayList<Figure> getFigures(){
        return new ArrayList<>(figuresList);
    }

/*    public void addFigure(int number, Figure figure){
        this.figuresList.add(figure);
    }

    public boolean removeFigure(int number){
        return this.removeFigure(number);
    }*/

}
