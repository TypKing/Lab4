public class Place {
    protected int countThings = 0;
    protected int countShorties = 0;
    protected String name;
    protected Things[] things = new Things[20];
    protected Shorty[] shorties = new Shorty[10];
    protected Shorty[][] field = new Shorty[10][10];

    Place(String name) {
        this.name = name;
    }

    Place(String name, int x, int y){
        this.name = name;
        this.field = new Shorty[x][y];
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shorty getObject(int x, int y) {
        return field[x][y];
    }

    public void addThings(Things thing){
        if ((!equals(thing.getPlace())) && (countThings<20)) {
            countThings++;
            thing.setPlace(this);
            things[countThings-1] = thing;
        }
    }

    public void removeThings(Things thing){
        if (equals(thing.getPlace())) {
            countThings--;
            thing.setPlace(null);
            for (int i = 0; i<things.length; i++){
                if (things[i] == thing) {
                    for (int j = i; j<things.length-1; j++) {
                        things[j] = things[j+1];
                    }
                    things[things.length-1] = null;
                    break;
                }
            }
            if (countThings == 0) {
                System.out.println(toString() + " освобождено");
            }
        } else {
            System.out.println("В " + toString() + " нет " + thing.toString());
        }
    }

    public void addShorty(Shorty shorty){
        if ((!equals(shorty.getPlace())) && (countShorties<20)) {
            countShorties++;
            shorty.setPlace(this);
            shorties[countShorties] = shorty;
        }
    }

    public void removeShorty(Shorty shorty){
        if (equals(shorty.getPlace())) {
            shorty.place = null;
            for (int i = 0; i<shorties.length; i++){
                if (shorties[i] == shorty) {
                    for (int j = i; j<shorties.length; j++){
                        shorties[j] = shorties[j+1];
                    }
                    shorties[shorties.length-1] = null;
                }
            }
            if (countShorties == 0) {
                System.out.println(toString() + " освобождено");
            }
        } else {
            System.out.println("В " + toString() + " нет " + shorty.getName());
        }
    }

    public void returnThingsName(){
        System.out.println("В " + toString() + " находятся следующие вещи: ");
        for (Things thing : things) {
            if (thing != null) {
                System.out.print(thing.toString() + "  ");
            }
        }
        System.out.println();
    }

    public void returnShortiesName(){
        System.out.println("В " + toString() + " находятся следующие коротышки: ");
        for (Shorty shorty : shorties) {
            if (shorty != null){
                System.out.print(shorty.getName() + "  ");
            }
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object obj) {
        return toString() == obj.toString();
    }

    @Override
    public int hashCode() {
        return name.length() + countThings*countShorties;
    }

    @Override
    public String toString() {
        return name;
    }
}
