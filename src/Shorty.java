import java.nio.charset.Charset;
import java.util.Random;
import static java.lang.Math.*;

public class Shorty extends Human implements Movable, Runnable{
    protected int degreeOfDirt = 100;
    protected int degreeOfWet = 0;
    private int x;
    private int y;

    public Shorty(String name, Place place){
        super(name, place,Action.DEFAULT);
        place.countShorties++;
        place.shorties[place.countShorties-1] = this;
        Random random = new Random();
         do {
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
        while (place.field[x][y] != null);
        place.field[x][y] = this;
    }

    public Shorty(String name, Place place, int x, int y){
        super(name, place,Action.DEFAULT);
        place.countShorties++;
        place.shorties[place.countShorties] = this;
        this.x = x;
        this.y = y;
        place.field[x][y] = this;
    }

    public void setPlace(Place place){
        super.place = place;
    }

    public Place getPlace(){
        return place;
    }

    public void changePlace(Place p){
        super.place = p;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void changeAction(Action action) {
        System.out.println("Состояние у " + getName() + " изменилось с " + this.action.getTitle()+ " на " + action.getTitle());
        this.action = action;
    }

    public void makeSure(Shorty[] shorties){
        int count = 0;
        for (Shorty shorty : shorties) {
            if (shorty.action == Action.STANDING) {
                count++;
            }
        }
        if (count == shorties.length) {
            System.out.println(getName() + " убедился, что все коротышки слезли с полок");
        } else {
            System.out.println("Не все коротышки слезли с полок");
        }
    }

    public void getDown(Things object) throws InterruptedException {
        System.out.println(getName() + " слез с " + object.getName());
        setAction(Action.STANDING);
        Thread.sleep(440);
    }

    public void pressButton(Button button, Hole hole) throws InterruptedException {
        if (!button.singlePress) {
            System.out.println(name + " нажал на " + button.getColor()+ " " + button.getName());
            button.setSinglePress(true);
            hole.open();
            Thread.sleep(1000);
        }
        else System.out.println(button.getName() + " уже была нажата");
    }

    protected void see(Things[] things, Hole hole){
        int count = 0;
        if ((things[0].getPlace() == null) && (things[1].getPlace() != null)) {
                System.out.println(getName() + " увидел, что полки начали опускаться в " + hole.getName());
        } else {
            for (Things thing : things) {
                if (thing.getPlace() == null) {
                    count++;
                }
            }
            if (count == things.length) {
                System.out.print(getName() + " увидел, что все полки исчезли в " + hole.getName() + ". A ");
                hole.close();
            }
        }
    }

    public void runAway(int x, int y) throws InterruptedException {
        //передвижение на вектор
        Random random = new Random();
        System.out.println((this.x + x) + "  " + (this.y + y));
        if ((this.x + x < 10) && (this.y + y < 10)) {
            if ((place.field[this.x+x][this.y+y] == null)) {
                place.field[x][y] = null;
                this.x += x;
                this.y += y;
                place.field[x][y] = this;
                System.out.println (getName() + " переместился на координаты " + this.x + "  " + this.y);
                //должен высыхать в соответствии с тем, сколько он прошел
                degreeOfWet -= sqrt(x*x + y*y)*random.nextInt(10)/0.001;
                if (degreeOfWet < 0){
                    degreeOfWet = 100;
                }
                System.out.println(getName() + " немного высох в движении. Степень промокания: " + degreeOfWet);
            } else {
                System.out.println("Место уже занято");
                comeAcross(place.getObject(this.x + x, this.y + y));
            }
        } else {
                System.out.println("Такое передвижение невозможно");
        }
        Thread.sleep(300);
    }

    public void runAround() throws InterruptedException {
        //передвижение на рандомный вектор
        int z;
        int w;
        Random random = new Random();
//        first:
//        {
        do {
            z = random.nextInt(10);
            w = random.nextInt(10);
        }
        while (((this.x + z) >= 10) || ((this.y + w) >= 10));
        if ((place.field[this.x + z][this.y + w] == null)) {
            place.field[x][y] = null;
            this.x += z;
            this.y += w;
            place.field[x][y] = this;
            System.out.println(getName() + " переместился на координаты " + this.x + "  " + this.y);
            //должен высыхать в соответствии с тем, сколько он прошел
            degreeOfWet -= sqrt(z * z + w * w)+random.nextInt(10);
            if (degreeOfWet < 0){
                degreeOfWet = 0;
            }
            System.out.println(getName() + " немного высох в движении. Степень промокания: " + degreeOfWet);
        } else {
            System.out.println(getName() + " попытался переместиться на координаты " + (this.x + z) + " " + (this.y + w) + ", но место уже занято");
            comeAcross(place.getObject(this.x + z, this.y + w));
//                break first;
        }
//        }
        Thread.sleep(300);
    }

    public void showCoordinates(){
        System.out.println (getName() + " находится на координатах " + x + "  " + y);
    }

    public void getToWater(int value) throws InterruptedException {
        //изменение значения переменной degreeOfWet и degreeOfDirt в соответствии с продолжительностью нахождения под струей воды
        Random random = new Random();
        if (degreeOfWet < 100) {
            degreeOfWet += (value/1000)*(random.nextInt(10)+4)*Water.force.getForce()+20;
            if (degreeOfWet > 100) {
                degreeOfWet = 100;
            }
            System.out.println("Значение степени промокания у " + getName() + " изменилось на " + degreeOfWet);
        }
        if (degreeOfDirt <= 100) {
            degreeOfDirt -= (value/1000)*(random.nextInt(10)+3)*Water.force.getForce()+10;
            if (degreeOfDirt < 0) {
                degreeOfDirt = 0;
            }
            System.out.println("Значение степени загрязненности у " + getName() + " изменилось на " + degreeOfDirt);
        }
        Thread.sleep(value);
//        Time.increaseTime(value);
    }


    public void lock(Things thing){
        if (thing.close_open){
            System.out.println(getName() + " закрыл " + thing.getName() + ", которая находится в " + thing.getPlace());
            thing.setClose_open(false);
        } else {
            System.out.println(getName() + " открыл " + thing.getName() + ", которая находится в " + thing.getPlace());
            thing.setClose_open(true);
        }

    }

    public void comeAcross(Shorty shorty) throws InterruptedException {
        //у двоих коротышек уменьшается степень загрязнения
        if (!equals(shorty)) {
            Random random = new Random();
            this.degreeOfDirt -= (random.nextInt(10)+5)*3;
            shorty.degreeOfDirt -= (random.nextInt(10)+5)*3;
            if (degreeOfDirt < 0){
                degreeOfDirt = 0;
            }
            if (shorty.degreeOfDirt < 0){
                shorty.degreeOfDirt = 0;
            }
            System.out.println(getName() + " и " + shorty.getName() + " немного отчистились: " + getName() + " -> " + degreeOfDirt + ", " + shorty.getName() + " -> " + shorty.degreeOfDirt);
        } else {
            System.out.println(getName() + " уже здесь");
        }
    }

    @Override
    public int hashCode() {
        return degreeOfDirt*degreeOfWet;
    }

    public void escape(){
        changePlace(null);
    }
}
