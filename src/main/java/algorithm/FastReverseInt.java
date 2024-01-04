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

// Example 3:
//
// Input: x = 120
// Output: 21
public class FastReverseInt {
    public static void main(String[] args) {
        final FastReverseInt r = new FastReverseInt();
//        System.out.println(r.reverse(123));
//        System.out.println(r.reverse(-123));
//        System.out.println(r.reverse(Integer.MAX_VALUE));
//        System.out.println(r.reverse(123456789));
//        System.out.println(r.reverse(987654321));
//        System.out.println(r.reverse(1234567890));
//        System.out.println(r.reverse(-1563847412));
//        System.out.println(r.reverse(1534236469));
//        System.out.println(r.reverse(-2147483412));
        System.out.println(r.reverse(1463847412));
    }

    public int reverse(int x) {
        final boolean negative = x < 0;
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        x = Math.abs(x);

        final int size = getSize(x);

        if (size == 10) {
            final int divider = (int) Math.pow(10, 5);
            int hi = x / divider;
            int lo = x % divider;

            for (int i = 0; i < 5; i++) {
                final int loDivider = (int) Math.pow(10, i);
                final int hiDivider = (int) Math.pow(10, 4 - i);

                final int loDigit = getDigit(lo, loDivider);
                final int hiDigit = getDigit(hi, hiDivider);

                if (loDigit == hiDigit)
                    continue;

                hi = setDigit(hi, loDigit, hiDivider);
                lo = setDigit(lo, hiDigit, loDivider);
            }

            if (hi > Integer.MAX_VALUE / divider)
                return 0;

            x = hi * divider + lo;
            if (x < 0) {
                return 0;
            }
        } else {
            for (int i = 0; i < size; i++) {
                x = swap(x, i, size - 1 - i);

                if (x < 0)
                    return 0;
            }
        }

        if (negative)
            return -x;

        return x;
    }

    private int getDigit(int x, int divider) {
        return x / divider % 10;
    }

    private int setDigit(int x, int digit, int divider) {
        final int hi = x / divider / 10 * 10 + digit;
        final int lo = x % divider;

        return hi * divider + lo;
    }

    private int getSize(int x) {
        for (int i = 1; i <= 10; i++) {
            x /= 10;

            if (x == 0)
                return i;
        }

        return 10;
    }

    private int swap(int x, int i, int j) {
        if (i >= j)
            return x;

        final int divider1 = (int) Math.pow(10, i);
        final int divider2 = (int) Math.pow(10, j);

        final int digit1 = x / divider1 % 10;
        final int digit2 = x / divider2 % 10;

        if (digit1 == digit2)
            return x;

        int hi = x / divider2 / 10 * 10 + digit1;
        int lo = x % divider2;
        x = hi * divider2 + lo;

        hi = x / divider1 / 10 * 10 + digit2;
        lo = x % divider1;
        x = hi * divider1 + lo;

        return x;
    }

    public int simple_reverse(int x) {
        int result = 0;

        while (x != 0) {
            int lastDigit = x % 10;


            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && lastDigit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && lastDigit < -8)) {
                return 0;
            }

            result = result * 10 + lastDigit;
            x /= 10;
        }

        return result;
    }

}
