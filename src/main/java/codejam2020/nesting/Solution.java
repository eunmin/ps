package codejam2020.nesting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int num = 1; num <= t; ++num) {
            String s = in.next();
            String result = "";
            int depth = 0;
            for (int i = 0; i < s.length(); i++) {
                int currDepth = s.charAt(i) - 48;
                int count = depth - currDepth;
                if (count > 0) {
                    for (int n = 0; n < count; n++) {
                        result += ")";
                    }
                } else if (count < 0) {
                    for (int n = 0; n < (count * -1); n++) {
                        result += "(";
                    }
                }
                result += s.charAt(i);
                depth = currDepth;
            }
            for (int n = 0; n < depth; n++) {
                result += ")";
            }
            System.out.println("Case #" + num + ": " + result);
        }

    }
}
