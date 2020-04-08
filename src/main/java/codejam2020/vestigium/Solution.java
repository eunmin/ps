package codejam2020.vestigium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int k = 0;
            int r = 0;
            int c = 0;
            List<Set<Integer>> colSets = new ArrayList<>();
            for (int row = 0; row < n; row++) {
                Set<Integer> rowSet = new HashSet<>();
                for (int col = 0; col < n; col++) {
                    int value = in.nextInt();
                    if (row == col) k += value;
                    rowSet.add(value);
                    if (row == 0) colSets.add(new HashSet<>());
                    colSets.get(col).add(value);
                }
                if (rowSet.size() != n) {
                    r += 1;
                }
            }
            for (int col = 0; col < n; col++) {
                if (colSets.get(col).size() != n) {
                    c += 1;
                }
            }
            System.out.println("Case #" + i + ": " + k + " " + r + " " + c);
        }
    }
}