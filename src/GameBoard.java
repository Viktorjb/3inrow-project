public class GameBoard {

    private int[] board; // Array containing 9 ints, 0 symbolizing nothing, 1 symbolizing O and 2 symbolizing X

    public GameBoard() {
        this.board = new int[9]; // Board contains 9 "spots"
        for (int i : this.board){ // Set every "spot" to 0, i.e. nothing, to begin with
            i = 0;
        }
    }

    public String toString(){ // Returns the board with placed symbols in place
        return  "3-In-A-Row: 1  2  3" +
                "\n         A| " + intToSymbol(this.board[0]) + "  " + intToSymbol(this.board[1]) + "  " + intToSymbol(this.board[2]) +
                "\n         B| " + intToSymbol(this.board[3]) + "  " + intToSymbol(this.board[4]) + "  " + intToSymbol(this.board[5]) +
                "\n         C| " + intToSymbol(this.board[6]) + "  " + intToSymbol(this.board[7]) + "  " + intToSymbol(this.board[8]);
    }

    public char intToSymbol(int i){ // Turns ints into symbols (chars)
        if (i == 1){
            return 'O';
        } else if (i == 2){
            return 'X';
        } else{
            return ' ';
        }
    }

    public boolean setMarker(int symbol, int index){ // Sets an X or an O, returns false if the spot is taken
        if (index > 8 || index < 0){ // Makes sure the index is within the array
            System.out.println("Invalid move. Spot doesn't exist.");
            return false;
        }

        if (this.board[index] != 1 && this.board[index] != 2) {
            this.board[index] = symbol;
            return true;
        } else{
            System.out.println("Invalid move. Spot already taken.");
            return false;
        }
    }

    public boolean checkWinner(int symbol){ // Checks every possible win scenario, returns true if there's 3 in a row
        if (this.board[0] == symbol && this.board[1] == symbol && this.board[2] == symbol){
            return true;
        } else if(this.board[3] == symbol && this.board[4] == symbol && this.board[5] == symbol){
            return true;
        } else if(this.board[6] == symbol && this.board[7] == symbol && this.board[8] == symbol){
            return true;
        } else if(this.board[0] == symbol && this.board[3] == symbol && this.board[6] == symbol){
            return true;
        } else if(this.board[1] == symbol && this.board[4] == symbol && this.board[7] == symbol){
            return true;
        } else if(this.board[2] == symbol && this.board[5] == symbol && this.board[8] == symbol){
            return true;
        } else if(this.board[0] == symbol && this.board[4] == symbol && this.board[8] == symbol){
            return true;
        } else if(this.board[2] == symbol && this.board[4] == symbol && this.board[6] == symbol){
            return true;
        } else{
            return false;
        }
    }

    public boolean checkFull(){ // Checks if the board is full (used to render the game a tie)
        for (int i : board){
            if (i == 0){
                return false;
            }
        }
        return true;
    }

    public int[] getBoard() {
        return board;
    }
}
