package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=cbf97ad2e2f146d4ba86ffc14abdaeda
 */
public class OddEven {

    /**
     * Big-O: O(1)
     * Arithmetic operation
     *
     * @param number input
     * @return
     */
    public String isOddEvenMod (int number) {
        if (number % 2 == 0)
            return "even";
        else
            return "odd";
    }

    /**
     * Big-O: O(1)
     * bitwise operation
     *
     * @param number input
     * @return
     */
    public String isOddEvenBit (int number) {
        if ((number & 1) == 0)
            return "even";
        else
            return "odd";
    }
}
