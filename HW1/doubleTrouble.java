public class doubleTrouble {

    //Private Properties 

    private boolean winner = false;
    private boolean player; // When true, player is on their turn 

    /**
     *  Double Trouble Constructer and starts the game
     */
    doubleTrouble(){
        startingGameConditions(); // Ask for game conditions
        while(!winner){
            takeTurn(); 
            checkWinCondition();
        }

    }


    private void startingGameConditions(){
        // @TODO 
    }

    private boolean checkWinCondition(){
        // @TODO 
        return false; 
    }

    private void takeTurn(){
        // @TODO 
    }



}
