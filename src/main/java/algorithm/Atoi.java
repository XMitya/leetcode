package algorithm;

/*
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

The algorithm for myAtoi(string s) is as follows:

Read in and ignore any leading whitespace.
Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
Return the integer as the final result.
 */
public class Atoi {
    private static final char LOW = '0';
    private static final char HIGH = '9';
    private static final char MINUS = '-';
    private static final char PLUS = '+';
    private static final char SPACE = ' ';
    private static final int START = 0;
    private static final int SIGN = 1;
    private static final int DIGIT = 1;


    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("42"));
        System.out.println(atoi.myAtoi("   -42"));
        System.out.println(atoi.myAtoi("4193 with words"));
        System.out.println(atoi.myAtoi("    +4193 with words"));
        System.out.println(atoi.myAtoi("    +2147483647 with words"));
        System.out.println(atoi.myAtoi("    -2147483647 with words"));
        System.out.println(atoi.myAtoi("    -2147483648 with words"));
        System.out.println(atoi.myAtoi("    -2147483649 with words"));
        System.out.println(atoi.myAtoi("    -21474836470 with words"));
        System.out.println(atoi.myAtoi("    -21474 83647 with words"));
        System.out.println(atoi.myAtoi("words and 987"));
        System.out.println(atoi.myAtoi("+-12"));
        System.out.println(atoi.myAtoi("-+12"));
        System.out.println(atoi.myAtoi("--12"));
        System.out.println(atoi.myAtoi("++12"));
        System.out.println(atoi.myAtoi("  0000000000012345678"));
        System.out.println(atoi.myAtoi("00000-42a12348"));
        System.out.println(atoi.myAtoi("2147483800"));
        System.out.println(atoi.myAtoi("  +  413"));
    }

    public int myAtoi(String s) {
        boolean isNegative = false;
        int state = START;
        int result = 0;
        int digits = 0;

        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == SPACE && state == START)
                continue;

            state++;

            if (c == MINUS && state == SIGN)
                isNegative = true;
                // is digit
            else if (c == PLUS && state == SIGN)
                continue;
            else if (c >= LOW && c <= HIGH && state >= DIGIT) {
                final int digit = c - LOW;

                if (result == 0 && digit == 0)
                    continue;

                digits++;

                // overflow check
                if (result == Integer.MAX_VALUE / 10) {
                    if (isNegative && digit >= 8) {
                        return Integer.MIN_VALUE;
                    } else if (!isNegative && digit >= 7) {
                        return Integer.MAX_VALUE;
                    }
                } else if (result >= Integer.MAX_VALUE / 10) {
                    return isNegative
                            ? Integer.MIN_VALUE
                            : Integer.MAX_VALUE;
                }

                if (digits > 10) {
                    if (isNegative)
                        return Integer.MIN_VALUE;
                    else
                        return Integer.MAX_VALUE;
                }

                if (result != 0)
                    result *= 10;

                result += digit;
            } else {
                break;
            }
        }

        return isNegative
                ? -result
                : result;
    }
}
