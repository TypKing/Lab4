public enum Action {
    DEFAULT ("бездействует"),
    STANDING ("стоит"),
    RUNNING ("бежит"),
    SITTING ("сидит"),
    LYING("лежит"),
    KNOCKOFF("cбит с ног"),
    OVERSEE("надзирает");
    protected String title;
    Action(String title) {
        this.title = title;
    }

    protected String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
