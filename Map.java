import java.util.Random;

public class Map {
    private Tile[][] terrain;

    protected Random random;
    private Game game;
    final int currentLevel;

    public Map(Game gameObj) {
        game = gameObj;
        random = new Random();

        currentLevel = game.getCurrentLevel();

        if (currentLevel > 3) {
            terrain = new Tile[5 + (currentLevel / 2)][5 + (currentLevel / 2)];
        } else {
            terrain = new Tile[5][5];
        }

        generate();
    }

    public Tile[][] getTerrainData() {
        return terrain;
    }

    private void generate() {
        Game.clearConsole();
        System.out.println("Generating terrain...");

        for (int r = 0; r < terrain.length; r++) {
            for (int c = 0; c < terrain[r].length; c++) {
                // Generates wall border around map
                if (r == 0 || r == terrain.length - 1 || c == 0 || c == terrain[r].length - 1) {
                    terrain[r][c] = new Wall();
                } else {
                    terrain[r][c] = new Air();
                }
            }
        }

        if (currentLevel > 30) {
            int roomCount = 3 + random.nextInt(3);
            for (int i = 0; i < roomCount; i++) {
                createRoom();
            }
        }

        addGoal();

        redraw(new int[] { 0, 0 });
    }

    private void createRoom() {
        while (true) {
            int roomWidth = 4 + random.nextInt(6);
            int roomHeight = 4 + random.nextInt(6);
            int x = 1 + random.nextInt(terrain[0].length - roomWidth - 2);
            int y = 1 + random.nextInt(terrain.length - roomHeight - 2);

            // Check if the room can fit here without overlapping
            if (canPlaceRoom(x, y, roomWidth, roomHeight)) {
                // Place the room
                for (int r = y; r < y + roomHeight; r++) {
                    for (int c = x; c < x + roomWidth; c++) {
                        if (r == y || r == y + roomHeight - 1 || c == x || c == x + roomWidth - 1) {
                            terrain[r][c] = new Wall();
                        } else {
                            terrain[r][c] = new Air();
                        }
                    }
                }
                // Add an entrance
                addEntrance(x, y, roomWidth, roomHeight);
                return;
            }
        }
    }

    private boolean canPlaceRoom(int x, int y, int width, int height) {
        for (int r = y - 1; r <= y + height; r++) {
            for (int c = x - 1; c <= x + width; c++) {
                if (!terrain[r][c].getType().equals("air")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void addEntrance(int x, int y, int width, int height) {
        int wall = random.nextInt(4);
        switch (wall) {
            case 0: // Top wall
                terrain[y][x + 1 + random.nextInt(width - 2)] = new Air();
                break;
            case 1: // Bottom wall
                terrain[y + height - 1][x + 1 + random.nextInt(width - 2)] = new Air();
                break;
            case 2: // Left wall
                terrain[y + 1 + random.nextInt(height - 2)][x] = new Air();
                break;
            case 3: // Right wall
                terrain[y + 1 + random.nextInt(height - 2)][x + width - 1] = new Air();
                break;
        }
    }

    private void addGoal() {
        while (true) {
            int x = random.nextInt(terrain[0].length);
            int y = random.nextInt(terrain.length);

            final boolean goalLocked = game.getCurrentLevel() >= 5 && random.nextInt(2) == 1;

            if (terrain[y][x].getType().equals("air")) {
                final Goal goal = new Goal(goalLocked);
                terrain[y][x] = goal;

                if (goalLocked) {
                    addSwitch(goal);
                }
                break;
            }
        }
    }

    private void addSwitch(Goal goal) {
        while (true) {
            int x = random.nextInt(terrain[0].length);
            int y = random.nextInt(terrain.length);

            if (terrain[y][x].getType().equals("air")) {
                terrain[y][x] = new Switch(goal);
                break;
            }
        }
    }

    // Redraws the entire map in the console
    public void redraw(int[] newCoords) {
        if (terrain[newCoords[1]][newCoords[0]].getType() == "goal") {
            game.nextLevel();
            return;
        }

        Game.clearConsole();

        for (int r = 0; r < terrain.length; r++) {
            for (int c = 0; c < terrain[r].length; c++) {
                if (r == newCoords[1] && c == newCoords[0]) {
                    System.out.print("🔷");
                } else {
                    System.out.print(terrain[r][c].getTileChar());
                }
            }
            System.out.println();
        }
    }

    // Checks if the selected tile given a coordinate is passable
    protected boolean checkPassable(int x, int y) {
        if (!terrain[y][x].isSolid()) {
            return true;
        }
        return false;
    }

}
