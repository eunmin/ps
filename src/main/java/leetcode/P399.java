package leetcode;

import java.util.*;

public class P399 {
    public Map<String, Map<String, Double>> buildVars(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> vars = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String left = equation.get(0);
            String right = equation.get(1);

            Map<String, Double> leftVars = vars.get(left);
            if (leftVars == null) {
                leftVars = new HashMap<>();
            }
            leftVars.put(right, values[i]);
            vars.put(left, leftVars);

            Map<String, Double> rightVars = vars.get(right);
            if (rightVars == null) {
                rightVars = new HashMap<>();
            }
            rightVars.put(left, 1.0/values[i]);
            vars.put(right, rightVars);
        }
        return vars;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> vars = buildVars(equations, values);
        System.out.println(vars);

        double[] result = new double[queries.size()];

        for(int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String left = query.get(0);
            String right = query.get(1);

            Map<String, Double> leftVars = vars.get(left);
            if (leftVars == null) {
                result[i] = -1.0;
                continue;
            }
            Double rightValue = leftVars.get(right);
            if (rightValue != null) {
                result[i] = rightValue / 1.0;
                continue;
            }

            Map<String, Double> rightVars = vars.get(right);
            if (rightVars == null) {
                result[i] = -1.0;
                continue;
            }
            Double leftValue = leftVars.get(left);
            if (leftValue != null) {
                result[i] = 1.0 / leftValue;
                continue;
            }

            Set<String> leftKeys = leftVars.keySet();
            Set<String> rightKeys = rightVars.keySet();
            leftKeys.retainAll(rightKeys);

            List<String> leftKeylist = new ArrayList<>();
            leftKeylist.addAll(leftKeys);
            if (leftKeylist.isEmpty()) {
                result[i] = -1.0;
                continue;
            }
            String sameKey = leftKeylist.get(0);

            Double sameLeft = leftVars.get(sameKey);
            Double sameRight = rightVars.get(sameKey);

            if (sameLeft == null | sameRight == null) {
                result[i] = -1.0;
                continue;
            }

            result[i] = sameLeft / sameRight;
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(
               Arrays.asList("a", "b"),
               Arrays.asList("b", "c")
        );
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
               Arrays.asList("a", "c"),
               Arrays.asList("b", "a"),
               Arrays.asList("a", "e"),
               Arrays.asList("a", "a"),
               Arrays.asList("x", "x")
        );
        double[] result = new P399().calcEquation(equations, values, queries);
        for (double value : result) {
            System.out.println(value);
        }

        // Wrong answer input
        // [["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]
        // [3.0,4.0,5.0,6.0]
        // [["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
        //
        // Expected: [360.0,0.00833,20.0,1.0,-1.0,-1.0]
    }
}
