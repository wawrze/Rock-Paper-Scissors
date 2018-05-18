package rps;


import java.util.ArrayList;
import java.util.List;

public class Game {

    private int difficultyLevel;
    private int pointsToWin;
    private int computerPoints;
    private int playerPoints;
    private List<Turn> turnsHistory;
    FiguresSet figuresSet;

    public Game(int difficultyLevel, int pointsToWin, FiguresSet figuresSet) {
        this.difficultyLevel = difficultyLevel;
        this.pointsToWin = pointsToWin;
        this.figuresSet = figuresSet;
        this.computerPoints = 0;
        this.playerPoints = 0;
        this.turnsHistory = new ArrayList<>();
        this.turnsHistory.add(null);
        while(computerPoints < this.pointsToWin && playerPoints < this.pointsToWin) {
            turn();
        }
    }

    private void turn(){
        UserInterface.printWaitForMove(this.playerPoints, this.computerPoints, this.figuresSet);
        int playersChoice = UserInterface.getPlayersFigure(figuresSet);
        Turn turn =  new Turn(figuresSet.getFigures().get(playersChoice - 1),difficultyLevel, figuresSet);
        if(!turn.isDraw()) {
            playerPoints += turn.isPlayerWinner() ? 1 : 0;
            computerPoints += turn.isPlayerWinner() ? 0 : 1;
        }
        this.turnsHistory.add(turn);
        UserInterface.printTurnResult(turn);
    }

}