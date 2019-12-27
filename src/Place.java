import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Place {
    protected int countThings = 0;
    protected int countShorties = 0;
    protected int countClothes = 0;
    protected String name;
    protected Things[] things = new Things[20];
    protected Clothes[] clothes = new Clothes[20];
    protected Shorty[][] field = new Shorty[10][10];
    Sanitization sanitization;
    Place(String name) {
        this.name = name;
        sanitization = new Sanitization();
    }

    Place(String name, int x, int y){
        this.name = name;
        this.field = new Shorty[x][y];
        sanitization = new Sanitization();
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

    public void addClothes(Clothes clothes){
        this.clothes[countClothes] = clothes;
        countClothes++;
    }

    public void removeClothes(){
        this.clothes[countClothes] = null;
        countClothes--;
    }
    public void addShorty(Shorty shorty) throws PoliceException{
        int x;
        int y;
        if ((!equals(shorty.getPlace())) && (countShorties<20)) {
            countShorties++;
            shorty.changePlace(this);
//            shorties[countShorties] = shorty;
            Random random = new Random();
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            }
            while (field[x][y] != null);
            field[x][y] = shorty;
            shorty.setCoordinate(x, y);
        }
    }

    public void removeShorty(Shorty shorty){
        if (equals(shorty.getPlace())) {
            shorty.place = null;
//            for (int i = 0; i<shorties.length; i++){
//                if (shorties[i] == shorty) {
//                    for (int j = i; j<shorties.length; j++){
//                        shorties[j] = shorties[j+1];
//                    }
//                    shorties[shorties.length-1] = null;
//                }
//            }
            for (int i=0; i<field.length; i++){
                for (int j=0; j<field.length; j++){
                    if (field[i][j].equals(shorty)){
                        field[i][j] = null;
                    }
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
//        for (Shorty shorty : shorties) {
//            if (shorty != null){
//                System.out.print(shorty.getName() + "  ");
//            }
//        }
        for (int i=0; i<field.length; i++){
            for (int j=0; j<field.length; j++){
                if (field[i][j] != null){
                    System.out.print(field[i][j].getName() + "  ");
                }
            }
        }
        System.out.println();
    }
    public void moveClothes(Clothes ... clothe){
        for (Clothes cloth:clothe) {
            cloth.changePlace(this);
            clothes[countClothes] = cloth;
            countClothes++;
        }
    }
    public class Sanitization{
        private boolean permission;
        Sanitization(){
            permission = false;
        }
        void startSanitization() {
            if (permission) {
                System.out.println("Санитарная обработка началась.");
                class Gaz {
                    Gaz() {
                        killEverything();
                    }
                    void killEverything() {
                        if(!(countShorties > 0)) {
                            System.out.println(" Газ выпущен");
                            for (int i = 0; i < countClothes; i++) {
                                clothes[i].killInsects();
                            }
                        }
                        else throw new Die(name);
                    }

                }
                Gaz gaz = new Gaz();
            }
            else{
                System.out.println("Причин для санитарной обработки не обнаружено!");
            }
        }

        public void setPermission(boolean permission) {
            this.permission = permission;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return countThings == place.countThings &&
                countShorties == place.countShorties &&
                Objects.equals(name, place.name) &&
                Arrays.equals(things, place.things) &&
                Arrays.equals(field, place.field);
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