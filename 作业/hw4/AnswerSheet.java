package hw4;

import java.util.HashMap;
import java.util.Map;

public class AnswerSheet {
    private Map<Integer, String> answers;

    // 默认构造函数
    public AnswerSheet() {
        this.answers = new HashMap<>();
    }

    // 添加答案到答题卡
    public void addAnswer(int key, String answer) {
        answers.put(key, answer);
    }

    // 获取所有答案
    public Map<Integer, String> getAnswers() {
        return answers;
    }
}
