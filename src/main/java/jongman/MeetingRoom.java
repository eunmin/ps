package jongman;

import java.util.*;

public class MeetingRoom {

    public static class Pair {
        int first;
        int second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    public boolean disjoint(Pair a, Pair b) {
        return a.second <= b.first || b.second <= a.first;
    }
    public List<List<Integer>> makeGraph(List<Pair> meetings) {
        List<List<Integer>> adj = new ArrayList<>();
        int vars = meetings.size();
        for (int i = 0; i <vars*2; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i <vars; i += 2) {
            int j = i + 1;
            adj.get(i * 2 + 1).add(j * 2);
            adj.get(j * 2 + 1).add(i * 2);
        }
        for(int i = 0; i < vars; ++i) {
            for (int j = 0; j < i; ++j) {
                if(!disjoint(meetings.get(i), meetings.get(j))) {
                    adj.get(i * 2).add(j * 2 + 1);
                    adj.get(j * 2).add(i * 2 + 1);
                }
            }
        }
        return adj;
    }

    List<Integer> sccId = new ArrayList<>();
    List<Integer> discovered = new ArrayList<>();
    int sccCounter = 0;
    int vertextCounter = 0;
    Stack<Integer> st = new Stack<>();

    public int scc(int here, List<List<Integer>> adj) {
        int ret =  vertextCounter;
        discovered.set(here, ret);
        vertextCounter++;
        st.push(here);
        for (int i = 0; i < adj.get(here).size(); ++i) {
            int there = adj.get(here).get(i);
            if (discovered.get(there) == -1) {
                ret = Math.min(ret, scc(there, adj));
            } else if(sccId.get(there) == -1) {
                ret = Math.min(ret, discovered.get(there));
            }
        }
        if (ret == discovered.get(here)) {
            while(true) {
                int t = st.lastElement();
                st.pop();
                sccId.set(t, sccCounter);
                if (t == here) break;
            }
            ++sccCounter;
        }
        return ret;
    }

    public List<Integer> tarjanSCC(List<List<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            sccId.add(-1);
            discovered.add(-1);
        }
        for (int i = 0; i < adj.size(); i++) {
            if (discovered.get(i) == -1) {
                scc(i, adj);
            }
        }
        return sccId;
    }

    public List<Boolean> solve2SAT(List<List<Integer>> adj) {
        int n = adj.size() / 2;
        List<Integer> label = tarjanSCC(adj);
        for (int i = 0; i < 2 * n; i += 2) {
            if (label.get(i).equals(label.get(i + 1))) return new ArrayList<>();
        }

        List<Boolean> value = new ArrayList<>();
        for (int i = 0; i< 2 * n; i++) {
            value.add(null);
        }
        List<Pair> order = new ArrayList<>();
        for (int i = 0; i < 2 * n; i++) {
            order.add(new Pair(-1 * label.get(i), i));
        }
        // Collections.sort();
        for (int i = 0; i < 2 * n; ++i) {
            int vertex = order.get(i).second;
            int variable = vertex / 2;
            boolean isTrue = vertex % 2 == 0;
            if (value.get(variable) != null) continue;
            value.set(variable, !isTrue);
        }
        return value;
    }
    public static void main(String[] args) {
        List<Pair> meetings = new ArrayList<>();
        meetings.add(new Pair(2, 5));
        meetings.add(new Pair(6, 9));
        meetings.add(new Pair(1, 3));
        meetings.add(new Pair(8, 10));
        meetings.add(new Pair(4, 7));
        meetings.add(new Pair(11, 12));
        List<List<Integer>> adj = new MeetingRoom().makeGraph(meetings);
        System.out.println(new MeetingRoom().solve2SAT(adj));
    }
}
