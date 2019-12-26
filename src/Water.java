public class Water implements Movable {
    static Force force = Force.MEDIUM;
    Place place;
    Hole hole;

    public void setLocation(Hole hole) {
        this.hole = hole;
    }

    public void setForce(Force f){
        force = f;
    }

    public void changePlace(Place p){
        if (place != p) {
            System.out.println("Из " + hole.toString() + " брызнули " + force.getTitle() + " струи воды в " + p.toString());
            place = p;
        }
    }

    public void runFollow(Shorty shorty, int value) throws InterruptedException {
        System.out.println("Поток воды сбил " + shorty.getName() + " с ног");
        shorty.changeAction(Action.LYING);
        shorty.getToWater(value*2);
        shorty.changeAction(Action.RUNNING);
        shorty.runAround();
    }

    @Override
    public String toString() {
        return "поток воды";
    }

    @Override
    public int hashCode() {
        return force.getForce()*100;
    }
}
