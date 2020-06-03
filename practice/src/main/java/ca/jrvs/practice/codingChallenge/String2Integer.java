package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=2cbca929aa8843599918c9ea4c441e05
 */
public class String2Integer {

    /**
     * Big-O: O(n)
     *
     * @param string
     * @return
     */
    public int string2IntegerWithBuildInParse(String string){
        int num = 0;
        try
        {
            // the String to int conversion happens here
            num = Integer.parseInt(string.trim());

        }
        catch (NumberFormatException e)
        {
            System.out.println("NumberFormatException: " + e.getMessage());
        }
        return (int)num;
    }

    /**
     * Big-O: O(n) where n is the length of string
     * time complexity of the while loop: O(n)
     *
     * @param string string needs to be converted to integer
     * @return integer of input
     */
    public int string2IntegerWithoutBuildInParse(String string) throws Exception {

        if (string.isEmpty()) return 0;
        int sign = 1, base = 0, i = 0, n = string.length();
        while (i < n && string.charAt(i) == ' ') ++i;
        if (i < n && (string.charAt(i) == '+' || string.charAt(i) == '-')) {
            sign = (string.charAt(i++) == '+') ? 1 : -1;
        }
        while (i < n && string.charAt(i) >= '0' && string.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && string.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (string.charAt(i++) - '0');
        }
        return base * sign;
    }
}
