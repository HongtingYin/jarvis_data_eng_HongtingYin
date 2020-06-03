package ca.jrvs.practice.codingChallenge;

/**
 * tikcet: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=1ab59eed9bca4625b163777bee5af5b5
 */
public class ValidPalindrome {

    /**
     * Big-O: O(n) where n is the length of input string
     * a while loop to traverse input string
     *
     * @param s input string
     * @return true if input string is palindromic, false otherwise
     */
    public boolean isPalindromeWithTwoPointer(String s) {
        // convert the entire string to lowercase
        s = s.toLowerCase();
        int start = 0;
        int end = s.length()-1;

        while(start <= end){

            // both start and end are alphanumeric
            char c_start = s.charAt(start);
            char c_end = s.charAt(end);

            boolean c_start_valid = isAlphaNumeric(c_start);
            boolean c_end_valid = isAlphaNumeric(c_end);

            if(c_start_valid && c_end_valid){
                // subcase 1: they are valid, but do the chars do not equal
                if(c_start != c_end)
                    return false;

                // subcase 2: they are valid and chars equal
                start++;
                end--;
            }
            else{
                if(!c_start_valid && !c_end_valid){
                    // subcase 1: both are invalid
                    start++;
                    end--;
                }
                else if(c_start_valid && !c_end_valid){
                    // subcase 2: c_start is valid, but c_end is not
                    end--;
                }
                else{
                    // subcase 3: c_start is not valid, bu c_end is valid
                    start++;
                }

            }

        }
        return true;
    }

    /**
     * a helper function to check whether a character is one of the alphabet or number
     *
     * @param c input character
     * @return an alphabet or a number
     */
    public boolean isAlphaNumeric(char c){
        boolean is_alpha = c >= 'a' && c <= 'z';
        boolean is_numeric = c >= '0' && c <= '9';

        return is_alpha || is_numeric;
    }

    /**
     * Big-O: O(n) where n is the length of input string
     * a while loop to traverse input string
     *
     * @param s input string
     * @return true if input string is palindromic, false otherwise
     */
    public boolean isPalindromeWithRecursion(String s) {
        if(s == null)
            return false;

        return isPalindromeRecursion(s.toLowerCase(),0,s.length()-1);
    }

    public boolean isPalindromeRecursion(String s, int l, int r){
        if(l >= r)
            return true;
        // the character at index l is invalid
        if((s.charAt(l) < 'a' || s.charAt(l) > 'z') && (s.charAt(l) < '0' || s.charAt(l) > '9'))
            return isPalindromeRecursion(s,++l,r);
        // the character at index r is invalid
        else if((s.charAt(r) < 'a' || s.charAt(r) > 'z') && (s.charAt(r) < '0' || s.charAt(r) > '9'))
            return isPalindromeRecursion(s,l,--r);
        // the character at index l and r are valid but not equal
        else if(s.charAt(l) != s.charAt(r))
            return false;

        return isPalindromeRecursion(s,++l,--r);
    }

}
