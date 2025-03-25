package week3;

public class SingleQuestion extends Question {
    private final char answer;

    // 无参构造函数
    public SingleQuestion() {
        super();
        this.answer = '\0';
    }

    // 带参数的构造函数
    public SingleQuestion(String text, String[] options, char answer) {
        super(text, options);
        this.answer = answer;
    }

    // 重写 check 方法，检查单选题答案
    @Override
    public boolean check(char[] answers) {
        if (answers == null || answers.length != 1) {
            return false;
        }
        return Character.toLowerCase(answers[0]) == Character.toLowerCase(answer);
    }

    // 重写 getAnswer 方法，返回单选题答案
    @Override
    public String getAnswer() {
        return String.valueOf(answer);
    }
}
