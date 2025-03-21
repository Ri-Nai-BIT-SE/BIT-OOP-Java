package course;

// 课程类
class Course {
    private String id;      // 课程编号
    private String name;    // 课程名称
    private int credit;     // 学分

    // 构造方法
    public Course(String id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    // 获取课程编号
    public String getId() {
        return id;
    }

    // 设置课程编号
    public void setId(String id) {
        this.id = id;
    }

    // 获取课程名称
    public String getName() {
        return name;
    }

    // 设置课程名称
    public void setName(String name) {
        this.name = name;
    }

    // 获取学分
    public int getCredit() {
        return credit;
    }

    // 设置学分
    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "课程编号：" + id + "，课程名称：" + name + "，学分：" + credit;
    }
}
