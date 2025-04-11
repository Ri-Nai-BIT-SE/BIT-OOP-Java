package hw4;
import java.util.HashMap;
import java.util.Map;

public class Paper {
    private Map<Integer, Question> questions;
    private int count;

    // 默认构造函数
    public Paper() {
        this.questions = new HashMap<>();
        this.count = 0;
    }

    // 带参数的构造函数
    public Paper(int count) {
        this.questions = new HashMap<>();
        this.count = count;
    }

    // 添加题目到试卷
    public void addQuestion(int index, Question question) {
        questions.put(index, question);
        count++; // 每次添加题目时递增计数器
    }

    // 获取所有题目
    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    // 获取题目数量
    public int getCount() {
        return count; // 返回当前题目数量
    }

    // 显示试卷内容
    public void showPaper() {
        System.out.println("试卷内容：");
        for (Map.Entry<Integer, Question> entry : questions.entrySet()) {
            System.out.println("题目编号：" + entry.getKey());
            entry.getValue().showQuestion();
        }
    }
}