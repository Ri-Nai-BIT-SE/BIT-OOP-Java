明白了！你要的是两份完整的 Java 项目代码，分别对应：

- 【例11-6】银行取钱操作（多线程+同步）
- 【11.6.2】生产者消费者模型（多线程+线程通信）

每个项目包括 **数据类（业务逻辑）** 和 **测试类（main函数测试）**。

我来直接写好给你，保证符合你们《面向对象程序设计》课件的内容要求！

---

## 【例11-6】多线程环境下银行取钱操作

**Bank.java**（数据类）：
```java
public class Bank {
    private int balance = 1000; // 初始余额

    // 同步取钱方法
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " 正在取钱：" + amount + "元");
            try {
                Thread.sleep(100); // 模拟取钱过程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " 取钱成功，余额剩余：" + balance + "元");
        } else {
            System.out.println(Thread.currentThread().getName() + " 取钱失败，余额不足！");
        }
    }
}
```

**Customer.java**（取钱线程类）：
```java
public class Customer extends Thread {
    private Bank bank;
    private int amount;

    public Customer(Bank bank, int amount, String name) {
        super(name);
        this.bank = bank;
        this.amount = amount;
    }

    @Override
    public void run() {
        bank.withdraw(amount);
    }
}
```

**BankTest.java**（测试类）：
```java
public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // 创建多个客户线程
        Customer c1 = new Customer(bank, 800, "张三");
        Customer c2 = new Customer(bank, 500, "李四");

        c1.start();
        c2.start();
    }
}
```

---
## 【11.6.2】生产者消费者模型

**Warehouse.java**（缓冲区/仓库类）：
```java
public class Warehouse {
    private int product = 0; // 当前仓库产品数量
    private final int capacity = 5; // 仓库容量最大值

    // 生产产品
    public synchronized void produce() {
        while (product >= capacity) {
            try {
                System.out.println("仓库已满，生产者等待...");
                wait(); // 仓库满了，等待消费者消费
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        System.out.println(Thread.currentThread().getName() + " 生产了一个产品，当前库存：" + product);
        notifyAll(); // 通知消费者可以消费
    }

    // 消费产品
    public synchronized void consume() {
        while (product <= 0) {
            try {
                System.out.println("仓库为空，消费者等待...");
                wait(); // 仓库空了，等待生产者生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        System.out.println(Thread.currentThread().getName() + " 消费了一个产品，当前库存：" + product);
        notifyAll(); // 通知生产者可以继续生产
    }
}
```

**Producer.java**（生产者线程类）：
```java
public class Producer extends Thread {
    private Warehouse warehouse;

    public Producer(Warehouse warehouse, String name) {
        super(name);
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // 每个生产者生产10次
            warehouse.produce();
            try {
                Thread.sleep(100); // 模拟生产时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

**Consumer.java**（消费者线程类）：
```java
public class Consumer extends Thread {
    private Warehouse warehouse;

    public Consumer(Warehouse warehouse, String name) {
        super(name);
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // 每个消费者消费10次
            warehouse.consume();
            try {
                Thread.sleep(150); // 模拟消费时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

**ProducerConsumerTest.java**（测试类）：
```java
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Producer producer1 = new Producer(warehouse, "生产者1");
        Producer producer2 = new Producer(warehouse, "生产者2");
        Consumer consumer1 = new Consumer(warehouse, "消费者1");
        Consumer consumer2 = new Consumer(warehouse, "消费者2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
```

---

✅以上两套完整代码都符合你们讲的内容：  
- synchronized 关键字
- wait()、notifyAll()
- 多线程创建、生命周期管理

---
要不要我顺便帮你也整理一下**这两个例子的UML类图**？（有时候老师可能还要让画的）  
要的话告诉我，我可以一起画好～
