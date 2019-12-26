import java.sql.SQLOutput;

public class Clothes {
    String name;
    Insects[] insects = new Insects[10];
    int insectsStack = 0;
    private Human owner;
    Clothes(String name,Insects ... insects){
       this.name = name;
        for(Insects insect:insects) {
            addInsects(insect);
        }
    }
    public Human getOwner() {
        return owner;
    }
    // Одеваем одежду на человека
    public void setOwner(Human owner) {
        System.out.println(name + " был надет на " + owner.getName());
        this.owner = owner;
        owner.addClothes(this);
    }
    // Снимаем её
    public void deleteOwner(){
        System.out.println(name + " был снят с " + owner.getName());
        owner = null;
    }
    // Метод очистки одежды от насекомых
    public void killInsects(){
        for (int i = 0; i < insectsStack; i++) {
            insects[i] = null;
        }
        insectsStack = 0;
    }
    // Добавляем насекомых
    void addInsects(Insects insect) {
            insects[insectsStack] = insect;
            insectsStack++;
    }
    // Этот метод что-то типа трения одежды(при любом движении персонажа вся одежда на нем трется) и насекомые начинают кусать
    // Я не знаю почему насекомые вообще могут быть на одежде у коротышек, ведь коротышки сами по факту размером с крупных насекомых
    // По факту укус насекомого мог располовинить любого Human
    void rub(){
        for (int i = 0; i < insectsStack; i++) {
            insects[insectsStack].bite(this);
            owner.irritation++;
        }
        System.out.println(owner.getName() + " был укушен " + insectsStack + " раз!");
    }
    // Тут вставим статический класс - насекомые, так как они непрерывно связанны с одеждой
    static public class Insects extends LivingBeing {
        Insects(String name, Place place) {
            super(name, place);
        }
        // Кусь
        void bite(Clothes clothes) {
            System.out.println(name + " кусает" + clothes.getOwner());
        }
        // Прыгают на одежду
        void jumpToClothe(Clothes clothes){
            clothes.addInsects(this);
        }
    }
}
