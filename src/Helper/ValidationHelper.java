package Helper;

public class ValidationHelper {
    public static boolean hasInvalidInput(String...params) {
        int truthCount = 0;

        if (isNull(params)) truthCount += 1;
        if (isSpace(params)) truthCount += 1;

        return truthCount == 2;
    }

    public static boolean isNull (String... params) {
        boolean result = false;

        for (String param : params) {
            if (!(param == null)) continue;
            result = true;
        }
        return result;
    }

    public static boolean isSpace (String...params) {
        boolean result = false;

        for (String param : params){
            if (!param.equals(" ")) continue;
            result = true;
            break;
        }
        return result;
    }
}
