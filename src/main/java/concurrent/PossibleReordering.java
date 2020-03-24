package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PossibleReordering {
    int x = 0, y = 0;
    int a = 0, b = 0;

    public int[] reodering() throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier gate = new CyclicBarrier(3);

        Thread one = new Thread(() -> {
            try {
                gate.await();
                a = 1;
                x = b;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread other = new Thread(() -> {
            try {
                gate.await();
                b = 1;
                y = a;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        one.start();
        other.start();

        gate.await();

        one.join();
        other.join();

        return new int[] {x, y};
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int x;
        int y;
        for(;;) {
            int[] result = new PossibleReordering().reodering();
            x = result[0];
            y = result[1];
            if(x == y) {
                System.out.println(x + "," + y);
                break;
            }
        }
    }
}
