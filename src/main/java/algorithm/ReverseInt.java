package algorithm;

// Given a signed 32-bit integer x, return x with its digits reversed.
// If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
// Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
// Example 1:
//
// Input: x = 123
// Output: 321

// Example 2:
//
// Input: x = -123
// Output: -321

import java.util.Arrays;

// Example 3:
//
// Input: x = 120
// Output: 21
public class ReverseInt {
    public static void main(String[] args) {
        final ReverseInt r = new ReverseInt();
//        System.out.println(r.reverse(123));
//        System.out.println(r.reverse(-123));
//        System.out.println(r.reverse(Integer.MAX_VALUE));
//        System.out.println(r.reverse(123456789));
//        System.out.println(r.reverse(987654321));
//        System.out.println(r.reverse(1234567890));
        System.out.println(r.reverse(-1563847412));
    }

    public int reverse(int x) {
        final boolean negative = x < 0;
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        x = Math.abs(x);

        final byte[] reversedArr = toIntArray(x);
        final int lastIdx = findLastIdx(reversedArr);
        final byte[] maxValue = toIntArray(Integer.MAX_VALUE);
        reverse(maxValue);

        if (negative) {
            maxValue[maxValue.length - 1] += 1;
        }

        final int cmp = compareReversed(reversedArr, maxValue, lastIdx);

        if (cmp > 0) {
            return 0;
        }

        final int reversedInt = toReversedInt(reversedArr, lastIdx);
        if (negative)
            return -reversedInt;

        return reversedInt;
    }

    private int compareReversed(byte[] arr1, byte[] arr2, int lastIdx) {
        if (lastIdx < arr2.length - 1) {
            return -1;
        }

        for (int i = 0; i < arr1.length; i++) {
            final int cmp = Byte.compare(arr1[i], arr2[i]);
            if (cmp != 0) {
                return cmp;
            }
        }

        return 0;
    }

    private byte[] toIntArray(int x) {
        byte[] arr = new byte[10];
        for (int i = 0; i < arr.length; i++) {
            byte digit = (byte) (x % 10);
            x /= 10;
            arr[i] = digit;
        }

        return arr;
    }

    private void reverse(byte[] arr) {
        for (int i = 0; i < arr.length; i++) {
            final int j = arr.length - 1 - i;
            if (i >= j)
                return;

            final byte tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }
    }

    private int toReversedInt(byte[] arr, int lastIdx) {
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            x += arr[i];

            if (i == lastIdx)
                break;

            if (i < arr.length - 1)
                x *= 10;
        }

        return x;
    }

    private int findLastIdx(byte[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                return i;
            }
        }

        return 0;
    }
}
