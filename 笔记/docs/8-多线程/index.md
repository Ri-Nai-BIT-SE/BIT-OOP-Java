# 🧠 Java 多线程编程笔记（整理版）

## 一、进程与线程的基本概念

| 概念 | 描述 |
|------|------|
| **进程（Process）** | 是程序的一次执行过程，是操作系统资源分配的基本单位 |
| **线程（Thread）** | 是进程中最小的执行单元，是 CPU 调度的基本单位 |

### ✅ 区别：
| 特性 | 进程 | 线程 |
|------|------|------|
| 内存空间 | 独立内存 | 共享所属进程的内存 |
| 创建销毁开销 | 较大 | 较小 |
| 通信方式 | 需要特殊机制（如管道、消息队列） | 直接共享变量即可 |
| 切换效率 | 低 | 高 |

📌 **一个 Java 应用就是一个 JVM 实例，对应一个操作系统进程；这个进程中可以有多个线程。**


## 二、线程的创建与启动

Java 提供了多种创建线程的方式：

### ✅ 方式一：继承 `Thread` 类

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("线程运行中...");
    }
}

MyThread thread = new MyThread();
thread.start(); // 启动线程
```

### ✅ 方式二：实现 `Runnable` 接口（推荐）

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("线程运行中...");
    }
}

Thread thread = new Thread(new MyRunnable());
thread.start();
```

### ✅ 方式三：使用 Lambda 表达式（Java 8+）

```java
new Thread(() -> {
    System.out.println("Lambda 线程");
}).start();
```


## 三、线程的生命周期

Java 中线程的状态定义在 `Thread.State` 枚举中，共有 6 种状态：

| 状态 | 描述 |
|------|------|
| **NEW** | 线程对象已创建，但尚未调用 `start()` |
| **RUNNABLE** | 线程正在运行或等待 CPU 时间片 |
| **BLOCKED** | 线程等待获取一个锁 |
| **WAITING** | 线程无限期等待，直到被其他线程唤醒 |
| **TIMED_WAITING** | 线程在指定时间内等待 |
| **TERMINATED** | 线程执行完毕或因异常退出 |

### 🔄 生命周期图解：

```
NEW → RUNNABLE ↔ BLOCKED
           ↘
             WAITING → TERMINATED
           ↗
       TIMED_WAITING
```


## 四、线程同步机制

多线程环境下，为防止数据不一致问题，Java 提供了多种同步机制。

### ✅ 1. `synchronized` 关键字

#### （1）同步方法（实例方法）

```java
public synchronized void increment() {
    count++;
}
```

- 锁的是当前对象（`this`）

#### （2）同步静态方法

```java
public static synchronized void log() {
    // ...
}
```

- 锁的是类对象（`类.class`）

#### （3）同步代码块（推荐）

```java
Object lock = new Object();

synchronized (lock) {
    // 同步代码
}
```

- 更灵活，推荐用于跨线程通信场景


### ✅ 2. `ReentrantLock`（显式锁）

```java
ReentrantLock lock = new ReentrantLock();

lock.lock();
try {
    // 访问共享资源
} finally {
    lock.unlock();
}
```

- 支持尝试加锁、超时、公平锁等高级特性


## 五、线程间通信机制

线程之间可以通过以下方式进行协作：

### ✅ 1. `wait()` / `notify()` / `notifyAll()`

必须配合 `synchronized` 使用，用于线程等待和唤醒。

```java
synchronized (obj) {
    obj.wait();  // 当前线程释放锁并进入等待
}

synchronized (obj) {
    obj.notify(); // 唤醒一个等待中的线程
}
```

> ⚠️ 注意：`wait()` 必须放在 `while` 循环中，避免虚假唤醒。


### ✅ 2. `Condition`（配合 `ReentrantLock` 使用）

```java
ReentrantLock lock = new ReentrantLock();
Condition condition = lock.newCondition();

lock.lock();
try {
    condition.await(); // 等待
} finally {
    lock.unlock();
}

lock.lock();
try {
    condition.signal(); // 唤醒
} finally {
    lock.unlock();
}
```

- 更细粒度控制，支持多个条件变量


## 六、生产者-消费者模型示例

```java
class Buffer {
    private String data;
    private boolean isEmpty = true;

    public void put(String data) throws InterruptedException {
        synchronized (this) {
            while (!isEmpty) {
                wait(); // 缓冲区满，等待消费者消费
            }
            this.data = data;
            isEmpty = false;
            notifyAll(); // 唤醒消费者
        }
    }

    public String get() throws InterruptedException {
        synchronized (this) {
            while (isEmpty) {
                wait(); // 缓冲区空，等待生产者生产
            }
            isEmpty = true;
            notifyAll(); // 唤醒生产者
            return data;
        }
    }
}
```


## 七、并发工具类（`java.util.concurrent`）

Java 5 引入的并发包提供了很多实用工具类：

| 类名 | 功能 |
|------|------|
| `ExecutorService` | 线程池，统一管理线程资源 |
| `Callable & Future` | 支持有返回值的异步任务 |
| `CountDownLatch` | 控制多个线程的启动或结束顺序 |
| `CyclicBarrier` | 多个线程相互等待到达某个点后再继续执行 |
| `Semaphore` | 控制同时访问的线程数量 |
| `ConcurrentHashMap` | 线程安全的 Map 实现 |
| `BlockingQueue` | 线程安全的队列，常用于生产者-消费者模型 |

### 示例：线程池

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(() -> System.out.println("任务执行"));
executor.shutdown();
```


## 八、线程安全集合类

| 类型 | 示例 | 描述 |
|------|------|------|
| **同步集合** | `Collections.synchronizedList(list)` | 手动包装普通集合 |
| **并发集合** | `ConcurrentHashMap`, `CopyOnWriteArrayList` | 线程安全且性能更优 |
| **阻塞队列** | `ArrayBlockingQueue`, `LinkedBlockingQueue` | 适用于生产者-消费者模型 |


## 九、原子操作类（CAS）

Java 提供了一系列基于 CAS 的原子类，位于 `java.util.concurrent.atomic` 包下：

| 类名 | 描述 |
|------|------|
| `AtomicInteger` | 支持原子自增 |
| `AtomicBoolean` | 支持原子布尔值操作 |
| `AtomicReference<T>` | 支持引用类型原子操作 |

```java
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet(); // 原子自增
```


## 十、Fork/Join 框架（Java 7+）

适用于可拆分的大任务，采用“工作窃取”算法提高并发效率。

```java
class SumTask extends RecursiveTask<Integer> {
    int[] array;
    int start, end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 10) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
```


## 十一、CompletableFuture（Java 8+）

简化异步编程，支持链式调用、组合任务、异常处理。

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World");

System.out.println(future.get()); // 输出 Hello World
```


## 十二、虚拟线程（Virtual Threads）—— Java 21+

Java 21 引入轻量级线程，极大提升并发能力。

```java
Thread.ofVirtual().start(() -> {
    System.out.println("Running in virtual thread");
});
```

- 成千上万的虚拟线程可以运行在少量 OS 线程上
- 不需要手动管理线程池大小


## 十三、线程池详解

线程池通过复用线程，减少频繁创建和销毁带来的开销。

### 常见线程池：

| 类型 | 描述 |
|------|------|
| `newFixedThreadPool` | 固定大小的线程池 |
| `newCachedThreadPool` | 可缓存的线程池（按需创建） |
| `newSingleThreadExecutor` | 单线程串行执行任务 |
| `newScheduledThreadPool` | 支持定时任务的线程池 |

### 示例：

```java
ExecutorService pool = Executors.newFixedThreadPool(4);
pool.execute(() -> System.out.println("执行任务"));
pool.shutdown();
```


## 十四、线程安全最佳实践

| 最佳实践 | 说明 |
|----------|------|
| ✅ 使用线程池管理线程 | 避免频繁创建销毁线程 |
| ✅ 使用 `synchronized` 或 `ReentrantLock` | 控制对共享资源的访问 |
| ✅ 使用 `volatile` | 保证变量可见性和禁止指令重排 |
| ✅ 使用并发集合类 | 如 `ConcurrentHashMap`, `CopyOnWriteArrayList` |
| ✅ 使用 `wait/notify` 或 `Condition` | 实现线程间协作 |
| ✅ 使用 `CompletableFuture` | 替代回调地狱，简化异步编程 |
| ❌ 不要吞掉异常 | 显式处理或记录异常信息 |
| ❌ 不要滥用 `synchronized` | 会降低并发性能 |


## 十五、总结一句话：

> **Java 多线程是构建高并发程序的核心基础。理解线程的生命周期、掌握同步机制和线程通信方式，并合理使用线程池、并发工具类，是写出高效、安全、可维护多线程程序的关键。**

