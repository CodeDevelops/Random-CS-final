public class Tile {
    public static final String[][] tyleTypes = {
            { "air", " " },
            { "wall", "[]" }
    };

    private String type;

    public Tile(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
