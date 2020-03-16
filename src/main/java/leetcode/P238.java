package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] l = new int[n];
        l[0] = 1;
        for (int i = 1; i < n; i++) {
            l[i] = nums[i - 1] * l[i - 1];
        }
        int[] r = new int[n];
        r[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            r[i] = nums[i + 1] * r[i + 1];
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = l[i] * r[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = new P238().productExceptSelf(new int[] {1, 2, 3, 4});
        List<Integer> l = Arrays.stream(result).boxed().collect(Collectors.toList());
        System.out.println(l);
    }
}
