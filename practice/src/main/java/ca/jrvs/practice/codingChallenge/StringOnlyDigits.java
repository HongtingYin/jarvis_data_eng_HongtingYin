package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=ff26b4e832ca4ab38f66bd208ec8a26d
 */
public class StringOnlyDigits {

    /**
     * Big-O: O(n) where n is the length of input string
     * a for loop to find if each element in string is a digit
     *
     * @param str input string
     * @return true it string only contains digits,false otherwise
     */
    public boolean stringOnlyDigitsASCII(String str) {
        if (str.length() == 0)
            return false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) >'9')
                return false;
        }
        return true;
    }

    /**
     * Big-O: O(n) where n is the length of input string
     *
     * @param str input string
     * @return true it string only contains digits,false otherwise
     */
    public boolean stringOnlyDigitsAPI(String str) {
        try {
            Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Big-O: O(n) where n is the length of input string
     *
     * @param str input string
     * @return true it string only contains digits,false otherwise
     */
    public boolean stringOnlyDigitsRegex(String str) {
        if (str.matches("\\d+")) {
            return true;
        } else {
            return false;
        }
    }
}
