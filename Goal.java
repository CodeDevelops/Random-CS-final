public class Goal extends Tile {
    private boolean activated = false;

    public Goal() {
        this(false);
    }

    public Goal(boolean locked) {
        super("goal", "🔒", true);

        if (!locked) {
            activate();
        }
    }

    protected void activate() {
        if (!activated) {
            activated = true;
            super.tileChar = "░░";
            super.isSolid = false;
        }
    }

    public void interact() {
    }

}
