package hw5.producerConsumer;

public class Consumer extends Thread {
    private final Warehouse warehouse;
    private final int consumptionCount;

    public Consumer(Warehouse warehouse, String name, int consumptionCount) {
        super(name);
        this.warehouse = warehouse;
        this.consumptionCount = consumptionCount;
    }

    @Override
    public void run() {
        System.out.println(getName() + " 开始消费 " + consumptionCount + " 个产品...");
        for (int i = 0; i < consumptionCount; i++) {
            System.out.println(getName() + " 准备消费第 " + (i + 1) + " 个产品...");
            warehouse.consume();
            try {
                Thread.sleep((int)(Math.random() * 800));  // 随机消费时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " 完成消费任务。");
    }
} 
