import java.util.concurrent.*;

public class CyclicBarrierDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        CustomThreadCyclicBarrier t1 = new CustomThreadCyclicBarrier(cyclicBarrier);
        CustomThreadCyclicBarrier t2 = new CustomThreadCyclicBarrier(cyclicBarrier);

        executorService.submit(t1);
        executorService.submit(t2);

        System.out.println("Done");

    }
}


class CustomThreadCyclicBarrier extends Thread{

    CyclicBarrier cyclicBarrier;

    CustomThreadCyclicBarrier(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run(){

        try {
            System.out.println("Started Thread");
            cyclicBarrier.await();
            System.out.println("Completed Thread");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
