package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortStudents {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "ZhangSan", 28, 98));
        students.add(new Student(2, "LiSi", 21, 100));
        students.add(new Student(3, "KangKang", 27, 89));
        students.add(new Student(4, "LiMing", 19, 92));
        students.add(new Student(5, "WangGang", 22, 66));
        students.add(new Student(6, "ZhaoXin", 24, 85));
        students.add(new Student(7, "LiuWei", 20, 78));
        students.add(new Student(8, "BaiZhanTang", 16, 99));

        // 按 Name 排序
        System.out.println("按 Name 排序:");
        students.sort(Comparator.comparing(Student::getName));
        students.forEach(System.out::println);

        // 按 Age 倒序排序
        System.out.println("\n按 Age 倒序排序:");
        students.sort(Comparator.comparing(Student::getAge).reversed());
        students.forEach(System.out::println);

        // 按 Grade 排序
        System.out.println("\n按 Grade 排序:");
        students.sort(Comparator.comparing(Student::getGrade));
        students.forEach(System.out::println);
    }
}
