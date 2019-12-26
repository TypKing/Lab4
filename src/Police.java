public class Police extends Human {
    Police(String name, Place place){
        super(name,place,Action.OVERSEE);
    }
    //TODO:Тут можно реализовать действия полиции в зависимости от уровня их заинтересованности

    public void checkInterest() {
        if(irritation>=600) {
            System.out.println("Полицейские в Бешенстве!");
        }
        else if(irritation>=10) {
            System.out.println("Полицейским это не нравится");
        }
        else{
            System.out.println("Полицеским безразлично");
        }
    }

    void sitAndFart() throws InterruptedException {
        rubClothes();
        checkInterest();
        Thread.sleep(1000);
    }
    //TODO:Как-нибудь менять уровень заинтересованности полиции в зависимости от количества укусов
    //Есть степень раздраженности irritation у человека, она увеличивается когда его кусают
}
