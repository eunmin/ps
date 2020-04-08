package codejam2020.esab;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        int b = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            String buffer = "";
            Set<String> arrays = new HashSet<>();
            for (int j = 0; j < 150; ++j) {
                if (buffer.length() == b) {
                    arrays.add(buffer);
                    if (arrays.size() > 2) break;
                    buffer = "";
                }
                System.out.println((j % b) + 1);
                String response = in.next();

                buffer += response;
            }

            boolean correct = false;
            for (String array: arrays) {
                System.out.println(array);
                String result = in.next();
                if (result.equals("Y")) {
                    correct = true;
                    break;
                } else {
                    System.exit(0);
                }
            }
        }
        System.exit(0);
    }
}
