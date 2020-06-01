package ca.jrvs.practice.search;

import java.util.Optional;

public class BinarySearch {

    /**
     * find the the target index in a sorted array
     *
     * @param arr input arry is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not found
     */
    public <E> Optional<Integer> binarySearchRecursion(E[] arr, E target) {

        E[] array = arr;
        int l = arr.length;
        E tar = target;

        return binarySearch(array, 0, l - 1, tar);
    }

    private <E> Optional<Integer> binarySearch(E[] array, int low, int high, E target){

        if (low > high) {
            return Optional.empty();
        } else {
            int mid = (low + high) / 2;
            if (array[mid] == (target)) {
                System.out.println(target + " found at index " + mid);
                return Optional.of(mid);
            } else if ((Integer)target < (Integer)array[mid]) {
                return binarySearch(array, low, mid-1, target);
            } else {
                return binarySearch(array, mid+1, high, target);
            }
        }
    }

    /**
     * find the the target index in a sorted array
     *
     * @param arr input arry is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not found
     */
    public <E> Optional<Integer> binarySearchIteration(E[] arr, E target) {

        //Set low & high for complete list
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == (target)) {
                System.out.println(target +" found at index "+ mid);
            }  else if ((Integer)target < (Integer) arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return Optional.empty();
    }


}
