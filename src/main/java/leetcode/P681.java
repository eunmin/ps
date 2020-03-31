package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class P681 {
    public String nextClosestTime(String time) {
        List<Integer> digits = new ArrayList<>();
        digits.add(Integer.parseInt(time.substring(0,1)));
        digits.add(Integer.parseInt(time.substring(1,2)));
        digits.add(Integer.parseInt(time.substring(3,4)));
        digits.add(Integer.parseInt(time.substring(4,5)));

        TreeMap<Integer, String> times = new TreeMap<>();
        int inputMin = 0;

        for (int h1: digits) {
            if (h1 < 3) {
                for (int h2: digits) {
                    int hh = (h1 * 10) + h2;
                    if (hh < 24) {
                        for (int m1: digits) {
                            if (m1 < 6) {
                                for (int m2: digits) {
                                    int mm = (m1 * 10) + m2;
                                    if (mm < 60) {
                                        int totalMin = (hh * 60) + mm;
                                        String tm = String.format("%d%d:%d%d", h1, h2, m1, m2);
                                        if (time.equals(tm)) {
                                            inputMin = totalMin;
                                        }
                                        times.put(totalMin, tm);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Map.Entry<Integer, String> entry = times.higherEntry(inputMin);
        if (entry != null) {
            return entry.getValue();
        } else {
            return times.firstEntry().getValue();
        }
    }

    public static void main(String[] args) {
        String result = new P681().nextClosestTime("23:59");
        System.out.println(result);
    }
}
