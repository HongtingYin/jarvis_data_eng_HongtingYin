package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=d36e8c26edd14ccf995c3ef8eb983d9b
 */
public class RotateString {

    /**
     * Bio-O: O(n^2) where n is the length of input strings
     * .contains() has a complexity of O(n^2)
     *
     * @param A input string
     * @param B input string
     * @return true if A can be rotated to B or reverse, false otherwise
     */
    public boolean isRotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        } else if ((A+A).contains(B)){
            return true;
        } else {
            return false;
        }
    }
}
