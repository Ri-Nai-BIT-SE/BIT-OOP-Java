package course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 测试类
public class CourseSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> allCourses = new ArrayList<>();
        // 创建两个HashMap用于快速查找课程
        Map<String, Course> courseIdMap = new HashMap<>();  // 课程编号 -> 课程对象
        Map<String, Course> courseNameMap = new HashMap<>(); // 课程名称 -> 课程对象
        List<Student> students = new ArrayList<>();
        
        // 输入课程信息
        System.out.println("请输入课程数量：");
        int courseCount = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符
        
        for (int i = 0; i < courseCount; i++) {
            System.out.println("请输入第" + (i + 1) + "门课程的编号：");
            String courseId = scanner.nextLine();
            
            System.out.println("请输入第" + (i + 1) + "门课程的名称：");
            String courseName = scanner.nextLine();
            
            System.out.println("请输入第" + (i + 1) + "门课程的学分：");
            int credit = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符
            
            Course course = new Course(courseId, courseName, credit);
            allCourses.add(course);
            
            // 将课程添加到两个HashMap中
            courseIdMap.put(courseId, course);
            courseNameMap.put(courseName, course);
        }
        
        // 输入学生信息
        System.out.println("请输入学生数量：");
        int studentCount = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符
        
        for (int i = 0; i < studentCount; i++) {
            System.out.println("请输入第" + (i + 1) + "个学生的姓名：");
            String studentName = scanner.nextLine();
            
            System.out.println("请输入第" + (i + 1) + "个学生的年级：");
            int grade = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符
            
            Student student = new Student(studentName, grade);
            
            System.out.println("请输入该学生选择的课程数量：");
            int selectedCourseCount = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符
            
            System.out.println("可选课程列表：");
            for (Course course : allCourses) {
                System.out.println(course);
            }
            
            System.out.println("请选择输入方式：1-按课程编号选择，2-按课程名称选择");
            int choiceMethod = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符
            
            for (int j = 0; j < selectedCourseCount; j++) {
                if (choiceMethod == 1) {
                    System.out.println("请输入第" + (j + 1) + "门所选课程的编号：");
                    String courseId = scanner.nextLine();
                    
                    // 使用HashMap O(1)时间复杂度查找课程
                    Course course = courseIdMap.get(courseId);
                    if (course != null) {
                        student.addCourse(course);
                    } else {
                        System.out.println("未找到编号为 " + courseId + " 的课程，请重新输入：");
                        j--; // 重新输入
                    }
                } else if (choiceMethod == 2) {
                    System.out.println("请输入第" + (j + 1) + "门所选课程的名称：");
                    String courseName = scanner.nextLine();
                    
                    // 使用HashMap O(1)时间复杂度查找课程
                    Course course = courseNameMap.get(courseName);
                    if (course != null) {
                        student.addCourse(course);
                    } else {
                        System.out.println("未找到名称为 " + courseName + " 的课程，请重新输入：");
                        j--; // 重新输入
                    }
                } else {
                    System.out.println("无效的选择方式，请重新选择：");
                    j--; // 重新输入
                }
            }
            
            students.add(student);
        }
        
        // 显示所有学生的选课信息
        System.out.println("\n所有学生的选课信息：");
        for (Student student : students) {
            student.showMessage();
        }
        
        scanner.close();
    }
} 
