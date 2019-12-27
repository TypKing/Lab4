import java.util.Random;
import java.util.Timer;

public class Story {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        Time time = new Time();
        Water stream = new Water();

        Place room = new Place("Каталажка");
        Place corridor = new Place("Корридор");
        Place policeDeps = new Place("Полицейское управление");
        Place sanitary = new Place("Место для обработки");

        Police clintEastwood = new Police("Полицейский Клинт", policeDeps);
        Police robocop = new Police("Полицейский Роб", policeDeps);
        Shorty dontknow = new Shorty("Незнайка", corridor);
        Shorty migy = new Shorty("Мига", corridor);
        Shorty drigle = new Shorty("Дригль", corridor);
        Clothes.Insects[] insects = new Clothes.Insects[20];
        Clothes[] robes = new Clothes[13];
        Clothes cap = new Clothes("Полицейская одежда", policeDeps);
        robocop.addClothes(cap);
        for (int i = 0; i<10; i++) {
            insects[i] = new Clothes.Insects("Клоп " + (i + 1), corridor);
            robes[i] = new Clothes("Роба" + (i+1),corridor);
            insects[i].jumpToClothe(robes[i]);
        }

        Things[] shelf = new Things[10];
        Things door = new Things("Дверь", corridor, RoomsParts.WALL);
        door.setClose_open(true);
        Button button1 = new Button("Кнопка 1", corridor, Color.BLUE);
        Button button2 = new Button("Кнопка 2", corridor, Color.ORANGE);
        Hole squareHole = new Hole("Четырехугольное отверстие", room, RoomsParts.FLOOR);
        Hole circleHole = new Hole("Круглое отверстие", room, RoomsParts.WALL);
        Things shelfN = new Things("Полка Незнайки",room);
        Things shelfM = new Things("Полка Мигу",room);
        Clothes robeN = new Clothes("Одежда незнайки",room);
        Clothes robeM = new Clothes("Одежда Мигу",room);
        migy.addClothes(robeM);
        dontknow.addClothes(robeN);
        migy.deleteAllClothes();
        migy.getDown(shelfM);
        dontknow.checkClothesOn(migy,shelfN);
        timer.schedule(time, 0, 500);
        Shorty[] shorty = new Shorty[10];
        for (int i = 0; i<10; i++) {
            shorty[i] = new Shorty("Коротышка " + (i + 1), room);
            shorty[i].addClothes(robes[i]);
            shelf[i] = new Things("Полка " + (i + 1), room);
            shorty[i].checkClothesOn(migy,shelf[i]);
        }
        dontknow.makeSure(shorty);
        dontknow.pressButton(button1, squareHole);
        shelf[0].vanish(squareHole);
        dontknow.see(shelf, squareHole);
        room.returnThingsName();
        for (int i = 1; i<10; i++) {
            shelf[i].vanish(squareHole);
            sanitary.moveClothes(robes[i]);
        }
        sanitary.moveClothes(robeM,robeN,robes[0]);
        room.returnThingsName();
        room.returnShortiesName();
        drigle.see(shelf, squareHole);
        drigle.lock(door);
        drigle.pressButton(button2, circleHole);
        stream.setLocation(circleHole);
        stream.setForce(Force.STRONG);
        stream.changePlace(room);
        Random random = new Random();
        int k = random.nextInt(13)+7;
        for (int i = 0; i<k; i++) {
            int j = random.nextInt(9)+1;
            stream.runFollow(shorty[j], 100*(random.nextInt(2)+4));
        }
        PrisonBoss.setWish(false);
        PrisonBoss.rename(room, "Мойка");
        PrisonBoss.wasteMoney(3000000, "Постройка специальной умывальни");
        for (int i = 10; i<20; i++) {
            insects[i] = new Clothes.Insects("Клоп " + (i + 1), corridor);
            insects[i].jumpToPlace(policeDeps);
            insects[i].jumpToClothe(cap);
            if(i==19) System.out.println("Насекомые расползлись по всему " + policeDeps.name);
        }

        //clintEastwood.changePlace(sanitary);
        robocop.live(sanitary);
        robocop.live(sanitary);
        robocop.live(sanitary);
        try{
            clintEastwood.changePlace(corridor);
        }catch (PoliceException e){
            System.out.println(e.getMessage());
        }
        sanitary.sanitization.startSanitization();

//        Time.increaseTime();
//        PrisonBoss.checkMoney();
        System.out.println(Time.getMinutes());
        System.exit(0);

    }
}
