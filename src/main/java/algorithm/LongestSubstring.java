package algorithm;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstring {
    public static void main(String[] args) {
        test("abcabcbb");
        test("bbbbb");
        test("pwwkew");
        test("pwwkewabcd");
        test("wabcdpwwke");
        test("dvdf"); // 3
    }

    private static void test(String s) {
        LongestSubstring l = new LongestSubstring();

        System.out.printf("%s %s\n", s, l.lengthOfLongestSubstringFast(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1)
            return s.length();

        // fast path
        final Set<Character> uniqueChars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            uniqueChars.add(s.charAt(i));
        }
        final int maxLen = uniqueChars.size();

        final Set<Character> longestChars = new HashSet<>();
        int longest = 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = longestChars.size();

            longestChars.add(s.charAt(i));

            int size = longestChars.size();
            // fast path
            if (size == maxLen)
                return maxLen;

            if (size == len) {
                longest = Math.max(size, longest);

                longestChars.clear();

                i = start;
                start++;
            }
        }

        return Math.max(longest, longestChars.size());
    }

    public int lengthOfLongestSubstring2(String s) {
        final int stringLen = s.length();
        if (stringLen == 0 || stringLen == 1)
            return stringLen;

        // fast path
        final Set<Character> uniqueChars = new HashSet<>();
        for (int i = 0; i < stringLen; i++) {
            uniqueChars.add(s.charAt(i));
        }
        final int maxLen = uniqueChars.size();

        final Set<Character> longestChars = new HashSet<>();
        int longest = 1;
        int start = 0;
        for (int i = 0; i < stringLen; i++) {
            if (longestChars.add(s.charAt(i))) {
                // fast path
                if (longestChars.size() == maxLen)
                    return maxLen;
            } else {
                int size = longestChars.size();
                longest = Math.max(size, longest);

                longestChars.clear();

                i = start;
                start++;
            }
        }

        return Math.max(longest, longestChars.size());
    }

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/solutions/4007059/java-by-using-hashset-2-pointer-approach-optimised-explained/
    public int lengthOfLongestSubstringFast(String s) {
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);

            } else {
                while (s.charAt(left) != s.charAt(right)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(s.charAt(left));
                left++;
                set.add(s.charAt(right));
            }

        }
        return maxLength;
    }


}
