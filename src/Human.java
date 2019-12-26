public class Human extends LivingBeing {
    protected Action action;
    protected int irritation;
    protected Clothes[] clothes = new Clothes[10];
    int clothesStack=0;
    public void addClothes(Clothes clothes) {
        this.clothes[clothesStack] = clothes;
        clothesStack++;
    }

    public void deleteClothes() {
        this.clothes[clothesStack].deleteOwner();
        this.clothes[clothesStack] = null;
        clothesStack--;
    }
    // Метод реализует натирание всей одежды на человеке
    void rubClothes(){
        for (Clothes clothe:clothes) {
            clothe.rub();
        }
    }
    public Human(String name, Place place, Action action) {
        super(name, place);
        this.action = action;
        this.irritation=0;
    }

}
