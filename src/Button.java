public class Button extends Things{
    protected boolean singlePress;
    private Color color;
    private RoomsParts location = RoomsParts.WALL;

    Button(String name, Place place, Color color) {
        super(name, place);
        super.name = name;
        this.color = color;
    }

    public String getColor(){
        return color.getTitle();
    }

    public void setSinglePress(boolean b){
        this.singlePress = b;
    }

    public boolean getSinglePress(){
        return singlePress;
    }

    @Override
    public int hashCode() {
        return name.length()*getColor().length();
    }
}
