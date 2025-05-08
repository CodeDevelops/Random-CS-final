public class Map {
    private Tile[][] terrain = new Tile[10][10];
    private String seed;

    // Constructor for no provided seed
    public Map() {
        seed = "";
        generate();
    }

    public Map(String seed) {
        this.seed = seed;
        generate();
    }

    public Tile[][] getTerrainData() {
        return terrain;
    }

    private void generate() {
        for (int r = 0; r < terrain.length; r++) {
            for (int c = 0; c < terrain[r].length; c++) {
                terrain[r][c] = new Tile("wall");
            }
        }
        redraw();
    }

    public void redraw() {
        for (int r = 0; r < terrain.length; r++) {
            for (int c = 0; c < terrain[r].length; c++) {
                for (String[] tileType : Tile.tyleTypes) {
                    if (terrain[r][c].getType().equals(tileType[0])) {
                        System.out.print(tileType[1]);
                    }
                }
            }
            System.out.println();
        }
    }
}
