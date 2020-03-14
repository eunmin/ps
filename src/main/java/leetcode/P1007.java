package leetcode;

public class P1007 {
    public static int minDominoRotations(int[] A, int[] B) {
        int result = -1;
        int n = A.length;
        Integer[] countA = { 0, 0, 0, 0, 0, 0 };
        Integer[] countB = { 0, 0, 0, 0, 0, 0 };

        for (int i = 0; i < n; i++) {
            countA[A[i]-1]++;
            countB[B[i]-1]++;
        }

        for (int i = 0; i < 6; i++) {
            if (countA[i] + countB[i] >= n) {
                System.out.println("pick: " + (i + 1));
                result = 0;
                for (int j = 0; j < n; j++) {
                    if (A[j] != i + 1 && B[j] != i + 1) return -1;
                    if (A[j] == B[j]) continue;
                    if (countA[i] > countB[i]) {
                        if (B[j] == i + 1) result++;
                    } else {
                        if (A[j] == i + 1) result++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minDominoRotations(new int[] {2,3,2,1,1,1,2,2}, new int [] {2,1,2,1,1,3,1,1}));
    }
}
