package codejam2020.parenting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static class Range {
        private int start;
        private int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean disjoint(Range input) {
            return input.end <= start || end <= input.start;
        }
    }

    public static class Schedule {
        List<Range> schedules = new ArrayList<>();

        public boolean add(Range input) {
            for (Range schedule: schedules) {
                if (!schedule.disjoint(input)) {
                    return false;
                }
            }
            schedules.add(input);
            return true;
        }
    }

    public static void pick2(List<List<Integer>> result, List<Integer> picked, int toPick) {
        if (toPick == 0) { result.add(new ArrayList<>(picked)); return; }
        for (int next = 0; next < 2; ++next) {
            picked.add(next);
            pick2(result, picked, toPick - 1);
            picked.remove(picked.size()-1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            List<Range> schedules = new ArrayList<>();
            for (int s = 0; s < n; s++) {
                int start = in.nextInt();
                int end = in.nextInt();
                schedules.add(new Range(start, end));
            }
            List<List<Integer>> ps = new ArrayList<>();
            pick2(ps, new ArrayList<>(), n);

            List<Integer> selected = null;

            for (List<Integer> p: ps) {
                Schedule J = new Schedule();
                Schedule C = new Schedule();
                boolean pass = true;
                for (int idx = 0; idx < p.size(); idx++) {
                    boolean ok;
                    if (p.get(idx) == 0) {
                        ok = J.add(schedules.get(idx));
                    } else {
                        ok = C.add(schedules.get(idx));
                    }
                    if (!ok) {
                        pass = false;
                        break;
                    }
                }
                if (pass) {
                    selected = p;
                    break;
                }
            }

            String result = "";
            if (selected != null) {
                for (Integer p : selected) {
                    if (p == 0) {
                        result += "C";
                    } else {
                        result += "J";
                    }
                }
            } else {
                result = "IMPOSSIBLE";
            }
            System.out.println("Case #" + i + ": " + result);
        }
    }
}
