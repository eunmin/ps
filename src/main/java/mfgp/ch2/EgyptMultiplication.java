package mfgp.ch2;

public class EgyptMultiplication {
    public static int multiply0(int n, int a) {
        if (n == 1) return a;
        return multiply0(n - 1, a) + a;
    }

    public static boolean odd(int n) { return (n & 0x1) == 1; }

    public static int half(int n) { return n >> 1; }

    public static int multiply1(int n, int a) {
        if (n == 1) return a;
        int result = multiply1(half(n), a + a);
        if (odd(n)) result = result + a;
        return result;
    }

    public static int multAcc0(int r, int n, int a) {
        if (n == 1) return r + a;
        if (odd(n)) {
            return multAcc0(r + a, half(n), a + a);
        } else {
            return multAcc0(r, half(n), a + a);
        }
    }

    public static int multAcc1(int r, int n, int a) {
        if (n == 1) return r + a;
        if (odd(n)) r = r + a;
        return multAcc1(r, half(n), a + a);
    }

    public static int multAcc2(int r, int n ,int a) {
        if (odd(n)) {
            r = r + a;
            if (n == 1) return r;
        }
        return multAcc2(r, half(n), a + a);
    }

    public static int multAcc3(int r, int n, int a) {
        if (odd(n)) {
            r = r + a;
            if (n == 1) return r;
        }
        n = half(n);
        a = a + a;
        return multAcc3(r, n, a);
    }

    public static int multAcc4(int r, int n, int a) {
        while(true) {
            if (odd(n)) {
                r = r + a;
                if (n == 1) return r;
            }
            n = half(n);
            a = a + a;
        }
    }

    public static int multiply2(int n, int a) {
        if (n == 1) return a;
        return multAcc4(a, n - 1, a);
    }

    public static int multiply3(int n, int a) {
        while (!odd(n)) {
            a = a + a;
            n = half(n);
        }
        if (n == 1) return a;
        return multAcc4(a, n -1, a);
    }

    public static int multiply4(int n, int a) {
        while (!odd(n)) {
            a = a + a;
            n = half(n);
        }
        if (n == 1) return a;
        return multAcc4(a, half(n - 1), a + a);
    }

    public static void main(String[] args) {
        System.out.println(multiply0(41, 59));
        System.out.println(multiply1(41, 59));
        System.out.println(multAcc0(0, 41, 59));
        System.out.println(multAcc1(0, 41, 59));
        System.out.println(multAcc2(0, 41, 59));
        System.out.println(multAcc3(0, 41, 59));
        System.out.println(multAcc4(0, 41, 59));
        System.out.println(multiply2(41, 59));
        System.out.println(multiply3(41, 59));
        System.out.println(multiply4(41, 59));
    }
}
