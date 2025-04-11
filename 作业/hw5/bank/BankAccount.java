package hw5.bank;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(double amount) {
        System.out.println(Thread.currentThread().getName() + " 尝试取款 " + amount + "...");
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " 账户余额充足 (" + balance + ")，准备取款...");
            try {
                Thread.sleep(100); // 模拟取款耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " 成功取出 " + amount + "，当前余额: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " 余额不足 (" + balance + ")，取款失败，需要: " + amount);
        }
    }
} 
