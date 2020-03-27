package leetcode;

import java.util.HashMap;
import java.util.Map;

public class P388 {

    public int lengthLongestPath(String input) {
        Map<Integer, Integer> lengths = new HashMap<>();
        int i = 0;
        int max = 0;
        int depth = 0, length = 0;
        boolean isFile = false;
        while(i < input.length()) {
            char c = input.charAt(i);
            if (c == '\n' || i == input.length() - 1) {
                if (i == input.length() - 1) length++;

                lengths.put(depth, length);
                if (isFile) {
                    int sum = depth;
                    for (int key : lengths.keySet()) {
                        if (key <= depth) {
                            sum += lengths.get(key);
                        }
                    }
                    if (sum > max) {
                        max = sum;
                    }
                }
                depth = 0;
                length = 0;
                isFile = false;
            } else if (c == '.') {
                isFile = true;
                length++;
            } else if (c == '\t') {
                depth++;
            } else {
                length++;
            }
            i++;
        }
        return max;
    }

    public static void main(String[] args) {
        String s0 =
                "dir\n" +            // 3
                "\tsubdir1\n" +      // 7
                "\t\tfile1.ext\n" +  // 9
                "\t\tsubsubdir1\n" + // 10
                "\tsubdir2\n" +      // 7
                "\t\tsubsubdir2\n" + // 10
                "\t\t\tfile2.ext";   // 9

        String s1 = "a.txt";
        String s2 =
                "a\n" +
                "\taa\n" +
                "\t\taaa\n" +
                "\t\t\tfile1.txt\n" +
                "aaaaaaaaaaaaaaaaaaaaa\n" +
                "\tsth.png";

        System.out.println(new P388().lengthLongestPath(s2));
    }
}
