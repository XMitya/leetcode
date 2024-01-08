package algorithm;

import java.util.*;

public class LongestPalindrome {
    public static void main(String[] args) {
        test("babad");
        test("cbbd");
        test("lanna");
        test("anna");
        test("kabcdefedcbaj");
        test("kabcdef");
        test("a");
        test("ccc");
        test("aacabdkacaa");
        test("321012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210123210012321001232100123210123");
    }

    private static void test(String s) {
        final LongestPalindrome lp = new LongestPalindrome();

        System.out.printf("Input: %s, longest palindrome: %s\n", s, lp.longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1)
            return s;

        // O(Nlog(N))
        final Map<Character, NavigableSet<Integer>> indexMap = indexMap(s);
        int palindromeStartIdx = 0;
        int palindromeEndIdx = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            final char firstChar = s.charAt(i);
            final NavigableSet<Integer> indexes = indexMap.get(firstChar);
            if (indexes.size() == 1)
                continue;

            // fast path
            final int palLen = palindromeEndIdx - palindromeStartIdx;
            if (palLen >= s.length() - i)
                break;

            for (Iterator<Integer> it = indexes.descendingIterator(); it.hasNext();) {
                int index = it.next();
                if (palLen >= index - i || index <= i)
                    break;

                if (isPalindrome(i, index, s)) {
                    palindromeStartIdx = i;
                    palindromeEndIdx = index;

                    break;
                }
            }
        }

        if (palindromeEndIdx - palindromeStartIdx > 0) {
            return s.substring(palindromeStartIdx, palindromeEndIdx + 1);
        }

        return s.substring(0, 1);
    }

    private boolean isPalindrome(int startIdx, int endIdx, String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            final int begin = startIdx + i;
            final int end = endIdx - i;
            if (begin < end && s.charAt(begin) != s.charAt(end))
                return false;
        }

        return true;
    }

    private Map<Character, NavigableSet<Integer>> indexMap(String s) {
        final Map<Character, NavigableSet<Integer>> indexMap = new HashMap<>();
        // O(N)
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);

            final NavigableSet<Integer> indexSet = indexMap.computeIfAbsent(c, k -> new TreeSet<>());
            // O(log(N))
            indexSet.add(i);
        }

        return indexMap;
    }
}
