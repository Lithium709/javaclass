package syntax;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;

public class WaitNotify {

    static int id =0;
    static Object lock = new Object();
    static LinkedList<Integer> queue = new LinkedList();
    static Random random = new Random();

    static void produce() throws InterruptedException{
        while (true) {
            synchronized (lock) {
                if (queue.size() > 10) {
                    lock.wait();
                }
                Thread.sleep(1000);
                queue.add(id++);
                System.out.println("Produced item ");
                lock.notifyAll();
            }
        }
    }

    static void consume() throws InterruptedException{
        while (true) {
            synchronized (lock) {
                if (queue.size() == 10) {
                    lock.wait();
                }
                int item = queue.remove(id);
                Thread.sleep(random.nextInt(1000));
                System.out.println("Consumed item N " + item);
                lock.notifyAll();
            }
        }
    }

    static class Consumer extends Thread{
        @Override
        public void run(){
               try{
                   consume();
               } catch (InterruptedException e){}
            }
    }

    static class Producer extends Thread{
        @Override
        public void run() {
            try {
                produce();
            }
            catch (InterruptedException e){
            }
        }
    }

    public static void main(String[] args) {

        new Consumer().start();
        new Consumer().start();
        new Producer().start();

        //Executors.
    }
}
