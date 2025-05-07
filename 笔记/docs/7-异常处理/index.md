# Java 异常处理笔记

## 一、什么是异常？

在程序运行过程中发生的**不正常事件**，会打断正常的执行流程，称为 **异常（Exception）**。

Java 提供了一套完整的异常处理机制，让开发者可以优雅地处理错误情况，提高程序的健壮性和可读性。


## 二、Java 异常体系结构

Java 中所有的异常类都继承自 `java.lang.Throwable` 类，其主要子类如下：

```
Throwable
├── Error            // 严重问题，一般不处理（如 OutOfMemoryError）
└── Exception        // 程序中可以捕获和处理的问题
    ├── RuntimeException   // 非受检异常（Unchecked Exceptions）
    └── 其他异常           // 受检异常（Checked Exceptions）
```

### 常见异常分类：

| 类型 | 示例 | 是否必须处理 |
|------|------|--------------|
| `Error` | `OutOfMemoryError`, `StackOverflowError` | ❌ 不建议处理 |
| `RuntimeException` 及其子类 | `NullPointerException`, `ArrayIndexOutOfBoundsException` | ❌ 不强制处理 |
| 其他 `Exception` 子类 | `IOException`, `SQLException`, `ClassNotFoundException` | ✅ 必须处理或声明抛出 |


## 三、异常处理的五种关键字

Java 提供了以下关键字来进行异常处理：

| 关键字 | 作用 |
|--------|------|
| `try` | 包含可能会发生异常的代码块 |
| `catch` | 捕获并处理异常 |
| `finally` | 无论是否发生异常，都会执行的代码块（如关闭资源） |
| `throw` | 显式抛出一个异常对象 |
| `throws` | 在方法声明中声明该方法可能抛出的异常类型 |


## 四、try-catch-finally 的使用

### 基本语法：
```java
try {
    // 可能发生异常的代码
} catch (ExceptionType1 e1) {
    // 处理异常
} catch (ExceptionType2 e2) {
    // 处理另一种异常
} finally {
    // 总是执行的代码（如关闭文件、数据库连接等）
}
```

### 示例：
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("除数不能为零");
} finally {
    System.out.println("finally 块总是执行");
}
```

输出：
```
除数不能为零
finally 块总是执行
```


## 五、多异常捕获（Java 7+）

你可以在一个 `catch` 块中捕获多个异常类型（用 `|` 分隔）：

```java
try {
    // ...
} catch (IOException | SQLException e) {
    e.printStackTrace();
}
```


## 六、throws 和 throw 的区别

| 对比项 | `throws` | `throw` |
|--------|----------|---------|
| 位置 | 写在方法签名上 | 写在方法体内 |
| 作用 | 声明该方法可能抛出哪些异常 | 实际抛出一个异常对象 |
| 参数类型 | 异常类名 | 异常实例 |

### 示例：

```java
public void readFile(String filename) throws IOException {
    if (filename == null) {
        throw new NullPointerException("文件名不能为空");
    }
    // ...
}
```


## 七、自定义异常

你可以通过继承 `Exception` 或 `RuntimeException` 来创建自己的异常类。

### 示例：受检异常

```java
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message); // 调用 Exception(message) 构造方法
    }
}

public class Person {
    public void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("年龄不能为负数");
        }
    }
}
```

### 示例：非受检异常

```java
class InvalidNameException extends RuntimeException {
    public InvalidNameException(String message) {
        super(message); // 调用 RuntimeException(message) 构造方法
    }
}

public class Person {
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("名字不能为空");
        }
    }
}
```

## 八、异常处理的最佳实践

| 原则 | 说明 |
|------|------|
| ❌ 不要“吞”异常 | 即不要写空的 `catch` 块 |
| ✅ 记录日志 | 使用 `e.printStackTrace()` 或日志框架记录异常信息 |
| ✅ 资源清理放在 `finally` | 如关闭文件、网络连接等 |
| ✅ 尽量具体捕获异常 | 不要用 `catch (Exception e)` 捕捉所有异常 |
| ✅ 抛出合适的异常 | 根据业务逻辑选择受检/非受检异常 |
| ✅ 使用 try-with-resources（Java 7+） | 自动关闭资源，避免手动写 `finally` |

### 示例：try-with-resources（自动关闭资源）

```java
try (FileInputStream fis = new FileInputStream("file.txt")) {
    int data = fis.read();
    while (data != -1) {
        System.out.print((char) data);
        data = fis.read();
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

等价于

```java
FileInputStream fis = new FileInputStream("file.txt");
try {
    // ...
} finally {
    fis.close();
}
```

## 九、受检异常 vs 非受检异常（重要对比）

| 特征 | 受检异常（Checked Exceptions） | 非受检异常（Unchecked Exceptions） |
|------|----------------------------------|------------------------------------|
| 继承关系 | 继承自 `Exception`，但不是 `RuntimeException` | 继承自 `RuntimeException` |
| 是否强制处理 | ✅ 是 | ❌ 否 |
| 示例 | `IOException`, `SQLException` | `NullPointerException`, `ArrayIndexOutOfBoundsException` |
| 推荐使用场景 | 期望调用者处理的外部错误（如 IO、网络） | 编程错误或不可恢复的内部错误 |


## 十、总结一句话：

> **Java 的异常处理机制是一种强大的工具，帮助开发者识别和处理程序中的异常情况。理解 `try-catch-finally` 的使用、区分受检与非受检异常、合理使用 `throw` 和 `throws`，以及编写自定义异常，是写出高质量 Java 程序的关键技能。**

