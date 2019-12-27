import javafx.scene.effect.Blend;

public class PrisonBoss extends LivingBeing {
    static boolean wish;
    static double money = 16000000;
    static protected String name = "Тюремное начальство";

    public static void rename(Place place, String name) {
        System.out.println(PrisonBoss.name + " устроило " + name + " в " + place.toString());
        place.setName(name);
    }

    public static void wasteMoney(double q, String s) {
         if ((wish) && (checkMoney()>q)){
             money -= q;
             System.out.println(q + " денег потратилось на " + s);
         }
         else {
             System.out.println("Что-то пошло не так, и " + s + " не осуществилась");
         }
     }

     public static Double checkMoney(){
         System.out.println("На счету тюремного начальства: " + money);
         return money;
     }

     public static void setWish(boolean b){
        wish = b;
         System.out.print("Желание начальства что-то делать стало ");
         if (wish) {
             System.out.println("благоприятным");
         } else {
             System.out.println("негативным");
         }
     }
    @Override
    public int hashCode() {
        return (int)(money/name.length());
    }
}
