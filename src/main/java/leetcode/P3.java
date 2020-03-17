package leetcode;

public class P3 {
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;

        int start = 0;
        int end = 1;
        int max = 1;
        while (end < s.length()) {
            int index = -1;
            for (int i = start; i < end; i++) {
                if(s.charAt(i) == s.charAt(end)) {
                    index = i;
                    break;
                }
            }
            if (index < 0) {
                int len = end + 1 - start;
                if (len > max) {
                    max = len;
                }
            } else {
                start = index + 1;
            }
            end = end + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new P3().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new P3().lengthOfLongestSubstring("b"));
        System.out.println(new P3().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new P3().lengthOfLongestSubstring(""));
    }
}
