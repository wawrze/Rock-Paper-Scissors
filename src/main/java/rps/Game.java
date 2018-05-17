package rps;

import java.util.*;

public class Game {

    private int difficultyLevel;
    private int pointsToWin;
    private int computerPoints;
    private int playerPoints;
    private List<Turn> turnsHistory;
    Figures figures;

    public Game(int difficultyLevel, int pointsToWin) {
        this.difficultyLevel = difficultyLevel;
        this.pointsToWin = pointsToWin;
        this.computerPoints = 0;
        this.playerPoints = 0;
        this.turnsHistory = new ArrayList<>();
        this.turnsHistory.add(null);
        figures = new Figures();
        while(computerPoints < this.pointsToWin && playerPoints < this.pointsToWin) {
            turn();
        }
    }

    private void turn(){
        Menu.cls();
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.print("Player: " + playerPoints + "\t\t");
        System.out.println("Computer: " + computerPoints);
        System.out.println("Choose figure:");
        figures.getFigures().stream()
                .forEach(figure -> System.out.println("(" + figure.getNumber() + ") " + figure.getName()));
        System.out.println("Your choice: ");
        try {
            choice = sc.nextInt();
        }
        catch(InputMismatchException e){
            choice = 0;
        }
        sc.nextLine();
        while(choice <= 0 || choice > figures.getFigures().size()){
            System.out.println("Incorrect choice, choose again: ");
            try {
                choice = sc.nextInt();
            }
            catch(InputMismatchException e){
                choice = 0;
            }
            sc.nextLine();
        }
        Turn turn =  new Turn(figures.getFigures().get(choice - 1),difficultyLevel,figures);
        if(!turn.isDraw()) {
            playerPoints += turn.isPlayerWinner() ? 1 : 0;
            computerPoints += turn.isPlayerWinner() ? 0 : 1;
        }
        this.turnsHistory.add(turn);
        System.out.println(turn);
        Menu.waitForEnter();
    }

}