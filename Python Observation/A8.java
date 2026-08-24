class MyThread extends Thread {
    String message;
    int time;

    MyThread(String message, int time) {
        this.message = message;
        this.time = time;
    }

    public void run() {
        try {
            while (true) {
                System.out.println(message);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

public class A8 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Good Morning", 1000);
        MyThread t2 = new MyThread("Hello", 2000);
        MyThread t3 = new MyThread("Welcome", 3000);

        t1.start();
        t2.start();
        t3.start();
    }
}