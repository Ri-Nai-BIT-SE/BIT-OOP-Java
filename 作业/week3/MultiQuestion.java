package week3;

public class MultiQuestion extends Question {
    private final char[] answer;

    // 无参构造函数
    public MultiQuestion() {
        super();
        this.answer = new char[0];
    }

    // 带参数的构造函数
    public MultiQuestion(String text, String[] options, char[] answer) {
        super(text, options);
        this.answer = answer;
    }

    // 重写 check 方法，检查多选题答案
    @Override
    public boolean check(char[] answers) {
        if (answers == null || answers.length != answer.length) {
            return false;
        }
        // 忽略大小写（不改变原数组）
        char[] upperCaseAnswers = new char[answers.length];
        for (int i = 0; i < answers.length; i++) {
            upperCaseAnswers[i] = Character.toUpperCase(answers[i]);
        }
        char[] upperCaseCorrectAnswers = new char[answer.length];
        for (int i = 0; i < answer.length; i++) {
            upperCaseCorrectAnswers[i] = Character.toUpperCase(answer[i]);
        }
        // 排序后比较
        java.util.Arrays.sort(upperCaseAnswers);
        java.util.Arrays.sort(upperCaseCorrectAnswers);

        for (int i = 0; i < answers.length; i++) {
            if (upperCaseAnswers[i] != upperCaseCorrectAnswers[i]) {
                return false;
            }
        }
        return true;
    }

    // 重写 getAnswer 方法，返回多选题答案
    @Override
    public String getAnswer() {
        return String.valueOf(answer);
    }
}
