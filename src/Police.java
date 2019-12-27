public class Police extends Human {
    Police(String name, Place place){
        super(name,place);
        action = Action.OVERSEE;
    }
    //TODO:Тут можно реализовать действия полиции в зависимости от уровня их заинтересованности

    public void checkInterest(Place place) {
        if(irritation>=600) {
            System.out.println("Полицейские в Бешенстве!");
        }
        else if(irritation>=30) {
            System.out.println("Полицейским это не нравится");
            place.sanitization.setPermission(true);
        }
        else{
            System.out.println("Полицеским безразлично");
        }
    }
    public void changePlace(Place place) throws PoliceException{
        if(place.name.equals("Полицейское управление")) {
            super.place = place;
            place.countShorties++;
            for (int i = 0; i < clothesStack; i++) {
                clothes[i].changePlace(place);
            }
            System.out.println(getName() + " переместился в " + place.toString());
        }
        else throw new PoliceException(this);
    }
    void live(Place place) throws InterruptedException {
        rubClothes();
        checkInterest(place);
        Thread.sleep(1000);
    }
    //TODO:Как-нибудь менять уровень заинтересованности полиции в зависимости от количества укусов
    //Есть степень раздраженности irritation у человека, она увеличивается когда его кусают
}
