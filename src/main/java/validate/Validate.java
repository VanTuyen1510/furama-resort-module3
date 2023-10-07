package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String REGEX_CUSTOMER_ID = "^(KH-)\\d{4}$";
    private static final String REGEX_PHONE = "^(09|\\(84\\)\\+9)[01]\\d{7}$";
    private static final String REGEX_ID_CARD = "^\\d{9}|\\d{12}$";
    private static final String REGEX_EMAIL = "^\\w{3,}(\\.?\\w+)*@[a-z]{2,7}(.[a-z]{2,5}){1,3}$";

    public static boolean regexPhone(String phone) {
        pattern = Pattern.compile(REGEX_PHONE);
        matcher = pattern.matcher(phone);
        if (!matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean regexIdCard(String idCard) {
        pattern = Pattern.compile(REGEX_ID_CARD);
        matcher = pattern.matcher(idCard);
        if (!matcher.find()) {
            return true;
        }
        return false;
    }

}