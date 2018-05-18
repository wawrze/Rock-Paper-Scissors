package rps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Turn {

    private final Figure playersChoice;
    private final Figure computersChoice;
    private final boolean isPlayerWinner;
    private final boolean isDraw;

    public Turn(final Figure playersChoice,int difficultyLevel, FiguresSet figuresSet){
        this.playersChoice = playersChoice;
        this.computersChoice = figuresSet.getFigures().get(computerMove(playersChoice.getNumber(), difficultyLevel, figuresSet) - 1);
        if(playersChoice.equals(computersChoice))
            isDraw = true;
        else
            isDraw = false;
        this.isPlayerWinner = figuresSet.isPlayerWinning(playersChoice,computersChoice);
    }

    public Figure getPlayersChoice() {
        return playersChoice;
    }

    public Figure getComputersChoice() {
        return computersChoice;
    }

    public boolean isPlayerWinner() {
        return isPlayerWinner;
    }

    public boolean isDraw() {
        return isDraw;
    }

    private int computerMove(int playersChoice, int difficultyLevel, FiguresSet figuresSet){
        Random r = new Random();
        List<Figure> figuresList = figuresSet.getFigures();
        List<Figure> modifiedList = new ArrayList<>(figuresList);
        if(difficultyLevel == 1){
            for(Figure f : figuresList)
                for(int i : f.getIsBeaten())
                    if(i == playersChoice)
                        modifiedList.add(f);
        }
        else if(difficultyLevel == 3)
            for(Figure f2 : figuresList)
                for(int i2 : f2.getBeats())
                    if(i2 == playersChoice)
                        modifiedList.add(f2);
        return modifiedList.get(r.nextInt(modifiedList.size())).getNumber();
    }

    @Override
    public String toString(){
        String winner;
        if (isDraw)
            winner = ", no one wins.";
        else if(isPlayerWinner)
            winner = ", player wins.";
        else
            winner = ", computer wins.";
        return "player: " + playersChoice + ", computer: " + computersChoice + winner;
    }

}