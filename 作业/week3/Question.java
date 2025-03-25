package week3;

public class Question {
    protected String text;
    protected String[] options;

    // 无参构造函数
    public Question() {
        this.text = "";
        this.options = new String[0];
    }

    // 带参数的构造函数
    public Question(String text, String[] options) {
        this.text = text;
        this.options = options;
    }

    // 打印问题和选项
    public void print() {
        System.out.println(text);
        for (int i = 0; i < options.length; i++) {
            System.out.println((char) ('A' + i) + ". " + options[i]);
        }
    }

    // 检查答案（默认实现，子类需重写）
    public boolean check(char[] answers) {
        return false; // 默认返回 false
    }

    // 获取答案（默认实现，子类需重写）
    public String getAnswer() {
        return "";
    }
}
