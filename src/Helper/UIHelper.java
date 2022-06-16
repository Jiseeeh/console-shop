package Helper;

import java.util.concurrent.TimeUnit;

public class UIHelper {
    public static void sleep (long seconds ,String message) {
        try {
            System.out.println(message);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
