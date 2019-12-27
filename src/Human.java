public class Human extends LivingBeing {
    protected int irritation;
    protected Action action;
    protected Clothes[] clothes = new Clothes[10];
    int clothesStack=0;
    public void changePlace(Place place) throws PoliceException {
        super.place = place;
        place.countShorties++;
        for (int i = 0; i < clothesStack; i++) {
            clothes[i].changePlace(place);
        }
        System.out.println(getName() + " переместился в " + place.toString());
    }

    public void addClothes(Clothes clothes) {
        this.clothes[clothesStack] = clothes;
        clothesStack++;
        clothes.setOwner(this);
    }

    public void deleteClothes() {
        this.clothes[clothesStack].deleteOwner();
        this.clothes[clothesStack] = null;
        clothesStack--;
    }
    public void deleteAllClothes() {
        while(clothesStack>0) {
            clothesStack--;
            this.clothes[clothesStack].deleteOwner();
            this.clothes[clothesStack] = null;

        }
    }
    // Метод реализует натирание всей одежды на человеке
    void rubClothes(){
        for (int i=0;i<clothesStack;i++) {
            clothes[i].rub();
        }
    }
    public Human(String name, Place place) {
        super(name, place);
        this.irritation=0;
    }

}
