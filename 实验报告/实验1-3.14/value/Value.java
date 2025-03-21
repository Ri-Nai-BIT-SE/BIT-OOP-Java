package value;

public class Value {
    private char x;
    private double y;
    
    // 构造方法
    public Value(char x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // 获取x的方法
    public char getX() {
        return x;
    }
    
    // 设置x的方法
    public void setX(char x) {
        this.x = x;
    }
    
    // 获取y的方法
    public double getY() {
        return y;
    }
    
    // 设置y的方法
    public void setY(double y) {
        this.y = y;
    }
    
    // 重写equals方法
    @Override
    public boolean equals(Object obj) {
        // 如果引用相同，则相等
        if (this == obj) {
            return true;
        }

        // 如果obj为null或者类型不同，则不相等
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // 转换为Value类型
        Value other = (Value) obj;

        // 比较成员变量是否相等
        return this.x == other.x && this.y == other.y;
    }
    
    // 重写toString方法，方便测试
    @Override
    public String toString() {
        return "value.Value{x=" + x + ", y=" + y + "}";
    }
    
    // 测试方法
    public static void main(String[] args) {
        Value v1 = new Value('a', 3.14);
        Value v2 = new Value('a', 3.14);
        Value v3 = new Value('b', 2.71);

        System.out.println("v1.equals(v1): " + v1.equals(v1)); // 应该输出true
        System.out.println("v1.equals(v2): " + v1.equals(v2)); // 应该输出true
        System.out.println("v1.equals(v3): " + v1.equals(v3)); // 应该输出false
    }
} 
