package DAL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static String checkEmpty(String s) {
        if (s.isEmpty()) {
            return "* Please fill out this field!";
        }

        return "";
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String checkNum(String s) {
        if (s.isEmpty()) {
            return "* Please enter a number!";
        }

        if (!isNumeric(s) || s.contains(" ")) {
            return "* Input must be a number!";
        }

        return "";
    }
    public boolean isSecurePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
