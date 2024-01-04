package algorithm;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        MedianOfTwoSortedArrays med = new MedianOfTwoSortedArrays();
//        System.out.println(med.findMedianSortedArrays(new int[]{1, 3/*, 10, 30*/}, new int[]{2, 5, 8}));
        System.out.println(med.findMedianSortedArrays(new int[]{1, 4}, new int[]{2, 8}));
        System.out.println(med.findMedianSortedArrays(new int[]{1, 4, 6}, new int[]{2, 8, 10}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            // todo
        }

        if (nums2.length == 0) {
            // todo
        }

        final int min = min(nums1, nums2);
        final int max = max(nums1, nums2);

        int mid = (min + max) / 2;
        int step = mid;
        while (true) {
            final boolean realNumber = ((nums1.length + nums2.length) & 1) > 0;
            final Numbers num1 = findNumbers(nums1, mid);
            final Numbers num2 = findNumbers(nums2, mid);
            final int left = num1.left + num2.left;
            final int right = num1.right + num2.right;

            if (left == right) {
                // bingo!
                if (realNumber && (num1.exists || num2.exists)) {
                    return mid;
                }
            } else {
                if (!realNumber && (num1.exists || num2.exists) && Math.abs(left - right) == 1) {
                    int closest;
                    if (left > right) {
                        final int closest1 = findClosest(nums1, mid, false);
                        final int closest2 = findClosest(nums2, mid, false);
                        closest = Math.max(closest1, closest2);
                    } else {
                        final int closest1 = findClosest(nums1, mid, true);
                        final int closest2 = findClosest(nums2, mid, true);
                        closest = Math.min(closest1, closest2);
                    }

                    return (((double) mid) + closest) / 2;
                }

                step = Math.max(step / 2, 1);
                if (left > right) {
                    mid -= step;
                } else {
                    mid += step;
                }
            }
        }

//        return 0;
    }

    private int findClosest(int[] nums, int pivotal, boolean greater) {
        final int idx = Arrays.binarySearch(nums, pivotal);
        if (greater) {
            if (idx < 0) {
                int insIdx = Math.abs(idx) - 1;
                if (insIdx < nums.length) {
                    return nums[insIdx];
                }

                return last(nums);
            } else {
                if (idx < nums.length - 1) {
                    return nums[idx + 1];
                }

                return last(nums);
            }
        } else {
            if (idx < 0) {
                int insIdx = Math.abs(idx) - 1;
                if (insIdx >= nums.length) {
                    return last(nums);
                }

                return nums[Math.max(0, insIdx - 1)];
            } else {
                if (idx > 0) {
                    return nums[idx - 1];
                }

                return nums[0];
            }
        }
    }

    private Numbers findNumbers(int[] nums, int pivotal) {
        final int idx = Arrays.binarySearch(nums, pivotal);
        final boolean exists = idx >= 0;

        final int closeIdx = !exists
                ? Math.abs(idx) - 1
                : idx;
        int left;

        if (!exists && closeIdx == 0) {
            left = 0;
        } else if (!exists && closeIdx == nums.length) {
            left = nums.length;
        } else {
            left = closeIdx;
        }

        return new Numbers(left,
                           exists
                                   ? nums.length - left - 1
                                   : nums.length - left,
                           exists);
    }

    private int min(int[] nums1, int[] nums2) {
        return Math.min(nums1[0], nums2[0]);
    }

    private int max(int[] nums1, int[] nums2) {
        return Math.max(last(nums1), last(nums2));
    }

    private int last(int[] nums) {
        return nums[nums.length - 1];
    }

    private record Numbers(int left, int right, boolean exists) {
    }
}
