public enum Interest {
    DEFAULT ("безразлично"),
    ANGRY ("напряженно"),
    RAGE ("не нравится");
    protected String title;
    Interest(String title) {
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
