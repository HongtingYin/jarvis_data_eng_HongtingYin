package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=2cbca929aa8843599918c9ea4c441e05
 */
public class TwoSum {

    /**
     * Big-O: O(n^2) where n is the length of input array
     * nested for loop
     *
     * @param arr input array
     * @param target target sum
     * @return a list of indexes whose elements add up to the target
     */
    public int[] twoSumForLoop(int[] arr, int target) {
        for(int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++){
                if (arr[i] + arr[j] == target)
                    return new int[]{i,j};
            }
        }
        return new int[]{};
    }

    /**
     * Big-O: O(n*log(n)) where n is the length of input array
     * Arrays.sort is O(n*log(n)), iterating over for loop is O(n) so the time complexity is O(n*log(n))
     *
     * @param arr input array
     * @param target target sum
     * @return a list of indexes whose elements add up to target
     */
    public int[] twoSumSort(int[] arr, int target) {
        Arrays.sort(arr);
        int right = arr.length-1;
        for (int left = 0; left < right; left++) {
            if (arr[left] + arr[right] == target) {
//                return new int[]{left, right};
                return new int[]{arr[left], arr[right]};
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }

    /**
     * Big-O: O(n) where n is the length of array
     * iterating over for loop is O(n)
     *
     * @param arr
     * @param target
     * @return
     */
    public int[] twoSumHashMap(int[] arr, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length-1; i++) {
            int complement = target - arr[i];
            if (hashMap.containsKey(complement)){
                return new int[]{hashMap.get(complement), i};
            } else {
                hashMap.put(arr[i], i);
            }
        }
        return new int[]{};
    }
}
