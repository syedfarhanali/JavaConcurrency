import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(1);

        MyThread m1 = new MyThread(semaphore);
        MyThread m2 = new MyThread(semaphore);

        m1.start();
        m2.start();

        m1.join();
        m2.join();
    }


}

class MyThread extends Thread{

    private Semaphore semaphore;

    MyThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run(){

        System.out.println("Waiting for Permit"+ Thread.currentThread().getName());
        try {
            semaphore.acquire(); // RUN by commenting this to see the impact!!
            System.out.println("Got Permit"+ Thread.currentThread().getName());
            for(int i=0;i<5;i++){
                Shared.counter++;
                Thread.sleep(10);
                String output = Thread.currentThread().getName() + " incremented count:" +  + Shared.counter;
                System.out.println( output );
            }

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println("Released Permit"+ Thread.currentThread().getName());
        semaphore.release();

    }

}

class Shared{

    static int counter = 0;
}
