package leetcode;

import java.util.HashSet;
import java.util.Set;

public class P904 {
    public int totalFruit(int[] tree) {
        Set<Integer> basket = new HashSet<>();
        int maxTotal = 0;
        int prevFruit = -1;
        int invert = 0;
        int i = 0;
        int start = 0;
        while(i < tree.length) {
            basket.add(tree[i]);
            if (basket.size() > 2) {
                int total = i - start;
                if (total > maxTotal) {
                    maxTotal = total;
                }
                i = invert;
                start = i;
                basket = new HashSet<>();
                prevFruit = -1;
                continue;
            }
            if (prevFruit != tree[i]) {
                invert = i;
            }
            prevFruit = tree[i];
            i++;
        }
        int total = i - start;
        if (total > maxTotal) {
            maxTotal = total;
        }
        return maxTotal;
    }

    public static void main(String[] args) {
        int[] input1 = {1,2,1};
        int[] input2 = {0,1,2,2};
        int[] input3 = {1,2,3,2,2};
        int[] input4 = {3,3,3,1,2,1,1,2,3,3,4};
        int[] input5 = {1,0,3,4,3};

        System.out.println(new P904().totalFruit(input1));
        System.out.println(new P904().totalFruit(input2));
        System.out.println(new P904().totalFruit(input3));
        System.out.println(new P904().totalFruit(input4));
        System.out.println(new P904().totalFruit(input5));
    }
}
