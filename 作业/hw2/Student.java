package hw2;

/**
 * 学生类，用于表示和管理学生信息
 */
public class Student {


    // 实例变量
    private int studentNumber;    // 学号
    private String name;          // 姓名
    private int age;              // 年龄
    private Gender gender;        // 性别

    // 静态变量
    private static int studentTotalNum = 0;  // 学生总数计数器

    /**
     * 默认构造函数
     */
    public Student() {
        incrementStudentCount();
    }

    /**
     * 带参数的构造函数
     *
     * @param studentNumber 学号
     * @param name          姓名
     * @param age           年龄
     * @param gender        性别
     */
    public Student(int studentNumber, String name, int age, Gender gender) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
        incrementStudentCount();
    }

    /**
     * 设置学号
     */
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * 获取学号
     */
    public int getStudentNumber() {
        return studentNumber;
    }

    /**
     * 设置姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置年龄
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取年龄
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置性别
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 获取性别
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 获取性别的文字描述
     *
     * @return 返回"Female"或"Male"
     */
    public String getGenderDescription() {
        return gender == Gender.FEMALE ? "Female" : "Male";
    }

    /**
     * 增加学生总数
     */
    public void setStudentTotalNum() {
        incrementStudentCount();
    }

    /**
     * 获取学生总数
     */
    public static int getStudentTotalNum() {
        return studentTotalNum;
    }
    
    /**
     * 打印学生信息
     */
    public void printInfo() {
        String line = "+----------------------------------+";
        // -15s表示左对齐，
        String format = "| %-15s: %-15s |%n";
        
        System.out.println(line);
        System.out.printf(format, "Student Number", getStudentNumber());
        System.out.printf(format, "Name", getName());
        System.out.printf(format, "Age", getAge());
        System.out.printf(format, "Gender", getGenderDescription());
        System.out.println(line);
    }

    /**
     * 私有方法：增加学生计数
     */
    private void incrementStudentCount() {
        studentTotalNum++;
    }
}
