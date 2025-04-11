#import "@preview/bit-undergraduate-thesis-template:0.1.0" as template

#show: template.paper.with(
  subject: "面向对象技术与方法",
  title: "第一周作业",
  title-en: "",
  has_declear: false,
  has_contents: false,
  info-columns: (
    ("专业", "软件工程"),
    ("班级", "软工2301班"),
    ("姓名", "叶子宁"),
    ("学号", "1120231313"),
  ),
  header: "面向对象技术与方法",
  date: datetime.today(),
)
#set heading(numbering: none)
= 四则运算
== 作业要求
用基本类型中的 ```java int``` 和 ```java float```, ```java BigInteger```, ```java BigDecimal``` 实现四则运算。

=== 个人理解
由于作业的要求比较模糊，认为这里的作业应该不是实现一个四则运算的计算器（毕竟有多种类型的选择）。

作业目的应该是让我们了解到不同类型的数据在计算时的差异，以及在计算时的注意事项。

== 实现
源代码在 `ArithmeticOperation.java` 中，硬编码了内部的数据。

主体代码如下：
```java
public class ArithmeticOperation {
    public static void main(String[] args) {
        // 基本类型运算
        primitiveOperations();
        // 大数类型运算
        bigNumberOperations();
        // 大数类型与基本类型运算对比
        compareOperations();
        // 交叉类型运算
        crossTypeOperations();
    }
    private static void primitiveOperations()
    private static void bigNumberOperations()
    private static void compareOperations()
    private static void crossTypeOperations()
}
```
#template.indent()
主要展示了：
1. 基本类型自身的四则运算 \
2. 大数类型的四则运算
3. 大数类型与基本类型的运算对比 \
   涉及到整形溢出、浮点数精度问题
4. 交叉类型的运算 \
   涉及到类型转换的问题

具体代码比较简单，这里就不展示了。

= 验证码生成

== 作业要求
为了防止对网站的恶意注册，用户在注册时通常被要求输入网站提供的验证码。编写一个为网站生成验证码的程序，设验证码是4位，不能重复，验证码由数字和大小写字母组成，但为了避免混淆，不能包含`1`，`l`，`L`，`0`，`o`，`O`，`2`，`z`，`Z`，`9`，`g`。

== 实现
源码在 `CodeGenerator.java` 中。

ppt 中疑似好像让我们用 boolean 数组标记的方式实现？

但是我一开始没仔细看到。为比较简单选择不重复的验证码，我选择使用 ```java Collection.shuffle``` 的方式。类似于 `cpp` 中的 ```cpp random_shuffle```。

将数组打乱后，取前 `n` 个字符即可。

实现起来应该比 `do-while` 直到生成不重复的验证码要简单且优雅一些。
```java
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
```
