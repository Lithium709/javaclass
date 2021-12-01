package syntax;


class Calculator extends Thread {
    public static long sum;
    @Override
    public synchronized void run() {

        for(long i=0;i<1_000_000;i++){
            sum+=i;
        }
        notify();

    }
}



public class Threads {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.start();
        synchronized (calculator){
            try {
                calculator.wait();
            }
            catch (InterruptedException e){

            }
        }
        System.out.println(Calculator.sum);

    }
}
