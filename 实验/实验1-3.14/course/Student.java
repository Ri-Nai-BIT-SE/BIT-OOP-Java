package course;

import java.util.ArrayList;
import java.util.List;

// 学生类
class Student {
    private String name;        // 学生姓名
    private int grade;          // 年级
    private List<Course> courses;  // 所选课程列表

    // 构造方法
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
        this.courses = new ArrayList<>();
    }

    // 获取姓名
    public String getName() {
        return name;
    }

    // 设置姓名
    public void setName(String name) {
        this.name = name;
    }

    // 获取年级
    public int getGrade() {
        return grade;
    }

    // 设置年级
    public void setGrade(int grade) {
        this.grade = grade;
    }

    // 获取课程列表
    public List<Course> getCourses() {
        return courses;
    }

    // 设置课程列表
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // 添加课程
    public void addCourse(Course course) {
        courses.add(course);
    }

    // 计算总学分
    public int getHours() {
        int totalCredit = 0;
        for (Course course : courses) {
            totalCredit += course.getCredit();
        }
        return totalCredit;
    }

    // 显示学生信息
    public void showMessage() {
        System.out.println("学生姓名：" + name);
        System.out.println("年级：" + grade);
        System.out.println("所选课程：");

        for (Course course : courses) {
            System.out.println("  - " + course);
        }

        System.out.println("总学分：" + getHours());
        System.out.println();
    }
}
