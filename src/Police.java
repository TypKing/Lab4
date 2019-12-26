public class Police extends Human {
    Interest interest;
    Police(String name, Place place){
        super(name,place,Action.OVERSEE);
    }
    //TODO:Тут можно реализовать действия полиции в зависимости от уровня их заинтересованности
    void sitAndFart(){
        rubClothes();
    }
    //TODO:Как-нибудь менять уровень заинтересованности полиции в зависимости от количества укусов
    //Есть степень раздраженности irritation у человека, она увеличивается когда его кусают
}
