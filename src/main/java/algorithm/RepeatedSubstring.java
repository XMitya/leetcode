package algorithm;

public class RepeatedSubstring {

    public static void main(String[] args) {
        RepeatedSubstring r = new RepeatedSubstring();

        test("a", r);
        test("ab", r);
        test("aba", r);
        test("abab", r);
        test("abcabcabcabc", r);
        test("abcabcabcabcd", r);
        test("abcabcabcbac", r);
        test("abcabcdabcabcd", r);
    }

    private static void test(String s, RepeatedSubstring r) {
        System.out.printf("%s %s\n", s, r.repeatedSubstringPattern(s));
    }

    public boolean repeatedSubstringPattern(String s) {
        if (s.length() == 1)
            return false;

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        int offset = 1;
        for (int i = 1; i < s.length() / 2 + 1; i++) {
            if (equalsTillEnd(sb, s, offset))
                return true;

            sb.append(s.charAt(i));
            offset++;
        }

        return false;
    }

    private boolean equalsTillEnd(StringBuilder sb, String s, int offset) {
        if (s.length() % sb.length() != 0)
            return false;

        while (offset < s.length()) {
            if (!subEquals(sb, s, offset))
                return false;

            offset += sb.length();
        }

        return true;
    }

    private boolean subEquals(StringBuilder sb, String s, int offset) {
        return sb.subSequence(0, sb.length()).equals(s.subSequence(offset, offset + sb.length()));
    }
}
