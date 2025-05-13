public class Switch extends Tile {
    private Goal goal;
    private boolean activated = false;

    public Switch(Goal goal) {
        super("switch", "⚪", true);
        this.goal = goal;
    }

    public void interact() {
        if (!activated) {
            activated = true;
            super.tileChar = "⚫";

            goal.activate();
        }
    }
}
