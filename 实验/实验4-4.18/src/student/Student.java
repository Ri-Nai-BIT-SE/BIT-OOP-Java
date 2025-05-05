package student;

class Student {
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
        return String.format("ID: %4d, Name: %12s, Age: %3d, Grade: %3d", id, name, age, grade);
    }
}

