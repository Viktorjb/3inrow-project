import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Player p1;
        Player p2;

        while (true) { // Setting up players
            System.out.println("Welcome to Three-in-a-Row!\nPlease choose game mode (1-3).\n" +
                    "1. Player VS Computer\n" +
                    "2. Player VS Player\n" +
                    "3. Rules\n");

            int tempChoice = 0;

            try { // Catch if the player enters something other than an int
                tempChoice = Integer.parseInt(sc.next());
            } catch (Exception e){
                System.out.println("You didn't enter a number");
            }

            if (tempChoice == 1) { // If Player vs computer
                p1 = setUpPlayer(false,1);
                System.out.println("Human player " + p1.getName() + " joined the game!");
                p2 = setUpPlayer(true, 2);
                System.out.println("Computer player " + p2.getName() + " joined the game!");
                break;
            } else if (tempChoice == 2) { // If Player vs player
                p1 = setUpPlayer(false, 1);
                System.out.println("Human player " + p1.getName() + " joined the game!");
                p2 = setUpPlayer(false, 2);
                System.out.println("Human player " + p2.getName() + " joined the game!");
                break;
            } else if (tempChoice == 3) { // If rules
                printRules();
            } else {
                System.out.println("Please choose between 1, 2 and 3\n");
            }
        }


        runGame(p1, p2); // Run the game with set players


    }


    public static void runGame(Player p1, Player p2){

        GameBoard board = new GameBoard(); // The game board for this round
        Scanner sc = new Scanner(System.in);

        boolean runGame = true; // Turns false when a winner is decided

        boolean correctInput = true; // For making sure the input is correct

        Player[] players = {p1, p2}; // Array with both players to make the loop cleaner

        while (true) { // Outer loop, restarts the game as long as the player(s) wish to play again
            while (runGame) { // Inner loop, continues until the winner is decided
                for (Player p : players) {
                    System.out.println(board.toString());
                    System.out.println(p.getName() + "'s turn. Place an " + board.intToSymbol(p.getSymbol()) + "...");

                    if (!p.getIsAI()){ // If human
                        correctInput = false; // Set to false before getting input
                        while (!correctInput) { // Loop until correct player input
                            correctInput = board.setMarker(p.getSymbol(), inputToIndex(sc.next()));
                        }
                    } else{ // If AI
                        board.setMarker(p.getSymbol(), p.randomSelection(board.getBoard()));
                    }

                    if (board.checkWinner(p.getSymbol())) { // Check if someone has won
                        System.out.println(board.toString());
                        System.out.println(p.getName() + " won!");
                        p.addWin();
                        runGame = false;
                        break;
                    }

                    if (board.checkFull()){ // Check if the board is full (in case no one has won)
                        System.out.println(board.toString());
                        System.out.println("Board is full and nobody has won.");
                        runGame = false;
                        break;
                    }
                }
            }

            // States the number of wins and asks if the player wishes to play again
            System.out.println(p1.getName() + " has won " + p1.getNumberOfWins() + " times.\n" +
                    p2.getName() + " has won " + p2.getNumberOfWins() + " times.\n" +
                    "Do you wish to play again? (y/n)");
            String choice = sc.next();

            if (choice.equals("y") || choice.equals("Y")){
                System.out.println("Starting a new game!");
                board = new GameBoard(); // Make a new game board
                runGame = true; // To enter the "inner" game loop again
            } else if (choice.equals("n") || choice.equals("N")){
                System.out.println("Quitting.");
                break; // Quit the "outer loop" and thus ends the game
            } else {
                System.out.println("Enter 'y' or 'n'.");
            }
        }


    }


    public static int inputToIndex(String input){ // Converts an input to an index for the GameBoard array
        try {
            return Integer.parseInt(input) - 1; // Try to parse the input as an int
        } catch(Exception e){ // If parsing as int didn't work, try to parse as String
            if(input.equals("A1") || input.equals("a1")){
                return 0;
            } else if(input.equals("A2") || input.equals("a2")){
                return 1;
            } else if(input.equals("A3") || input.equals("a3")){
                return 2;
            } else if(input.equals("B1") || input.equals("b1")){
                return 3;
            } else if(input.equals("B2") || input.equals("b2")){
                return 4;
            } else if(input.equals("B3") || input.equals("b3")){
                return 5;
            } else if(input.equals("C1") || input.equals("c1")){
                return 6;
            } else if(input.equals("C2") || input.equals("c2")){
                return 7;
            } else if(input.equals("C3") || input.equals("c3")){
                return 8;
            } else{
                return 9; // Invalid input, this is outside of the array and will cause an error message
            }
        }
    }

    public static void printRules(){ // Prints the rules in case the player doesn't know them
        System.out.println("The winner is the player who manages to place three of the same symbol in a row.\n" +
                "You can write either 1-9, or A1-C3, to place your symbol.\n" +
                "If the board gets filled without either player getting three in a row, it's a tie,\n" +
                "and nobody wins.\n" +
                "You can play either against the computer or another player.\n");
    }

    public static Player setUpPlayer(boolean isAI, int symbol){ // For setting up a player
        Scanner sc = new Scanner(System.in);
        if (!isAI) {
            System.out.println("Please enter the player's name: ");
            return new Player(sc.nextLine(), symbol, isAI);
        } else {
            return new Player(symbol, isAI);
        }
    }

}
