import java.util.Random;

public class Map {
    private Tile[][] terrain = new Tile[10][10];
    private String seed;
    private Random random;

    // Constructor for no provided seed
    public Map() {
        this("");
    }

    public Map(String seed) {
        if (seed.equals("")) {
            random = new Random();
            this.seed = Long.toString(random.nextLong());
        } else {
            this.seed = seed;
            random = new Random(seed.hashCode());
        }
        generate();
    }

    public Tile[][] getTerrainData() {
        return terrain;
    }

    private void generate() {
        clearConsole();
        System.out.println("Generating terrain...");

        for (int r = 0; r < terrain.length; r++) {
            for (int c = 0; c < terrain[r].length; c++) {
                // Generates wall border around map
                if (r == 0 || r == terrain.length - 1 || c == 0 || c == terrain[r].length - 1) {
                    terrain[r][c] = new Tile("wall");
                } else {
                    terrain[r][c] = new Tile("air");
                }
            }
        }

        int roomCount = 3 + random.nextInt(3);
        for (int i = 0; i < roomCount; i++) {
            createRoom();
        }

        redraw(new int[] { 0, 0 });
    }

    private void createRoom() {
        int roomWidth = 3 + random.nextInt(4);
        int roomHeight = 3 + random.nextInt(4);
        int x = random.nextInt(terrain[0].length - roomWidth - 1);
        int y = random.nextInt(terrain.length - roomHeight - 1);

        for (int r = y; r < y + roomHeight; r++) {
            for (int c = x; c < x + roomWidth; c++) {
                if (r == y || r == y + roomHeight - 1 || c == x || c == x + roomWidth - 1) {
                    terrain[r][c] = new Tile("wall");
                } else {
                    terrain[r][c] = new Tile("air");
                }
            }
        }
    }

    // Redraws the entire map in the console
    public void redraw(int[] newCoords) {
        clearConsole();

        for (int r = 0; r < terrain.length; r++) {
            for (int c = 0; c < terrain[r].length; c++) {
                if (r == newCoords[1] && c == newCoords[0]) {
                    System.out.print("🔷");
                } else {
                    boolean found = false;
                    for (String[] tileType : Tile.tyleTypes) {
                        if (terrain[r][c].getType().equals(tileType[0])) {
                            found = true;
                            System.out.print(tileType[1]);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.print("XX");
                    }
                }
            }
            System.out.println();
        }
    }

    // Checks if the selected tile given a coordinate is passable
    protected boolean checkPassable(int x, int y) {
        if (terrain[y][x].getType().equals("air")) {
            return true;
        }
        return false;
    }

    // Clears the console using an escape code (May not work on certain systems)
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
