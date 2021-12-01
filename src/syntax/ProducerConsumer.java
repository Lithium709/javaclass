package syntax;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    private static int id = 0;
    private static Queue<Integer> queue = new LinkedList<>();

    public static void produce() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                while (queue.size() > 2) {
                    queue.wait();
                }
                int el = id++;
                queue.add(el);
                Thread.sleep(1000);
                System.out.println("Produced " + el);
                queue.notifyAll();
            }
        }
    }

    public static void consume() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                while (queue.size() == 0) {
                    queue.wait();
                }
                int el = queue.remove();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+ " consumed " + el);
                queue.notify();
            }
        }
    }

    public static void main(String ...args){


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                }
                catch (InterruptedException e){
                    }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume();
                }
                catch (InterruptedException e){
                }
            }
        }, "Consumer 1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume();
                }
                catch (InterruptedException e){
                }
            }
        }, "Consumer 2").start();

    }

}
