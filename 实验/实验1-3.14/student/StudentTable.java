package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentTable {
    
    // 学生类
    static class Student {
        private final int id;
        private final String name;
        private final int age;
        private final int grade;
        
        public Student(int id, String name, int age, int grade) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }
        
        public int getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        public int getGrade() {
            return grade;
        }
        
        @Override
        public String toString() {
            return String.format("%-5d%-15s%-5d%-5d", id, name, age, grade);
        }
    }
    
    // 打印表头
    private static void printHeader() {
        System.out.printf("%-5s%-15s%-5s%-5s%n", "ID", "Name", "Age", "Grade");
        System.out.println("---------------------------");
    }
    
    // 打印学生列表
    private static void printStudents(List<Student> students) {
        printHeader();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // 创建学生列表
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "ZhangSan", 28, 98));
        students.add(new Student(2, "LiSi", 21, 100));
        students.add(new Student(3, "KangKang", 27, 89));
        students.add(new Student(4, "LiMing", 19, 92));
        students.add(new Student(5, "WangGang", 22, 66));
        students.add(new Student(6, "ZhaoXin", 24, 85));
        students.add(new Student(7, "LiuWei", 20, 78));
        students.add(new Student(8, "BaiZhanTang", 16, 99));
        
        // 打印原始表格
        System.out.println("原始表格：");
        printStudents(students);
        
        // 按姓名排序
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("按姓名排序：");
        printStudents(students);
        
        // 按年龄倒序排序
        students.sort(Comparator.comparing(Student::getAge).reversed());
        System.out.println("按年龄倒序排序：");
        printStudents(students);
        
        // 按成绩排序
        students.sort(Comparator.comparing(Student::getGrade).reversed());
        System.out.println("按成绩排序：");
        printStudents(students);
    }
} 
