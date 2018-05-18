package rps;

import org.junit.Assert;
import org.junit.Test;

public class TurnTestSuite {

    private static int numberOfGames = 10000;

    @Test
    public void testEasyGame(){
        //Given
        FiguresSet figuresSet = new FiguresSet();
        int numberOfWins = 0;
        double winsShare;
        Turn turn;
        //When
        for(int i = 0;i < numberOfGames;i++) {
            turn = new Turn(figuresSet.getFigures().get(0), 1, figuresSet);
            numberOfWins += turn.isPlayerWinner() ? 1 : 0;
        }
        winsShare = (double) numberOfWins / (double) numberOfGames;
        //Then
        Assert.assertEquals(0.5,winsShare,0.05);
    }

    @Test
    public void testMediumGame(){
        //Given
        FiguresSet figuresSet = new FiguresSet();
        int numberOfWins = 0;
        double winsShare;
        Turn turn;
        //When
        for(int i = 0;i < numberOfGames;i++) {
            turn = new Turn(figuresSet.getFigures().get(0), 2, figuresSet);
            numberOfWins += turn.isPlayerWinner() ? 1 : 0;
        }
        winsShare = (double) numberOfWins / (double) numberOfGames;
        //Then
        Assert.assertEquals(0.375,winsShare,0.05);
    }

    @Test
    public void testHardGame(){
        //Given
        FiguresSet figuresSet = new FiguresSet();
        int numberOfWins = 0;
        double winsShare;
        Turn turn;
        //When
        for(int i = 0;i < numberOfGames;i++) {
            turn = new Turn(figuresSet.getFigures().get(0), 3, figuresSet);
            numberOfWins += turn.isPlayerWinner() ? 1 : 0;
        }
        winsShare = (double) numberOfWins / (double) numberOfGames;
        //Then
        Assert.assertEquals(0.25,winsShare,0.05);
    }

    @Test
    public void testGetters(){
        //Given
        Turn turn;
        FiguresSet figuresSet = new FiguresSet();
        boolean result = true;
        //When
        for(int i = 0;i < numberOfGames;i++){
            turn = new Turn(figuresSet.getFigures().get(0), 2, figuresSet);
            if(turn.isDraw())
                result = result && turn.getComputersChoice().getNumber() == 1;
            else if(turn.isPlayerWinner())
                result = result && turn.getComputersChoice().getNumber() == 3;
            else
                result = result && turn.getComputersChoice().getNumber() == 2;
            result = result && turn.getPlayersChoice().getNumber() == 1;
        }
        result = result && figuresSet.getFigures().get(0).getName().equals("Rock");
        //Then
        Assert.assertTrue(result);
    }

    @Test
    public void testToString(){
        //Given
        Turn turn;
        FiguresSet figuresSet = new FiguresSet();
        boolean result = true;
        String s1 = "player: Rock, computer: Rock, no one wins.";
        String s2 = "player: Rock, computer: Paper, computer wins.";
        String s3 = "player: Rock, computer: Scissors, player wins.";
        //When
        for(int i = 0;i < numberOfGames;i++) {
            turn = new Turn(figuresSet.getFigures().get(0), 2, figuresSet);
            if (turn.isDraw())
                result = result && s1.equals("" + turn);
            else if (turn.isPlayerWinner())
                result = result && s3.equals("" + turn);
            else
                result = result && s2.equals("" + turn);
        }
        //Then
        Assert.assertTrue(result);
    }

}