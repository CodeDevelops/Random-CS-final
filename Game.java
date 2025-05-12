public class Game {
    private Map map;
    private Player player;
    private int level;
    public final int totalLevels;

    public Game() {
        this((int) (Math.random() * (14 - 5 + 1) + 5));
    }

    public Game(int totalLevels) {
        this.totalLevels = totalLevels;
        level = 0;

        nextLevel();
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentLevel() {
        return level;
    }

    protected void nextLevel() {
        level++;
        map = new Map(this);
        player = new Player(map);
    }

    public String toString() {
        String returnString;
        float perc = (level / totalLevels) * 10;

        returnString = "LVL " + level + " / " + totalLevels + " ";

        for (int i = 0; i < 10; i++) {
            if (perc > i) {
                returnString += "▓";
            } else {
                returnString += "░";
            }
        }
        
        return returnString;
    }

    // Clears the console using an escape code (May not work on certain systems)
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}