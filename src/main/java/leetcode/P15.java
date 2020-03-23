package leetcode;

import java.util.*;

public class P15 {

    public Map<Integer, Integer> hashedNums = new HashMap<>();
    public Set<List<Integer>> result = new HashSet<>();

    public void solve(int n, int[] nums, List<Integer> picked) {
        if (picked.size() == 2) {
            int first = picked.get(0);
            int second = picked.get(1);
            int sum = first + second;
            int target = sum * -1;
            Integer count = hashedNums.get(target);
            if (count != null) {
                boolean found = true;
                if ((target == first || target == second) && (count < 2)) {
                    found = false;
                }
                if (found) {
                    List<Integer> list = new ArrayList<>(picked);
                    list.add(sum * -1);
                    Collections.sort(list);
                    result.add(list);
                }
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
        for (int num : nums) {
            Integer count = hashedNums.get(num);
            if (count == null) {
                hashedNums.put(num, 0);
            } else {
                hashedNums.put(num, count + 1);
            }
        }
        solve(0, nums, new ArrayList<>());
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        int size = 1000;
        Random rnd = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rnd.nextInt(10000) - 5000;
        }
        System.out.println(new P15().threeSum(arr));
    }
}
