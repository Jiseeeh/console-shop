package Helper;

import java.util.concurrent.TimeUnit;

public class UIHelper {
    public static void sleep(long seconds, String message) {
        try {
            System.out.printf("""
                                            
                    ---------------------------------------------------
                    %s
                    ---------------------------------------------------
                                        
                    """, message);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loginSuccess() {
        sleep(2, "Login success! Redirecting you to your dashboard...");
    }

}
