package hw4;

public class Student {
    private String name;

    // 默认构造函数
    public Student() {
    }

    // 带参数的构造函数
    public Student(String name) {
        this.name = name;
    }

    // 获取学生姓名方法
    public String getName() {
        return name;
    }

    // Setter 方法
    public void setName(String name) {
        this.name = name;
    }
}
