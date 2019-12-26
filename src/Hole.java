public class Hole extends Things{

    Hole(String name, Place place, RoomsParts location){
        super(name, place, location);
        super.name = name;
        super.setPlace(place);
        super.location = location;
    }

    public void close(){
        super.close_open = false;
        System.out.println(getName() + ", имевшиеся в " + getRoomPlace() + ", закрылись");
    }

    public void open(){
        super.close_open = true;
        System.out.println(getName() + ", имевшиеся в " + getRoomPlace() + ", открылись");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.length()*getPlace().toString().length();
    }

    @Override
    public void vanish(Hole hole) throws InterruptedException {
        close();
    }
}
