import java.sql.SQLOutput;

public class Clothes {
    String name;
    boolean haveOwner;
    Insects[] insects = new Insects[10];
    int insectsStack = 0;
    private Place place;
    private Human owner;

    Clothes(String name, Place place, Insects... insects) {
        this.place = place;
        place.addClothes(this);
        this.name = name;
        for (Insects insect : insects) {
            addInsects(insect);
        }
    }

    public Human getOwner() {
        return owner;
    }

    public void changePlace(Place place) {
        this.place = place;
    }

    // Одеваем одежду на человека
    public void setOwner(Human owner) {
        this.owner = owner;
        haveOwner = true;
    }

    // Снимаем её
    public void deleteOwner() {
        System.out.println(name + " был снят с " + owner.getName());
        owner = null;
        haveOwner = false;
    }

    // Метод очистки одежды от насекомых
    public void killInsects() {
        for (int i = 0; i < insectsStack; i++) {
            insects[i] = null;
        }
        System.out.println(" Насекомые уничтожены.");
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
    void rub() {
        for (int i = 0; i < insectsStack; i++) {
            insects[i].biteable.bite(this);
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
//        void bite(Clothes clothes) {
//            System.out.println(name + " кусает " + clothes.getOwner());
//        }
        Biteable biteable = new Biteable() {
            @Override
            public void bite(Clothes clothes) {
                System.out.println(name + " кусает " + clothes.getOwner());
            }
        };
        // Прыгают на одежду
        void jumpToClothe(Clothes clothes) {
            clothes.addInsects(this);
        }
        void jumpToPlace(Place place){
            super.place = place;
        }
    }
}
