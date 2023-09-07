package algorithm;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 0, 2, 3, 0, 3};
        System.out.println("input: " + Arrays.toString(arr));
        final int[] sorted = sort(arr);

        System.out.println("result: " + Arrays.toString(sorted));
    }

    public static int[] sort(int[] arr) {
        int[] count = new int[6];
        int[] out = new int[arr.length];

        for (int j = 0; j < arr.length; j++) {
            count[arr[j]]++;
        }

        System.out.println("count: " + Arrays.toString(count));

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        System.out.println("count: " + Arrays.toString(count));

//        for (int j = arr.length - 1; j >= 0; j--) {
//            System.out.println();
//            System.out.println("index: " + j);
////            out[count[arr[j]] - 1] = arr[j];
////            count[arr[j]]--;
//            final int value = arr[j];
//            System.out.println("value: " + value);
//            System.out.println("out index: " + (count[value] - 1));
//            out[count[value] - 1] = value;
//            System.out.println("out: " + Arrays.toString(count));
//            count[value]--;
//            System.out.println("count: " + Arrays.toString(count));
//        }
        for (int j = 0; j < arr.length; j++) {
            System.out.println();
            System.out.println("index: " + j);

            final int value = arr[j];

            System.out.println("value: " + value);
            System.out.println("out index: " + (count[value] - 1));

            out[count[value] - 1] = value;

            System.out.println("out: " + Arrays.toString(count));

            count[value]--;

            System.out.println("count: " + Arrays.toString(count));
        }

        System.out.println("count: " + Arrays.toString(count));

        return out;
    }
}
