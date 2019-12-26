import java.util.TimerTask;

public class Time extends TimerTask {
    private static int minute = 0;

    public static void increaseTime(int second) {
        minute += second;
    }

    public static String getMinutes(){
        return "Прошло " + minute/2 + " минут";
    }

    @Override
    public void run() {
        minute++;
        if (minute%2 == 0){
            System.out.println();
            System.out.println("Прошла минута");
            System.out.println();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getMinutes();
    }

    @Override
    public int hashCode() {
        return minute;
    }
}
