import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    public static void timeChecker() {
        LocalTime time = LocalTime.now();
        LocalTime calculation = time.minusMinutes(150);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        int minute = time.getMinute();

        if (minute % 2 == 1) {
            System.out.println("The time is now " + time.format(formatter));
            System.out.println("Codrut has asked me to print t - 2h30m which is " + calculation.format(formatter));
            System.out.println("");
        }
    }

    public void run() {
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timeChecker();
            }
        }, 0, 60 * 1000);
    }
}
// Task completed