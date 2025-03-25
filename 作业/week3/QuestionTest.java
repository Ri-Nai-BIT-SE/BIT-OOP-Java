package week3;

import java.util.Scanner;

public class QuestionTest {
    public static void main(String[] args) {
        // 创建单选题
        SingleQuestion singleQuestion = new SingleQuestion(
                "三国演义中的三绝是谁?",
                new String[]{"曹操", "刘备", "关羽", "诸葛亮"},
                'D'
        );

        // 创建多选题
        MultiQuestion multiQuestion = new MultiQuestion(
                "最早向刘备推荐诸葛亮的是谁?",
                new String[]{"徐庶", "司马徽", "鲁肃", "关羽"},
                new char[]{'A', 'B'}
        );

        // 测试单选题
        testQuestion(singleQuestion);

        // 测试多选题
        testQuestion(multiQuestion);
    }

    // 测试问题的方法
    public static void testQuestion(Question question) {
        Scanner scanner = new Scanner(System.in);

        // 打印问题和选项
        question.print();

        // 获取用户输入
        System.out.print("请选择: ");
        String input = scanner.nextLine();
        char[] answers = input.toCharArray();

        // 检查答案
        boolean isCorrect = question.check(answers);

        // 输出结果
        if (isCorrect) {
            System.out.println("恭喜，答对了！");
        } else {
            System.out.println(
                    "很遗憾，答错了。\n"
                            + "正确答案是："
                            + question.getAnswer()
            );
        }
        scanner.close();
    }
}
