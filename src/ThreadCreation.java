public class ThreadCreation {
    public static void main(String[] args) {

        createThread();

    }

    private static void createThread(){

        Thread t = new Thread(()->{
            System.out.println("Lambda1 thread Name:"+ Thread.currentThread().getName());
        });
        t.start();

        Runnable r = () -> {
            System.out.println("Lambda2 thread Name:"+ Thread.currentThread().getName());
        };
        Thread t2 = new Thread(r);
        t2.start();

        CustomThread1 t3  = new CustomThread1();
        t3.start();

        CustomThread2 r1  = new CustomThread2();
        Thread t4 = new Thread(r1);
        t4.start();

    }
}


class CustomThread1 extends Thread{

    @Override
    public void run(){
        System.out.println("Name:"+ Thread.currentThread().getName());
    }
}

class CustomThread2 implements Runnable{

    @Override
    public void run(){
        System.out.println("Name:"+ Thread.currentThread().getName());
    }
}