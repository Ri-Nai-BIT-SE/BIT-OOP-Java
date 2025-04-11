package hw5.producerConsumer;

public class Warehouse {
    private static final int CAPACITY = 5;
    private int productCount = 0;

    public synchronized void produce() {
        while (productCount >= CAPACITY) {
            try {
                System.out.println(Thread.currentThread().getName() + " 发现仓库已满 (" + productCount + "/" + CAPACITY + ")，进入等待...");
                wait();
                System.out.println(Thread.currentThread().getName() + " 被唤醒，检查仓库状态...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productCount++;
        System.out.println(Thread.currentThread().getName() + " 生产了一个产品，当前库存: " + productCount + "/" + CAPACITY);
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " 发出通知：仓库有新产品了。");
    }

    public synchronized void consume() {
        while (productCount <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 发现仓库为空 (" + productCount + "/" + CAPACITY + ")，进入等待...");
                wait();
                System.out.println(Thread.currentThread().getName() + " 被唤醒，检查仓库状态...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productCount--;
        System.out.println(Thread.currentThread().getName() + " 消费了一个产品，当前库存: " + productCount + "/" + CAPACITY);
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " 发出通知：仓库有空位了。");
    }
} 
