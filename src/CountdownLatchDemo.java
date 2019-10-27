import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(2);

        CustomThread ct1 = new CustomThread(cdl);
        CustomThread ct2 = new CustomThread(cdl);
        CustomThread ct3 = new CustomThread(cdl);

        ct1.start();
        ct2.start();

        cdl.await();

        //cdl.await(1000, TimeUnit.MICROSECONDS);
        System.out.println("Waiting for 0,1 to run then will start 2");

        ct3.start();
    }
}

class CustomThread extends Thread{

    CountDownLatch cdl;

    CustomThread(CountDownLatch cdl){
        this.cdl = cdl;
    }

    @Override
    public void run(){
        try {
            cdl.countDown();
            System.out.println("Started"+ Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
