package hw5.producerConsumer;

public class Producer extends Thread {
    private final Warehouse warehouse;
    private final int productionCount;

    public Producer(Warehouse warehouse, String name, int productionCount) {
        super(name);
        this.warehouse = warehouse;
        this.productionCount = productionCount;
    }

    @Override
    public void run() {
        System.out.println(getName() + " 开始生产 " + productionCount + " 个产品...");
        for (int i = 0; i < productionCount; i++) {
            System.out.println(getName() + " 准备生产第 " + (i + 1) + " 个产品...");
            warehouse.produce();
            try {
                Thread.sleep((int)(Math.random() * 500));  // 随机生产时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " 完成生产任务。");
    }
} 
