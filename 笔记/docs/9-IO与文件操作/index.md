非常好，下面我将根据你提供的《第12章 Java输入输出流》的完整内容，**重新系统梳理并整理成一份结构清晰、逻辑严谨、便于复习和查阅的笔记文档**。

---

# 📘 第12章：Java 输入输出流（I/O）知识点总结

## 一、I/O 基本概念

### ✅ 什么是 I/O？
- **Input/Output（输入/输出）** 是程序与外部设备或网络之间进行数据交换的过程。
- Java 使用"**流（Stream）**"的方式处理 I/O 操作，抽象为一个流动的数据管道。

### ✅ Java I/O 的发展历程：
| 版本 | 功能 |
|------|------|
| JDK 1.0 | 面向字节的 I/O 流（`InputStream` / `OutputStream`） |
| JDK 1.1 | 引入面向字符的 I/O 流（`Reader` / `Writer`） |
| JDK 1.4 | 新增 `java.nio` 包，提升性能和功能 |
| JDK 7+ | 支持 `try-with-resources` 自动关闭资源机制 |

---

## 二、Java 流的分类

| 分类方式 | 类型 | 描述 |
|----------|------|------|
| 按方向 | 输入流 / 输出流 | 数据从源读取或写入目标 |
| 按单位 | 字节流 / 字符流 | 以字节（8位）或字符（16位 Unicode）为单位 |
| 按功能 | 节点流 / 处理流 | 节点流直接操作物理存储；处理流增强节点流的功能 |

---

## 三、字节流体系

### 🔹 抽象类：`InputStream` 和 `OutputStream`

#### `InputStream`接口与主要方法：
- `int read()`: 读取单个字节，返回0-255的整数，如果到达末尾返回-1
- `int read(byte[] b)`: 读取多个字节到缓冲区，返回实际读取的字节数
- `int read(byte[] b, int off, int len)`: 读取指定长度到缓冲区指定位置
- `void close()`: 关闭流并释放资源
- `long skip(long n)`: 跳过n个字节
- `int available()`: 返回不阻塞的情况下可读取的字节数

#### `OutputStream`接口与主要方法：
- `void write(int b)`: 写入单个字节
- `void write(byte[] b)`: 写入字节数组
- `void write(byte[] b, int off, int len)`: 写入部分字节数组
- `void flush()`: 刷新缓冲区
- `void close()`: 关闭流并释放资源

#### 1. `InputStream` 主要子类：
- `FileInputStream`：从文件中读取字节
- `ByteArrayInputStream`：从内存缓冲区读取字节
- `PipedInputStream`：用于线程间通信
- `SequenceInputStream`：合并多个流为一个流

#### 2. `OutputStream` 主要子类：
- `FileOutputStream`：向文件写入字节
- `ByteArrayOutputStream`：在内存中收集字节
- `PipedOutputStream`：用于线程间通信

#### 示例：使用 `FileInputStream` + `FileOutputStream` 实现文件复制
```java
try (FileInputStream fis = new FileInputStream("input.txt");
     FileOutputStream fos = new FileOutputStream("output.txt")) {
    int data;
    while ((data = fis.read()) != -1) {
        fos.write(data);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

---

### 🔹 缓冲流：`BufferedInputStream` 和 `BufferedOutputStream`

#### 接口特性：
- 继承自`FilterInputStream`和`FilterOutputStream`
- 默认缓冲区大小为8KB，可通过构造函数设置
- 显著提高I/O操作性能，减少系统调用次数

#### `BufferedInputStream`主要方法：
- 继承`InputStream`的所有方法
- `void mark(int readlimit)`: 标记当前位置
- `void reset()`: 重置到上次标记位置
- `boolean markSupported()`: 是否支持标记功能

#### `BufferedOutputStream`主要方法：
- 继承`OutputStream`的所有方法
- `void flush()`: 重写以刷新缓冲区数据到底层流

#### 示例：带缓冲的文件复制
```java
try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("input.bin"));
     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("output.bin"))) {
    int data;
    while ((data = bis.read()) != -1) {
        bos.write(data);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

---

### 🔹 数据过滤流：`DataInputStream` 和 `DataOutputStream`

#### 接口特性：
- 实现了`DataInput`和`DataOutput`接口
- 用于按基本数据类型读写（int, double, boolean 等）
- 必须配合使用，不能混用普通文本流

#### `DataInputStream`主要方法：
- `readBoolean()`, `readByte()`, `readChar()`, `readDouble()`, `readFloat()`
- `readInt()`, `readLong()`, `readShort()`, `readUTF()`
- `skipBytes(int n)`: 跳过指定字节数

#### `DataOutputStream`主要方法：
- `writeBoolean()`, `writeByte()`, `writeChar()`, `writeDouble()`, `writeFloat()`
- `writeInt()`, `writeLong()`, `writeShort()`, `writeUTF()`
- `size()`: 返回已写入的字节数

#### 示例：
```java
// 写入
try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.bin"))) {
    dos.writeInt(100);
    dos.writeDouble(3.14);
    dos.writeUTF("Hello");
}

// 读取
try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {
    System.out.println(dis.readInt());       // 100
    System.out.println(dis.readDouble());    // 3.14
    System.out.println(dis.readUTF());       // Hello
}
```

---

### 🔹 打印流：`PrintStream` / `PrintWriter`

#### `PrintStream`接口特性：
- 扩展自`FilterOutputStream`
- 不会抛出IOException异常
- 可设置自动刷新功能

#### 主要方法：
- `println()`: 打印数据并添加换行符
- `print()`: 打印数据不添加换行符
- `printf()`: 格式化输出
- `checkError()`: 检查是否发生错误

#### `PrintWriter`接口特性：
- 基于字符流，支持国际化字符
- 提供与PrintStream相同的打印方法
- 可指定字符编码

#### 示例：
```java
PrintWriter writer = new PrintWriter(System.out, true);
writer.println("这是一条日志信息");
```

---

### 🔹 对象流：`ObjectInputStream` / `ObjectOutputStream`

#### 接口特性：
- 实现`ObjectInput`/`ObjectOutput`接口
- 支持对象序列化和反序列化
- 要求对象类实现`Serializable`接口

#### `ObjectOutputStream`主要方法：
- `writeObject(Object obj)`: 序列化对象
- `writeStreamHeader()`: 写入流头部信息
- 其他继承自DataOutputStream的写入方法

#### `ObjectInputStream`主要方法：
- `Object readObject()`: 反序列化对象
- `readStreamHeader()`: 读取流头部信息
- 其他继承自DataInputStream的读取方法

#### 示例：
```java
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int age;
}

// 序列化
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
    oos.writeObject(new Student("张三", 20));
}

// 反序列化
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
    Student s = (Student) ois.readObject();
    System.out.println(s.name + ", " + s.age);
}
```

---

### 🔹 字节数组流：`ByteArrayInputStream` / `ByteArrayOutputStream`

#### `ByteArrayInputStream`接口特性：
- 从内存字节数组读取数据的输入流
- 不需要关闭，没有底层资源

#### 主要方法：
- 继承自InputStream的所有方法
- `available()`: 返回可读取的字节数
- `mark(int)`和`reset()`: 支持标记和重置

#### `ByteArrayOutputStream`接口特性：
- 输出数据到内存字节数组的输出流
- 可动态增长的字节缓冲区

#### 主要方法：
- 继承自OutputStream的所有方法
- `toByteArray()`: 获取内部字节数组副本
- `toString()`: 将内容转换为字符串
- `writeTo(OutputStream)`: 写入到其他输出流

#### 示例：对象深复制（通过序列化）
```java
public static <T extends Serializable> T deepCopy(T object) throws IOException, ClassNotFoundException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(object);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bais);
    return (T) ois.readObject();
}
```

---

## 四、字符流体系

### 🔹 抽象类：`Reader` / `Writer`

#### `Reader`接口与主要方法：
- `int read()`: 读取单个字符
- `int read(char[] cbuf)`: 读取多个字符到数组
- `int read(char[] cbuf, int off, int len)`: 读取指定长度到数组指定位置
- `void close()`: 关闭流并释放资源
- `boolean ready()`: 检查流是否准备好被读取

#### `Writer`接口与主要方法：
- `void write(int c)`: 写入单个字符
- `void write(char[] cbuf)`: 写入字符数组
- `void write(char[] cbuf, int off, int len)`: 写入部分字符数组
- `void write(String str)`: 写入字符串
- `void write(String str, int off, int len)`: 写入部分字符串
- `void flush()`: 刷新缓冲区
- `void close()`: 关闭流并释放资源

### 🔹 转换流：`InputStreamReader` / `OutputStreamWriter`

#### 接口特性：
- 字节流和字符流之间的桥梁
- 可指定字符编码，支持国际化

#### `InputStreamReader`主要方法：
- 继承自Reader的所有方法
- `String getEncoding()`: 获取使用的编码

#### `OutputStreamWriter`主要方法：
- 继承自Writer的所有方法
- `String getEncoding()`: 获取使用的编码

#### 示例：指定 UTF-8 编码写入中文
```java
try (OutputStreamWriter writer = new OutputStreamWriter(
         new FileOutputStream("utf8.txt"), "UTF-8")) {
    writer.write("你好，世界！");
}
```

---

### 🔹 文件字符流：`FileReader` / `FileWriter`

#### 接口特性：
- `FileReader`是`InputStreamReader`的子类
- `FileWriter`是`OutputStreamWriter`的子类
- 默认使用系统平台的字符编码

#### 主要方法：
- 继承自Reader/Writer的所有方法
- 没有添加新方法，主要简化构造函数

#### 示例：
```java
try (FileReader reader = new FileReader("hello.txt")) {
    int ch;
    while ((ch = reader.read()) != -1) {
        System.out.print((char) ch);
    }
}
```

---

### 🔹 缓冲字符流：`BufferedReader` / `BufferedWriter`

#### 接口特性：
- 为字符流提供缓冲功能
- 支持按行读写操作

#### `BufferedReader`主要方法：
- 继承自Reader的所有方法
- `String readLine()`: 读取一行文本，不包含换行符

#### `BufferedWriter`主要方法：
- 继承自Writer的所有方法
- `void newLine()`: 写入一个平台相关的换行符

#### 示例：逐行读取文件
```java
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}
```

---

### 🔹 打印流：`PrintWriter`

#### 接口特性：
- 提供格式化输出功能
- 不会抛出IOException
- 可自动刷新

#### 主要方法：
- 继承自Writer的所有方法
- `println()`: 打印并换行
- `print()`: 打印不换行
- `printf()`: 格式化输出
- `format()`: 格式化输出
- `checkError()`: 检查错误状态

#### 示例：
```java
PrintWriter writer = new PrintWriter(System.out, true);
writer.printf("姓名: %s, 年龄: %d%n", "李四", 25);
```

---

## 五、随机访问文件：`RandomAccessFile`

#### 接口特性：
- 实现了`DataInput`和`DataOutput`接口
- 既不是InputStream也不是OutputStream的子类
- 支持随机读写文件中的任意位置

#### 主要方法：
- `long getFilePointer()`: 获取当前文件指针位置
- `void seek(long pos)`: 设置文件指针位置
- `long length()`: 获取文件长度
- `int skipBytes(int n)`: 跳过n个字节
- 继承自DataInput/DataOutput的所有读写方法

#### 构造函数模式：
- `"r"`: 只读模式
- `"rw"`: 读写模式
- `"rws"`: 读写模式，同步文件内容和元数据更新
- `"rwd"`: 读写模式，同步文件内容更新

#### 示例：
```java
try (RandomAccessFile raf = new RandomAccessFile("random.dat", "rw")) {
    raf.writeInt(100);      // 写入整数
    raf.seek(0);            // 移动指针到开头
    System.out.println(raf.readInt());  // 读取整数
}
```

---

## 六、操作文件的工具类

### 🔹 `Path` 接口 和 `Paths` 类

#### `Path`接口主要方法：
- `Path getFileName()`: 获取文件名
- `Path getParent()`: 获取父路径
- `Path resolve(Path/String)`: 解析路径
- `Path relativize(Path)`: 获取相对路径
- `Path normalize()`: 规范化路径
- `File toFile()`: 转换为File对象
- `boolean startsWith(Path/String)`: 判断是否以指定路径开始
- `boolean endsWith(Path/String)`: 判断是否以指定路径结束

#### `Paths`类主要方法：
- `static Path get(String first, String... more)`: 获取路径实例
- `static Path get(URI uri)`: 通过URI获取路径

#### 示例：
```java
Path path = Paths.get("data", "input.txt");
System.out.println(path.toString());  // Windows: data\input.txt
```

### 🔹 `Files` 类

#### 主要静态方法：
- 文件操作：`createFile()`, `delete()`, `copy()`, `move()`
- 目录操作：`createDirectory()`, `createDirectories()`
- 读取内容：`readAllBytes()`, `readAllLines()`, `lines()`
- 写入内容：`write()`, `writeString()`
- 属性读取：`size()`, `getLastModifiedTime()`, `isDirectory()`, `isRegularFile()`
- 遍历目录：`list()`, `walk()`, `find()`

#### 示例：
```java
// 创建目录
Files.createDirectories(Paths.get("temp", "logs"));

// 读取文件内容
List<String> lines = Files.readAllLines(Paths.get("data.txt"));
lines.forEach(System.out::println);
```

---

## 七、流的分类汇总表

| 类型 | 节点流 | 处理流 |
|------|--------|--------|
| 字节流 | `FileInputStream` / `FileOutputStream`<br>`ByteArrayInputStream` / `ByteArrayOutputStream` | `BufferedInputStream` / `BufferedOutputStream`<br>`DataInputStream` / `DataOutputStream`<br>`ObjectInputStream` / `ObjectOutputStream` |
| 字符流 | `FileReader` / `FileWriter`<br>`CharArrayReader` / `CharArrayWriter` | `InputStreamReader` / `OutputStreamWriter`<br>`BufferedReader` / `BufferedWriter`<br>`PrintWriter` |

---

## 八、流的设计模式：装饰器模式（Decorator Pattern）

- `FilterInputStream` / `FilterOutputStream` 是所有处理流的基类
- 通过组合方式动态扩展流功能，避免类爆炸
- 示例结构：

```
InputStream
   ↳ FileInputStream        ← 节点流（真实源）
   ↳ FilterInputStream      ← 抽象装饰类
       ↳ BufferedInputStream ← 具体装饰类1（加缓冲）
       ↳ DataInputStream     ← 具体装饰类2（读写基本类型）
       ↳ ObjectInputStream   ← 具体装饰类3（支持对象序列化）
```

---

## 九、总结建议

| 场景 | 推荐使用的流 |
|------|---------------|
| 顺序读写二进制文件 | `FileInputStream` / `FileOutputStream` |
| 顺序读写文本文件 | `FileReader` / `FileWriter` 或 `InputStreamReader` / `OutputStreamWriter` |
| 读写基本数据类型 | `DataInputStream` / `DataOutputStream` |
| 读写对象 | `ObjectInputStream` / `ObjectOutputStream` |
| 格式化输出 | `PrintWriter` |
| 随机访问文件 | `RandomAccessFile` |
| 文件管理 | `Path` / `Paths` / `Files` |

---

## 十、思维导图结构（建议整理为笔记大纲）

你可以将以下结构作为整理笔记的大纲：

```
第12章 Java输入输出流
├── I/O基本概念
├── 流的分类
│   ├── 按方向：输入流 / 输出流
│   ├── 按单位：字节流 / 字符流
│   └── 按功能：节点流 / 处理流
├── 字节流体系
│   ├── InputStream / OutputStream
│   ├── FileInputStream / FileOutputStream
│   ├── BufferedInputStream / BufferedOutputStream
│   ├── DataInputStream / DataOutputStream
│   ├── ObjectInputStream / ObjectOutputStream
│   └── ByteArrayInputStream / ByteArrayOutputStream
├── 字符流体系
│   ├── Reader / Writer
│   ├── InputStreamReader / OutputStreamWriter
│   ├── FileReader / FileWriter
│   ├── BufferedReader / BufferedWriter
│   └── PrintWriter
├── RandomAccessFile
└── 文件操作工具类
    ├── Path / Paths
    └── Files
```

