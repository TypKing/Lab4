import java.util.Random;
import java.util.Timer;

public class Story {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        Time time = new Time();
        Water stream = new Water();

        Place room = new Place("Каталажка");
        Place corridor = new Place("Корридор");
        Place policeDeps = new Place("Полицейское управленик");

        Police clintEastwood = new Police("Полицейский Клинт", policeDeps);
        Police robocop = new Police("Полицейский Роб", policeDeps);
        Shorty dontknow = new Shorty("Незнайка", corridor);
        Shorty drigle = new Shorty("Дригль", corridor);
        Shorty[] shorty = new Shorty[10];
        Clothes.Insects bukashka = new Clothes.Insects("Букашка",corridor);
        Clothes.Insects tarakashka = new Clothes.Insects("Таракашка",corridor);
        Clothes.Insects klop = new Clothes.Insects("Клоп",corridor);
        Clothes.Insects klesh = new Clothes.Insects("Клещ",corridor);
        Clothes.Insects bloha = new Clothes.Insects("Блоха",corridor);
        Clothes policecap = new Clothes("Полицейская фуражка",bukashka,tarakashka,klop,klesh,bloha);
        policecap.setOwner(robocop);

        Things[] shelf = new Things[10];
        Things door = new Things("Дверь", corridor, RoomsParts.WALL);
        door.setClose_open(true);
        Button button1 = new Button("Кнопка 1", corridor, Color.BLUE);
        Button button2 = new Button("Кнопка 2", corridor, Color.ORANGE);
        Hole squareHole = new Hole("Четырехугольное отверстие", room, RoomsParts.FLOOR);
        Hole circleHole = new Hole("Круглое отверстие", room, RoomsParts.WALL);

        timer.schedule(time, 0, 500);
        for (int i = 0; i<10; i++) {
            shorty[i] = new Shorty("Коротышка " + (i + 1), room);
            shelf[i] = new Things("Полка " + (i + 1), room);
            shorty[i].getDown(shelf[i]);
        }
        dontknow.makeSure(shorty);
        dontknow.pressButton(button1, squareHole);
        shelf[0].vanish(squareHole);
        dontknow.see(shelf, squareHole);
        room.returnThingsName();
        for (int i = 1; i<10; i++) {
            shelf[i].vanish(squareHole);
        }
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
        robocop.sitAndFart();
        robocop.sitAndFart();
        robocop.sitAndFart();
        robocop.sitAndFart();
        robocop.sitAndFart();
//        Time.increaseTime();
//        PrisonBoss.checkMoney();
        System.out.println(Time.getMinutes());
        System.exit(0);

    }
}
