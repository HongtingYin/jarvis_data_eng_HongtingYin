package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=a3307b9abbba432e821c95610e9472f7
 */
public class ValidAnagram {

    /**
     * Big-O: O(nlog(n)) where n the length of input strings
     * a for loop to check whether elements with same index are equal in two arrays, which is O(n)
     * two Arrays.sort() method, which has a time complexity of O(nlog(n))
     *
     * @param A input string
     * @param B input string
     * @return true or false
     */
    public boolean isValidAnagramWithSort(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        if (lenA != lenB)
            return false;

        char[] arrayA = A.toCharArray();
        char[] arrayB = B.toCharArray();
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        for (int i = 0; i < lenA; i++) {
            if (arrayA[i] != arrayB[i])
                return false;
        }
        return true;
    }

    /**
     * Time complexity: O(n) where n is the length of input string
     * two for loops without nested loop
     *
     * Space complexity: O(1)
     * the hash table contains 26-letter spots, therefor O(26) = O(1)
     *
     * @param A input string
     * @param B input string
     * @return true or false
     */
    public boolean isValidAnagramWithoutSort(String A, String B) {

        // a hashmap contains the letters occurs in input string
        HashMap<Character, Integer> chars = new HashMap<>();

        //save all letters from string A and its associated numbers
        for (char cA:A.toCharArray()) {
            int count = chars.getOrDefault(cA, 0);
            count++;
            chars.put(cA, count);
        }

        //traverse string B
        for (char cB:B.toCharArray()) {
            int count = chars.getOrDefault(cB, 0);

            //if there is no corresponding letter from string A
            if (count == 0)
                return false;

            //if there is corresponding letters from string A
            //reduce the count of that letter, remove it from chars and update the count
            count --;
            if(count == 0)
                chars.remove(cB);
            else
                chars.put(cB, count);

        }
        return chars.size() == 0;
    }
}
