package week1;

import java.util.ArrayList;
import java.util.Collections;

public class CodeGenerator {
    // 验证码包含的字符
    static final char[] includedChars = {
            '3', '4', '5', '6', '7', '8',

            'a', 'b', 'c', 'd', 'e', 'f',
            'h', 'i', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y',

            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y'
    };

    public static void main(String[] args) {
        try {
            // 生成长度为8的随机字符串
            String code = generateCode(4);
            System.out.println("Generated Code: " + code);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    static String generateCode(int length) {
        // 检查长度是否合法
        if (length <= 0 || length > includedChars.length) {
            throw new IllegalArgumentException("Length must be between 1 and " + includedChars.length);
        }

        // 生成随机字符串
        StringBuilder code = new StringBuilder();

        // 将数组 Copy 到 ArrayList
        ArrayList<Character> chars = new ArrayList<Character>();
        for (char c : includedChars) {
            chars.add(c);
        }

        // 打乱顺序
        Collections.shuffle(chars);

        // 从 ArrayList 中取出前 length 个字符
        for (int i = 0; i < length; i++) {
            code.append(chars.get(i));
        }

        // 返回字符串
        return code.toString();
    }
}
