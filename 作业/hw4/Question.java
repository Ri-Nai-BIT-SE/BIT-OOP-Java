package hw4;

import java.util.List;

public class Question {
    private String title;
    private List<String> options;
    private String answer;

    // 默认构造函数
    public Question() {
    }

    // 带参数的构造函数
    public Question(String title, List<String> options, String answer) {
        this.title = title;
        this.options = options;
        this.answer = answer;
    }

    // 显示题目方法
    public void showQuestion() {
        System.out.println(title);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    // 获取答案方法
    public String getAnswer() {
        return answer;
    }

    // Getter 和 Setter 方法
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswerText() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}


