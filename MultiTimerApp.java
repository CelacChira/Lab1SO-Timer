import java.awt.*;
import java.util.*;

class SoundTask extends TimerTask {
    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Beep la fiecare 2 secunde");
    }
}

class MessageTask extends TimerTask {
    String mesaj;
    public MessageTask(String mesaj) {
        this.mesaj = mesaj;
    }
    @Override
    public void run() {
        System.out.println(mesaj);
    }
}

public class MultiTimerApp {
    public static void main(String[] args) {
        System.out.println("Program pornit...");

        // 1. Timer care rulează la fiecare 2 secunde
        Timer t1 = new Timer();
        t1.scheduleAtFixedRate(new SoundTask(), 0, 2000);

        // 2. Timer care rulează la o oră fixă (ex: 18:30)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);
        Date oraFixata = calendar.getTime();

        Timer t2 = new Timer();
        t2.schedule(new MessageTask("Este ora 18:30!"), oraFixata);

        // 3. Timer care rulează la fiecare 5 secunde, timp de 20 secunde
        Timer t3 = new Timer();
        t3.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                counter += 5;
                System.out.println("Au trecut " + counter + " secunde");
                if(counter >= 20) {
                    System.out.println("Timerul s-a oprit.");
                    this.cancel();
                }
            }
        }, 0, 5000);
    }
}
