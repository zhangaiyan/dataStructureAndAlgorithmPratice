/**
 * @author zhangchaoyue
 * @date 2019/11/29
 */
public class PracticeForSimpleRanking {

    public static void main(String[] args) {
        int[] arr = generateRandom();
//        bubbleSort(arr);
//        selectSort(arr);
        insertionSort(arr);
        displayResult(arr);

    }

    /**
     * 冒泡排序
     * 外层循环控制每一轮比较的次数，内存循环进行比较并交换
     *
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

    /**
     * 选择排序
     * 逐个选择最小值放在最左端
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }

    /**
     * 插入排序
     * 临界值左边为已排序部分，右边为未排序部分
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        //已排序部分
        for (int i = 1; i < arr.length; i++) {
            //标记了未排序部分最左端的数据
            int temp = arr[i];
            int j = i;
            //将未排序部分逐个插入到已排序部分
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swap(int[] arr, int j) {
        swap(arr, j + 1, j);
    }

    private static void swap(int[] arr, int i, int min) {
        int t = arr[min];
        arr[min] = arr[i];
        arr[i] = t;
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
