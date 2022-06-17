package Helper;

import java.util.concurrent.TimeUnit;

public class UIHelper {
    /*
     * @param seconds {How long the message will show.}
     * @param message {Message to be displayed.}
     */
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

    // ? I made this a method here because I don't want to redundantly type the code
    // ? in one place.
    public static void loginSuccess() {
        sleep(2, "Login success! Redirecting you to your dashboard...");
    }
}
