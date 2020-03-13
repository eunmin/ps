package leetcode;

public class P5 {
    public String longestPalindrome(String s) {
        if (s.isEmpty()) return "";

        for (int wordLength = s.length(); wordLength > 0; wordLength--) {
            for (int i = 0; i < (s.length()+1-wordLength); i++) {
                int start = i;
                int end = start + wordLength - 1;
                int left = start;
                int right = end;
                boolean found = true;
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        found = false;
                        break;
                    }
                    left++;
                    right--;
                }
                if (found) {
                    return s.substring(start, end + 1);
                }
            }
        }
        return s.substring(0,1);
    }

    public static void main(String[] args) {
        P5 p = new P5();

        System.out.println(p.longestPalindrome("babad"));
    }
}
