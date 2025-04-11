### 例11-6 多线程银行取钱操作

#### 数据类：Account.java
```java
public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    // 同步方法保证线程安全 
    public synchronized void withdraw(double amount) {
        if (amount > balance) {
            System.out.println(Thread.currentThread().getName() + " 余额不足，当前余额：" + balance);
        } else {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " 取款成功，剩余余额：" + balance);
        }
    }
}
```

#### 测试类：BankTest.java
```java
public class BankTest {
    public static void main(String[] args) {
        Account account = new Account(1000);

        Runnable withdrawTask = () -> {
            for (int i = 0; i < 5; i++) {
                account.withdraw(200);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        };

        Thread user1 = new Thread(withdrawTask, "用户1");
        Thread user2 = new Thread(withdrawTask, "用户2");
        user1.start();
        user2.start();
    }
}
```

---

### 11.6.2 生产者-消费者模型

#### 数据类：Buffer.java
```java
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final int MAX_SIZE = 5;
    private Queue<Integer> queue = new LinkedList<>();

    // 生产方法 
    public synchronized void produce(int data) throws InterruptedException {
        while (queue.size() == MAX_SIZE) { // 使用while防止虚假唤醒 
            wait();
        }
        queue.add(data);
        System.out.println("生产者生产：" + data + "，当前库存：" + queue.size());
        notifyAll(); // 通知消费者 
    }

    // 消费方法
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int data = queue.poll();
        System.out.println("消费者消费：" + data + "，剩余库存：" + queue.size());
        notifyAll(); // 通知生产者 
        return data;
    }
}
```

#### 生产者线程：Producer.java
```java
public class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.produce(i);
                Thread.sleep((int)(Math.random() * 500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

#### 消费者线程：Consumer.java
```java
public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.consume();
                Thread.sleep((int)(Math.random() * 800));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

#### 测试类：PCModelTest.java
```java
public class PCModelTest {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        
        Thread producer = new Thread(new Producer(buffer), "生产者");
        Thread consumer = new Thread(new Consumer(buffer), "消费者");
        
        producer.start();
        consumer.start();
    }
}
```

---

### 关键点说明：
1. **线程同步**：使用`synchronized`关键字保证对共享资源的互斥访问
2. **线程通信**：通过`wait()`/`notifyAll()`实现生产者与消费者的协作
3. **虚假唤醒处理**：使用`while`循环而非`if`判断条件
4. **线程安全队列**：通过`LinkedList`实现缓冲区，容量限制为5
