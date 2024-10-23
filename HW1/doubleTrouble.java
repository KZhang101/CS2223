import java.util.Scanner;
public class doubleTrouble {

    //Private Properties 

    private boolean winner = false; // when true, the player has won
    private boolean player; // When true, player is on their turn 
    private boolean gameRunning = false; // When true, a game is being played

    private int[] colorCount; // Yellow, Orange, Green  
    private String[] colorOrder = new String[] {"Yellow", "Orange", "Green"}; // Might not need this


    private Scanner scanner = new Scanner(System.in);  
    /**
     *  Double Trouble Constructer and starts the game
     */
    doubleTrouble(){
        System.out.println("Welcome lowly human... Prepared to be DESTROYED");
        startingGameConditions(); // Ask for game conditions
        while(!gameRunning){
            takeTurn(); 
            checkWinCondition();
            break;
        }
    }

    private void startingGameConditions(){
        // @TODO 
        this.winner = false;
        System.out.println("Choose your fate human... (choose if you want to go first or not :)");
        System.out.println("(0) You'll make the first move");
        System.out.println("(1) I the all mighty computer will go first");
        this.player = scanner.nextInt() == 1 ? true : false;
        this.colorCount = new int[] {7, 5, 3};
        
    }

    private boolean checkWinCondition(){
        /**
         * Checks if there are no remaining colors, allowing the current user to win
         */
        for(int i = 0; i<this.colorOrder.length; i++){
            if (!(this.colorCount[i] == 0)) return false;
        }
        return true; 
    }

    private void takeTurn(){
        // @TODO 
        if(this.player){

        }
        else{

        }
    }


    /** Utilities */



}
