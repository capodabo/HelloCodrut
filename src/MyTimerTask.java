import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask {

    public static void httpRead() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://worldtimeapi.org/api/timezone/Europe/Bucharest.txt"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String worldTime[] = response.body().split("\n");
        String utcDateTime1 = worldTime[12];
        String utcDateTime2[] = utcDateTime1.split(": ");
        String utcDateTime3 = utcDateTime2[1];
        ZonedDateTime localTime = ZonedDateTime.parse(utcDateTime3);
        ZonedDateTime timeCalculation = localTime.minusMinutes(150);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        int minute = localTime.getMinute();

        if (minute % 2 == 1) {
            System.out.println("The time is now " + localTime.format(formatter));
            System.out.println("Codrut has asked me to print t - 2h30m which is " + timeCalculation.format(formatter) + "\n");
            System.out.println("WorldTime API text:" + "\n" + response.body() + "\n");
        }
    }

    public static void timer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    httpRead();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 60 * 1000);
    }
    public static void main(String[] args) {
        timer();
    }
}

// Task completed - Strutocamila achieved!

