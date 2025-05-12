public class Game {
    private Map map;
    private Player player;
    private final String seed;

    private int level;

    public Game() {
        this("");
    }

    public Game(String seed) {
        this.seed = seed;
        level = 0;

        nextLevel();
    }

    public Player getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public String getSeed() {
        return seed;
    }

    protected void nextLevel() {
        level++;
        map = new Map();
        player = new Player();
    }

    // Clears the console using an escape code (May not work on certain systems)
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}