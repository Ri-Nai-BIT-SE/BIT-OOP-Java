package hw5.bank;

public class BankWithdrawTest {
    public static void main(String[] args) {
        System.out.println("--- 银行取款模拟开始 ---");
        BankAccount account = new BankAccount(1000);
        System.out.println("账户初始化完成，初始余额: " + 1000);

        // 创建取款任务
        Runnable withdrawTask = () -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(300);
                try {
                    Thread.sleep(100); // 每次取款后稍微停顿
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println("取款任务已创建。");

        // 创建多个线程模拟多人同时取款
        Thread t1 = new Thread(withdrawTask, "用户A");
        Thread t2 = new Thread(withdrawTask, "用户B");
        Thread t3 = new Thread(withdrawTask, "用户C");
        System.out.println("用户线程已创建: 用户A, 用户B, 用户C");

        try {
            System.out.println("准备启动线程...");
            Thread.sleep(500); // 启动前稍作停顿

            System.out.println("启动 用户A 线程...");
            t1.start();
            Thread.sleep(100); // 错开启动时间

            System.out.println("启动 用户B 线程...");
            t2.start();
            Thread.sleep(100); // 错开启动时间

            System.out.println("启动 用户C 线程...");
            t3.start();

            // 等待所有线程完成 (可选，如果需要等待结束)
            // t1.join();
            // t2.join();
            // t3.join();
            // System.out.println("--- 所有用户尝试取款完毕 ---");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- 主线程继续执行（或结束） ---");
    }
} 
