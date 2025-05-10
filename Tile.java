public class Tile {
    public static final String[][] tyleTypes = {
            { "air", "  " },
            { "wall", "██" },
            { "goal", "▓▓" },
    };

    private String type;

    public Tile(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean setType(String type) {
        boolean found = false;

        for (String[] tiletype : tyleTypes) {
            if (tiletype[0].equals(type)) {
                found = true;
                break;
            }
        }

        if (found) {
            this.type = type;
        }
        return found;
    }
}
