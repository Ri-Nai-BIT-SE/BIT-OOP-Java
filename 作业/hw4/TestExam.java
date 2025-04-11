package hw4;

import java.util.Scanner;

public class TestExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入学生姓名
        System.out.print("请输入学生姓名：");
        String studentName = scanner.nextLine();

        // 创建学生对象
        Student student = new Student(studentName);

        // 创建考试对象
        Exam exam = new Exam(student);

        // 开始考试
        exam.exam();

        scanner.close();
    }
}