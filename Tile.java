public abstract class Tile implements TileInterface {
    private final String type;
    protected String tileChar;
    protected boolean isSolid;

    protected Tile(String type, String tileChar) {
        this(type, tileChar, true);
    }

    protected Tile(String type, String tileChar, boolean isSolid) {
        this.type = type;
        this.tileChar = tileChar;
        this.isSolid = isSolid;
    }

    public String getType() {
        return type;
    }

    public String getTileChar() {
        return tileChar;
    }

    public boolean isSolid() {
        return isSolid;
    }

    abstract public void interact();
}
