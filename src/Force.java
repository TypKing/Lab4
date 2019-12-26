public enum Force {
    STRONG ("мощные", 3),
    MEDIUM ("средние", 2),
    WEAK("слабые", 1);
    private String title;
    private int force;
    Force(String title, int force) {
        this.title = title;
        this.force = force;
    }

    protected String getTitle() {
        return title;
    }

    protected int getForce() {
        return force;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
