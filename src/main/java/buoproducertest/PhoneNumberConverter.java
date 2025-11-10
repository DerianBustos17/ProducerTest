package buoproducertest;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberConverter {
    private static Map<Character, Character> thePhoneMap = new HashMap<>();

    static {
        String[] groups = {"ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        for (int i = 0; i < groups.length; i++) {
            for (char c : groups[i].toCharArray()) {
                thePhoneMap.put(c, Character.forDigit(i + 2, 10));
            }
        }
    }

    public static String convertToPhoneNumber(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "00000000000";
        }
        
        input = input.toUpperCase().replaceAll("[^A-Z0-9]", "");
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(thePhoneMap.get(c));
            } else {
                result.append(c);
            }
        }

        if (result.length() < 11) {
            while (result.length() < 11) result.append("0");
        } else if (result.length() > 11) {
            result.setLength(11);
        }

        return result.toString();
    }
    
    public static String formatPhoneNumber(String number) {
        if (number.length() == 11) {
            return number.substring(0, 3) + "-" + number.substring(3, 7) + "-" + number.substring(7);
        }
        return number;
    }
}