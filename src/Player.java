import java.util.ArrayList;
import java.util.Random;

public class Player {

    private String name; // Player's name
    private int symbol; // Symbol the player is playing as, 1 for O, and 2 for X
    private boolean isAI; // Is the player an AI?
    private int numberOfWins; // How many times has the player won?

    public Player(String name, int symbol, boolean isAI) {
        this.name = name;
        this.symbol = symbol;
        this.isAI = isAI;
        this.numberOfWins = 0;
    }

    public Player(int symbol, boolean isAI) { // Creates a player with a random name (used mainly for AI players)
        this.name = randomName();
        this.symbol = symbol;
        this.isAI = isAI;
        this.numberOfWins = 0;
    }

    public String getName() {
        return name;
    }

    public int getSymbol() {
        return symbol;
    }

    public boolean getIsAI(){
        return isAI;
    }

    public int getNumberOfWins(){
        return numberOfWins;
    }

    public int addWin(){ // Adds 1 to number of wins and then returns the new value
        numberOfWins = numberOfWins + 1;
        return numberOfWins;
    }

    public int randomSelection(int[] boardArray){ // AI's random selection
        Random random = new Random(); // Creates a random class to help with randomization

        ArrayList<Integer> selectFrom = new ArrayList<>(); // Creates a list of ints to pick from
        for (int i = 0; i <= boardArray.length - 1; i++){
            if (boardArray[i] == 0){ // Insert all indexes that contain 0 (i.e. untouched "spots" on the game board
                selectFrom.add(i);
            }
        }
        return selectFrom.get(random.nextInt(selectFrom.size())); // Return a random index that contains 0
    }

    private String randomName(){ // Returns a random name, used for AI players
        Random random = new Random();
        String[] names = {"John", "Bob", "Rupert", "Oliver", "Morgan", "Alice", "Kate", "Jane", "Ellie", "Lily"};
        return names[random.nextInt(10)];
    }
}
