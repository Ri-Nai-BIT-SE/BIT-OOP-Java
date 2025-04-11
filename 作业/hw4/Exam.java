package hw4;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Exam {
    private Student student;
    private Paper paper;
    private AnswerSheet answerSheet;

    // 默认构造函数
    public Exam() {
    }

    // 带参数的构造函数
    public Exam(Student student) {
        this.student = student;
        this.paper = new Paper();
        this.answerSheet = new AnswerSheet();
    }

    // 创建试卷
    public void createPaper() {
        // 示例：创建一个包含 3 道题目的试卷
        Question q1 = new Question("What is the capital of France?",
                List.of("Paris", "London", "Berlin"),
                "Paris");
        Question q2 = new Question("What is the largest planet in the solar system?",
                List.of("Earth", "Jupiter", "Mars"),
                "Jupiter");
        Question q3 = new Question("Who painted the Mona Lisa?",
                List.of("Leonardo da Vinci", "Michelangelo", "Raphael"),
                "Leonardo da Vinci");

        paper.addQuestion(1, q1);
        paper.addQuestion(2, q2);
        paper.addQuestion(3, q3);
    }

    // 学生作答
    public void answerQuestions() {
        Scanner scanner = new Scanner(System.in); // 使用 Scanner 读取输入
        paper.showPaper();
        System.out.println("请输入您的答案（按题目编号输入选项编号）：");

        for (Map.Entry<Integer, Question> entry : paper.getQuestions().entrySet()) {
            int questionNumber = entry.getKey();
            Question question = entry.getValue();

            System.out.print("题目 " + questionNumber + " 的答案：");
            String userInput = scanner.nextLine(); // 用户输入选项编号
            try {
                int optionIndex = Integer.parseInt(userInput); // 将输入转换为整数
                if (optionIndex >= 1 && optionIndex <= question.getOptions().size()) {
                    String selectedOption = question.getOptions().get(optionIndex - 1); // 获取对应的选项内容
                    answerSheet.addAnswer(questionNumber, selectedOption); // 记录选项内容
                } else {
                    System.out.println("无效的选项编号，默认记录为空答案。");
                    answerSheet.addAnswer(questionNumber, ""); // 如果输入无效，记录为空答案
                }
            } catch (NumberFormatException e) {
                System.out.println("输入无效，默认记录为空答案。");
                answerSheet.addAnswer(questionNumber, ""); // 如果输入不是数字，记录为空答案
            }
        }

        scanner.close(); // 关闭 Scanner
    }

    // 显示分数
    public void showScoreOfPaper() {
        int totalScore = 0;
        int questionCount = paper.getCount();

        System.out.println("\n评分结果：");
        for (Map.Entry<Integer, Question> entry : paper.getQuestions().entrySet()) {
            int questionNumber = entry.getKey();
            Question question = entry.getValue();
            String correctAnswer = question.getAnswer();
            String userAnswer = answerSheet.getAnswers().get(questionNumber);

            System.out.print("题目 " + questionNumber + ": ");
            if (correctAnswer.equals(userAnswer)) {
                System.out.println("正确！");
                totalScore++;
            } else {
                System.out.println("错误！正确答案是：" + correctAnswer);
            }
        }

        double score = (double) totalScore / questionCount * 100;
        System.out.println("\n最终得分：" + String.format("%.2f", score));
    }

    // 考试流程
    public void exam() {
        createPaper();
        answerQuestions();
        showScoreOfPaper();
    }
}
