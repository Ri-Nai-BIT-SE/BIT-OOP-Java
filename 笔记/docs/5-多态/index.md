# 多态

## 多态的定义

多态是面向对象编程中的一个重要概念，它允许一个类在运行时表现出不同的行为。多态通过继承和接口实现。

三要素：继承、向上转型、重写

:::details 静态绑定和动态绑定
## 静态绑定和动态绑定

在 Java 中，**静态绑定（Static Binding）和动态绑定（Dynamic Binding）** 是理解 **多态（Polymorphism）** 和 **方法调用机制** 的关键概念。它们决定了 Java 在运行时如何选择调用哪个方法。

### 🧠 一、什么是绑定（Binding）？

在 Java 中，**绑定是指将一个方法调用与一个方法体关联起来的过程**。

根据绑定的时机不同，分为：

| 类型         | 又称             | 绑定时机            | 示例场景                  |
|--------------|------------------|---------------------|---------------------------|
| 静态绑定     | 早期绑定（Early Binding） | 编译期（Compile Time） | `private`、`static`、`final` 方法、构造器等 |
| 动态绑定     | 晚期绑定（Late Binding）  | 运行期（Run Time）      | 虚方法（如非 private/static/final 的普通方法） |


### 🔍 二、静态绑定（Static Binding）

#### ✅ 定义：
静态绑定是在**编译期间**就确定要调用哪个方法的机制。

#### ⚙️ 原理：
- 根据**变量的声明类型**（不是实际对象类型）来决定调用哪个方法。
- 不依赖于对象的实际类型。

#### ✅ 适用情况：
以下几种方法使用静态绑定：

| 方法类型        | 是否静态绑定 | 说明 |
|-----------------|---------------|------|
| `private` 方法   | ✅             | 子类不能重写，只能自己调用 |
| `static` 方法    | ✅             | 属于类，不是对象 |
| `final` 方法     | ✅             | 不能被重写 |
| 构造函数         | ✅             | 不能被继承，也不能被重写 |

#### 🧪 示例：

```java
class Animal {
    static void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    static void speak() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.speak(); // 输出 "Animal speaks"（静态绑定）
    }
}
```

> 即使 `a` 实际指向的是 `Dog` 对象，但因为是 `static` 方法，所以调用的是 `Animal.speak()`。


### 🔁 三、动态绑定（Dynamic Binding）

#### ✅ 定义：
动态绑定是在**运行时**才确定要调用哪个方法的机制。

#### ⚙️ 原理：
- 根据**对象的实际类型**（而不是变量的声明类型）来决定调用哪个方法。
- 这是实现 **多态** 的基础。

#### ✅ 适用情况：
- 普通的方法（没有被 `private`、`static`、`final` 修饰）

#### 🧪 示例：

```java
class Animal {
    void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.speak(); // 输出 "Dog barks"（动态绑定）
    }
}
```

> 尽管变量 `a` 是 `Animal` 类型，但它实际指向的是 `Dog` 对象，因此调用了 `Dog` 的 `speak()` 方法。

### 📌 四、总结对比表

| 特征               | 静态绑定                   | 动态绑定                   |
|--------------------|----------------------------|----------------------------|
| 又称               | 早期绑定（Early Binding）  | 晚期绑定（Late Binding）   |
| 绑定时间           | 编译期                     | 运行期                     |
| 决定依据           | 声明类型                   | 实际对象类型               |
| 支持多态吗？       | ❌ 不支持                   | ✅ 支持                    |
| 常见方法类型       | `private`, `static`, `final`, 构造方法 | 普通虚方法（可重写）     |
| 性能效率           | 更快（无需运行时判断）     | 稍慢（需要运行时查找）     |


### 🧱 五、设计哲学：为什么要有两种绑定？

Java 设计者为了平衡性能和灵活性，采用了这两种绑定机制：

- **静态绑定**：提高性能，适合不会变化的行为（如工具类、不可变类）。
- **动态绑定**：实现多态，提供更强的扩展性和灵活性，是面向对象编程的核心。


### 📝 六、一句话总结：

> **静态绑定在编译时根据变量类型决定调用哪个方法；动态绑定在运行时根据对象实际类型决定调用哪个方法。动态绑定是 Java 多态的基础，而静态绑定用于提升性能或限制行为。**

:::










## 向上转型与向下转型

Java编译器允许在具有直接或间接继承关系的类之间进行类型转换。

- 子类对象->父类引用(**向上转型**)
- 父类引用->子类引用(**向下转型**)

### instanceof 关键字

instanceof 关键字用于判断一个对象是否属于某个类或其子类。

```java
if (obj instanceof ClassName) {
    // 如果 obj 是 ClassName 类型或其子类类型，则执行此处的代码
}
```

### 向上转型

向上转型是多态的一种表现形式，它允许一个子类对象被当作父类类型来使用。

```java
class Animal {
    void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog(); // 向上转型
        a.speak(); // 输出 "Dog barks"
    }
}
```





### 向下转型

向下转型是将父类引用转换为子类引用。

只能通过强制类型转换来实现向下转型。

需要使用 instanceof 关键字来判断对象是否属于某个子类。

```java
Animal a = new Dog(); // 向上转型
if (a instanceof Dog) {
    Dog d = (Dog) a; // 向下转型
    d.bark(); // 输出 "Dog barks"
}
```

## 开闭原则

开闭原则OCP（Open-Closed Principle）

面向对象设计的一个最**核心**的原则

对于**扩展**是**开放**的，对于**修改**是**关闭**的

在面向对象编程领域中，开闭原则规定“软件中的对象（类，模块，函数等等）应该对于扩展是开放的，但是对于修改是封闭的”，这意味着一个实体是允许在不改变它的源代码的前提下变更它的行为。

如果一个软件设计符合开闭原则，那么可以非常**方便**地对系统进行扩展，而且在扩展时无须修改现有代码，使得软件系统在拥有适应性和灵活性的同时具备较好的稳定性和延续性。


