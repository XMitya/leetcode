package leetcode;

import java.util.Arrays;

public class RemoveDuplicatesInSortedArray {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2, 3, 3, 4, 5, 6, 6, 6, 7, 8};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] nums = {1};

        final int len = removeDuplicates2(nums);
        System.out.println(len);
        System.out.println(Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                System.arraycopy(nums, i + 1, nums, i, len - 1 - i);
                i--;
                len -= 1;
            }
        }

        return len;
    }

    public static int removeDuplicates2(int[] nums) {
        int prevIdx = 0;
        int nextIdx = 1;
        for (; nextIdx < nums.length; nextIdx++) {
             if (nums[prevIdx] != nums[nextIdx]) {
                 if (nextIdx - prevIdx > 1)
                     nums[++prevIdx] = nums[nextIdx];
                 else
                     prevIdx++;
             }
        }

        return prevIdx + 1;
    }
}
