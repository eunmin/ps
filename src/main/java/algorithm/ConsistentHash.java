package algorithm;

public class ConsistentHash {

    public static Integer hash(Long key, Integer numBuckets) {
        Long b = 1L, j = 0L;
        while (j < numBuckets) {
            b = j;
            key = key * 2862933555777941757L + 1;
            j = new Double(((b + 1) * ((double)(1L << 31) / (double)(key >> 33) + 1))).longValue();
        }

        return b.intValue();
    }

    public static void main(String[] args) {
        System.out.println(hash(10L, 3));
        System.out.println(hash(50L, 3));
        System.out.println(hash(100L, 3));

        System.out.println(hash(10L, 4));
        System.out.println(hash(50L, 4));
        System.out.println(hash(100L, 4));
    }
}
