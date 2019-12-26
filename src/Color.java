public enum Color {
    RED ("красная"),
    BLUE ("синяя"),
    GREEN ("зеленая"),
    YELLOW ("желтая"),
    PINK ("розовая"),
    ORANGE ("оранжевая"),
    PURPLE ("фиолетовая");
    private String title;
    Color(String title) {
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
