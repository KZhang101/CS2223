import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class doubleTrouble {

    //Private Properties 

    private boolean winner; // when true, the player has won
    private boolean player; // When true, player is on their turn 
    private boolean gameRunning; // When true, a game is being played
    private int currentGame; // Game Number 

    private int[] colorCount; // Yellow, Orange, Green  
    private ArrayList<String> colorOrder = new ArrayList<String>();
    private int[] winCount = new int[]{0, 0}; // play = 0, computer 1;
    

    private Scanner scanner = new Scanner(System.in);  
    
    doubleTrouble(){
        /**
         *  Double Trouble Constructer and starts the game
         */
        this.colorOrder.add("G");
        this.colorOrder.add("O");
        this.colorOrder.add("Y");

        System.out.println("Welcome lowly human... Prepared to be DESTROYED");
        this.currentGame = 1;
        System.out.println("Choose how many winning games the winner has to be: ");

        int neededWins = scanner.nextInt(); // Input check
        scanner.nextLine();
        while(neededWins <= 0){
            System.out.println("Choose a valid win count: ");
            neededWins = scanner.nextInt(); 
            scanner.nextLine();
        }

        while (this.winCount[this.winner ? 0: 1] < neededWins) { // checks if number of wins is met for both players 
            System.out.println("Game Number: " + this.currentGame);
            playGame(); // Plays a single game of Double Trouble
            currentGame++;
        }
        if(this.winCount[0] > this.winCount[1]){
            System.out.println("IMPOSSIBLE HOW DID YOU BEAT ME NOOOOOOOOOOOOOOOO????" + "(You has won the best of " + ((neededWins*2)-1) + ")");
        }
        else{
            System.out.println("I AM THE WINNER OF DOUBLE TROUBLEEE (Computer has won the best of " + ((neededWins*2)-1) + ")");
        }
    }
    
    private void playGame(){
        /**
         * Plays 1 games of double trouble.
         */
        startingGameConditions(); // Ask for game conditions
        while(this.gameRunning){
            takeTurn(); 
            if(checkWinCondition()){
                this.gameRunning = false; // Winner found
                this.winner = this.player; 
                this.winCount[this.winner ? 0: 1]++;
            }
            else{
               this.player = !this.player; // Flip turn
            }
        }
        displayWinner();
    }

    private void startingGameConditions(){
        /**
         * Initialize beginning game conditions and states. Allows the user to choose who goes first. 
         */
        // @TODO 
        this.winner = false;
        this.gameRunning = true;

        
        this.colorCount = new int[] {3, 5, 7}; // Yellow, Orange, Green  

        System.out.println("Choose your fate human... (choose if you want to go first or not :)");
        System.out.println("(0) You'll make the first move");
        System.out.println("(1) I the all mighty computer will go first");
        
        boolean validInput = false;
        while(!validInput){
            System.out.print("Choose your turn: ");
            int input = scanner.nextInt(); // might break here if it is not an int
            scanner.nextLine();
            this.player = input == 0 ? true: false;
            if(input == 1 | input == 0) break;
            System.out.println("What?? You chose what? Tell me again.");
        }
    }
    
    private void displayWinner(){
        /**
         * Simple print on who won a single game. 
         */
        if(this.winner){
            System.out.println("You beat me this time human....");
        }
        else{
            System.out.println("HAHA LOOOOOSERRRRR....");
        }
    }

    private boolean checkWinCondition(){
        /**
         * Checks if there are no remaining colors, allowing the current user to win.
         * @return if someone has won the game
         */
        for(int i = 0; i<this.colorOrder.size(); i++){ 
            if (!(this.colorCount[i] == 0)) return false;  
        }
        return true; 
    }
    
    private boolean checkPossibleWin(){
        /**
         * @return if current player has a winning condition
         */
        int xOrSum = this.colorCount[0] ^ this.colorCount[1] ^  this.colorCount[2];
        return xOrSum != 0;
    }
    
    private ArrayList<String> availableColors(){
        /**
         * This function will display available colors to flip
         * @return ArrayList<String> of available colors 
         */
        ArrayList<String> avaColors = new ArrayList<String>();
        for(int i = 0; i < 3; i++){
            if(this.colorCount[i] != 0){ 
                System.out.println("(" + this.colorOrder.get(i) + "): " + this.colorCount[i]);
                avaColors.add(this.colorOrder.get(i)); // Adds what colors are available 
            }
        }
        return avaColors;
    }
   
    private void playerAction(){
    /**
    * Asks the user to make a game action by choosing a valid color and flip count.
    */
        System.out.println("Choose an available color");
        ArrayList<String> avaColors = availableColors();
        
        // Player Input
        boolean isValidInput = false; 
        String color = " ";
        int flipCount = 0;
        int colorIndex = 0; 

        while(!isValidInput){
            // Input Color and check if it is a valid color
            System.out.print("Color: "); 
            color = scanner.nextLine().toUpperCase();

            if(!avaColors.contains(color)){
                System.out.println("You're testing my edge cases.. I see...Pick a valid color human.");
                //availableColors();
                continue; 
            };

            // Input number of flips and check for valid flips
            boolean validNum = false;
            colorIndex = this.colorOrder.indexOf(color);
            while(!validNum){
                System.out.print("Number of flipped (" + color + "): "); 
                flipCount = scanner.nextInt();
                scanner.nextLine();
                if(flipCount > this.colorCount[colorIndex] && flipCount != 0){ //checks for valid num input 
                    System.out.println("You're testing my stability!!! That's not a valid count. Pick a valid count human.");
                    availableColors();
                    continue; 
                }
                validNum = true;
            }
            isValidInput = true;
        }
        this.colorCount[colorIndex] = this.colorCount[colorIndex] - flipCount; 
    }

    private void computerSmart(){
        /**
         * If the computer has a winning move, the computer will play perfectly to win the game.
         */  
        int A = this.colorCount[0];
        int B = this.colorCount[1];
        int C = this.colorCount[2];

        int flipCount = 0;
        int flipIndex = 0;

        if(A > (B^C)){
            flipCount = A - (B^C);
            flipIndex = 0;
        }
        else if(B > (C^A)){
            flipCount = B - (A^C);
            flipIndex = 1;
        }
        else{ // C > (A^B)
            flipCount = C - (B^A);
            flipIndex = 2;
        }
        computerAction(flipIndex, flipCount);
    }

    private void computerAction(int colorIndex, int flipCount){
        /*
         * Computer will do a game action given a color index and the number of flipped colors 
         */
        this.colorCount[colorIndex] = this.colorCount[colorIndex] - flipCount;
        System.out.println("I THE MIGHTY COMPUTER MADE MY MOVE");
        System.out.println("I pick " + this.colorOrder.get(colorIndex) + " and flip it " +  flipCount + " times.");
    }

    private void computerRandom(){
        /**
         * The computer will choose a valid and random color and count
         */
        Random rand = new Random();
        ArrayList<String> avaColor = availableColors(); 
        String color = avaColor.get(rand.nextInt(avaColor.size())); // Picks a random available color 
        int colorIndex = this.colorOrder.indexOf(color); 
        int currentCount = this.colorCount[colorIndex];
        int flipCount = rand.nextInt(currentCount) + 1; // At least flip 1
        computerAction(colorIndex, flipCount);
    }

    private void takeTurn(){
        /**
         * Contains 1 turn of the game and checks win conditions after game actions. 
         */
        System.out.println("\n\n");
        if(this.player){
            System.out.println("Your turn human... Make your choice.");
            // someinput 
            playerAction(); 
        }
        else{ // Computer's turn
            if(checkPossibleWin()){
                System.out.println("YOU MADE YOUR LOSING MOVE");
                computerSmart();
            }
            else{
                computerRandom();
            }
        }
    }
}
