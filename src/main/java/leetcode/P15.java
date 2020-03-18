package leetcode;

import java.util.*;

public class P15 {
    public Set<List<Integer>> result = new HashSet<>();

    public void solve(int n, int[] nums, List<Integer> picked) {
        if (picked.size() == 3) {
            int sum = 0;
            for (Integer value: picked) {
                sum += value;
            }
            if (sum == 0) {
                List<Integer> value = new ArrayList<>(picked);
//                Collections.sort(value);
                result.add(value);
            }
            return;
        }
        for (int i = n ; i < nums.length; i++) {
            picked.add(nums[i]);
            solve(i + 1, nums, picked);
            picked.remove(picked.size() - 1);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        solve(0, nums, new ArrayList<>());
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        System.out.println(new P15().threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    }
}
