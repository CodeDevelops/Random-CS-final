import java.util.Random;

public class Player {
    private int[] coords = { 0, 0 };
    private final Map map;

    public Player(Map map) {
        this.map = map;

        Tile[][] terrain = map.getTerrainData();
        Random random = map.random;

        // Finds a safe place to spawn the player
        while (true) {
            int x = random.nextInt(terrain[0].length);
            int y = random.nextInt(terrain.length);

            if (terrain[y][x].getType().equals("air")) {
                coords[0] = x;
                coords[1] = y;
                break;
            }
        }

        moveTo(coords[0], coords[1]);
    }

    // Moves the player in a direction
    public boolean move(char direction) {
        boolean success;
        switch (direction) {
            case 'w':
                success = moveTo(coords[0], coords[1] - 1);
                break;
            case 'a':
                success = moveTo(coords[0] - 1, coords[1]);
                break;
            case 's':
                success = moveTo(coords[0], coords[1] + 1);
                break;
            case 'd':
                success = moveTo(coords[0] + 1, coords[1]);
                break;

            // Returns false if invalid direction is provided
            default:
                return false;
        }

        if (success) {
            return true;
        }
        return false;
    }

    // Moves the player to a specific coordinate
    public boolean moveTo(int x, int y) {
        if (map.checkPassable(x, y)) {
            coords[0] = x;
            coords[1] = y;
            map.redraw(coords);
            return true;
        }
        map.redraw(coords);
        return false;
    }

    // Returns the player's current coordinates
    public int[] getCoords() {
        return coords;
    }
}
