public enum RoomsParts {
    WALL("стене"),
    FLOOR("полу"),
    CEILING("потолоке");

    private String title;
    RoomsParts(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return getTitle();
    }
}
