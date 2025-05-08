public class Player extends Map {
    private int[] coords = { 0, 0 };

    public boolean move(char direction) {
        boolean success;
        switch (direction) {
            case 'w':
                success = moveTo(coords[0], coords[1] + 1);
                break;
            case 'a':
                success = moveTo(coords[0] - 1, coords[1]);
                break;
            case 's':
                success = moveTo(coords[0], coords[1] - 1);
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

    public boolean moveTo(int x, int y) {
        if (checkPassable(x, y)) {
            coords[0] = x;
            coords[1] = y;
            super.redraw();
            return true;
        }
        return false;
    }

    private boolean checkPassable(int x, int y) {
        if (super.getTerrainData()[x][y].getType().equals("air")) {
            return true;
        }
        return false;
    }
}
