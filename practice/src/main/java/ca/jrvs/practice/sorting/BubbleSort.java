package ca.jrvs.practice.sorting;

public class BubbleSort {
    /**
     * In-place bubble sort
     * @param numbers
     */
    public static int[] bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length -1; j > i; j--) {
                if (numbers[j] < numbers[j - 1]) {
                    swap(numbers, j, j-1);
                }
            }
        }
        return numbers;
    }

    public static int[] bubblesort(int[] numbers){
        for (int i = numbers.length-1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if (numbers[j] > numbers[j+1]) {
                    swap(numbers, j, j+1);
                }
            }
        }
        return numbers;
    }

    /*
     * Utility method to swap two numbers in array
     */
    public static void swap(int[] array, int from, int to){
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

}
