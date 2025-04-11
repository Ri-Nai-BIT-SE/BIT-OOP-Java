package hw5.producerConsumer;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        System.out.println("--- 生产者消费者模型模拟开始 ---");
        Warehouse warehouse = new Warehouse();
        System.out.println("仓库初始化完成，容量: " + 5); // 假设容量为 5

        // 创建两个生产者线程
        Producer producer1 = new Producer(warehouse, "生产者A", 5);
        Producer producer2 = new Producer(warehouse, "生产者B", 5);
        System.out.println("生产者线程已创建: 生产者A, 生产者B");
        
        // 创建两个消费者线程
        Consumer consumer1 = new Consumer(warehouse, "消费者X", 5);
        Consumer consumer2 = new Consumer(warehouse, "消费者Y", 5);
        System.out.println("消费者线程已创建: 消费者X, 消费者Y");

        try {
            System.out.println("准备启动线程...");
            Thread.sleep(500); // 启动前稍作停顿

            System.out.println("启动 生产者A 线程...");
            producer1.start();
            Thread.sleep(100); // 错开启动时间

            System.out.println("启动 生产者B 线程...");
            producer2.start();
            Thread.sleep(100); // 错开启动时间

            System.out.println("启动 消费者X 线程...");
            consumer1.start();
            Thread.sleep(100); // 错开启动时间

            System.out.println("启动 消费者Y 线程...");
            consumer2.start();

            // 等待所有线程完成
            System.out.println("所有线程已启动，主线程等待它们完成...");
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
            
            System.out.println("所有生产和消费操作已完成!");
            System.out.println("--- 生产者消费者模型模拟结束 ---");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} 
