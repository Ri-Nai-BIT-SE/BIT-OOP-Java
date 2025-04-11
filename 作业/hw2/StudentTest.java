package hw2;

/**
 * 学生类测试程序
 */
public class StudentTest {
    public static void main(String[] args) {
        // 创建学生数据
        int[] studentNumbers = {1120231472, 1120235896, 1120239043, 1120234721, 1120237538, 1120232619, 1120238274, 1120236105};
        // 不使用中文，使用英文（会有编码问题）而且不好对齐
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "George", "Helen"};
        int[] ages = {20, 21, 19, 22, 23, 20, 21, 18};
        Gender[] genders = {
                Gender.FEMALE,
                Gender.MALE,
                Gender.MALE,
                Gender.MALE,
                Gender.FEMALE,
                Gender.MALE,
                Gender.FEMALE,
                Gender.MALE,
        };

        // 创建学生数组并输出信息
        Student[] students = new Student[names.length];
        for (int i = 0; i < names.length; i++) {
            students[i] = new Student(studentNumbers[i], names[i], ages[i], genders[i]);
        }
        
        // 使用foreach循环输出学生信息
        for (Student student : students) {
            student.printInfo();
        }

        // 输出学生总数
        System.out.println("Total number of students: " + Student.getStudentTotalNum());
    }
}
