import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangchaoyue
 * @date 2019/11/29
 */
public class PraticeForSimpleRanking {

    public static void main(String[] args) {
        int[] arr = generateRandom();
        bubbleSort(arr);
        displayResult(arr);

    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j);
                }
            }
        }
    }

    public static void selectSort(List<Integer> list) {

    }

    public static void insertionSort(List<Integer> list) {

    }

    private static void swap(int[] arr, int j) {
        int t = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = t;
    }

    private static int[] generateRandom() {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    private static void displayResult(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
