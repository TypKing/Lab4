public class Things {
    protected RoomsParts location;
    private Place place = new Place("Someting");
    protected String name;
    protected boolean close_open = false;
    Things(String name, Place place){
        this.name = name;
        place.addThings(this);
    }

    Things(String name, Place place, RoomsParts location){
        this.name = name;
        this.place = place;
        this.location = location;
    }

    public void setClose_open(boolean bool){
        close_open = bool;
    }

    public String getName(){
        return name;
    }

    public Place getPlace(){
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getRoomPlace(){
        return location.getTitle();
    }

    public void vanish(Hole hole) throws InterruptedException {
        if (hole.close_open) {
            System.out.println(getName() + " исчезлa в " + hole.getName() + ", которые находятся в " + hole.getRoomPlace());
            place.removeThings(this);
            Thread.sleep(90);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return name == obj.toString();
    }

    @Override
    public int hashCode() {
        return name.length()*2;
    }
}
