import java.util.Objects;

public abstract class LivingBeing {
    protected String name;
    Place place;

    LivingBeing() {}

    LivingBeing(Place place) {
        this.place = place;
    }

    LivingBeing(String name, Place place) {
        this.name = name;
        this.place = place;
    }


    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivingBeing that = (LivingBeing) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }
}
