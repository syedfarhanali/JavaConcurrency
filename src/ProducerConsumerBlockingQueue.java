import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumerBlockingQueue {


    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

        Producer p = new Producer(blockingQueue);
        Consumer c = new Consumer(blockingQueue);

        p.start();
        c.start();

    }
}


class Producer extends Thread{

    BlockingQueue blockingQueue;
    final Integer POISON_PILL = -1;

    Producer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run(){
        generateNumbers();
    }

    private void generateNumbers() {
        try {
            for (int i = 0; i < 5; i++) {
                Integer val = ThreadLocalRandom.current().nextInt(100);
                System.out.println("Putting : "+ val);
                blockingQueue.put(val);
                Thread.sleep(1000);
            }
            System.out.println("Consumer please die!");
            blockingQueue.put(POISON_PILL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}

class Consumer extends Thread{

    BlockingQueue blockingQueue;
    final Integer POISON_PILL = -1;

    Consumer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run(){
        try {
            while (true) {
                Integer val = (Integer) blockingQueue.take();
                if(POISON_PILL == val){
                    System.out.println("Consumer : I am dying :( !!");
                    break;
                }
                System.out.println("Reading : "+ val);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
