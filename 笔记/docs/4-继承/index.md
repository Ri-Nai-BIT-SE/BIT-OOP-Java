# 继承

## 继承的定义

继承是面向对象编程中的一个重要概念，它允许一个类（子类）继承另一个类（父类）的属性和方法。通过继承，子类可以重用父类的代码，并可以添加新的属性和方法，或者重写父类的方法。


## 继承的语法

```java
class 子类 extends 父类 {
    // 子类的属性和方法
    @Override
    public void 方法名() {
        // 重写父类的方法
    }

    public void 其他方法() {
        // 其他方法
    }
}
```

## 重写

重写是子类对父类方法的重新实现。重写的方法**必须**与父类的方法具有相同的方法名、参数列表和返回类型。

```java
class 父类 {
    public void 方法名() {
        // 父类的方法
    }
}

class 子类 extends 父类 {
    @Override
    public void 方法名() {
        // 子类的方法
    }
}
```
:::details 重写与重载的区别
**重写**(Override)：
重写是子类对父类方法的重新实现。重写的方法**必须**与父类的方法具有相同的方法名、参数列表和返回类型。

**重载**(Overload)：
重载是同一个类中多个方法具有相同的方法名，但参数列表不同。

重载：
```java
class 类名 {
    public void test(int a) {
        // 方法体
    }

    public void test(int a, int b) {
        // 方法体
    }
}

重写：
```java
class 子类 extends 父类 {
    @Override
    public void test(int a) {
        // 方法体
    }
}
```
:::


## Object类

Object类是所有类的父类，所有类都直接或间接继承自Object类。

Object类中定义了以下方法：

| 方法名 | 描述 |
| --- | --- |
| `clone()` | 克隆一个一模一样的对象 |
| `finalize()` | 在对象被垃圾收集器析构(回收)之前调用，用来清除回收对象 |
| `toString()` | 把非字符串的数据类型转化为字符串，输出结果：包名.类名+@+16进制的哈希值 |
| `equals()` | 用于检测一个对象是否等于另外一个对象 |
| `hashCode()` | 在散列存储结构中确定对象的存储地址，用hashcode来代表对象就是在hash表中的位置 |
| `notify()` | 唤醒在此对象锁(监视器)上等待的单个线程 |
| `notifyAll()` | 唤醒在此对象锁(监视器)上等待的所有线程 |
| `wait()` | 当前线程释放对象锁(监视器)的拥有权，在其他线程调用此对象的 notify() 方法或 notifyAll() 方法前，当前线程处于等待状态 |
| `getClass()` | 返回此 Object 的运行时类 |

### 重写toString()方法

一般我们重写toString()方法，返回对象的描述信息。

```java
@Override
public String toString() {
    return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
}
```


:::details clone()方法
### clone()方法

#### 🧬 一、什么是 `clone()`？

在 Java 中，`clone()` 是定义在 `Object` 类中的一个受保护（`protected`）方法：

```java
protected native Object clone() throws CloneNotSupportedException;
```

它的作用是：**创建并返回当前对象的一个副本（拷贝）**。这个副本是一个新的对象，但内容与原对象相同。


#### ⚠️ 二、为什么不能直接调用 `clone()`？

因为 `clone()` 在 `Object` 中是 `protected` 的，所以你不能直接对任意对象调用 `clone()`，除非：

1. 该类 **重写了 `clone()` 方法为 `public`**
2. 并且实现了 `Cloneable` 接口（标记接口）

否则会抛出异常：`CloneNotSupportedException`

#### ✅ 三、如何正确使用 `clone()`？（实现步骤）

##### 步骤 1：让类实现 `Cloneable` 接口

这是一个空接口（marker interface），只是告诉 JVM 这个类允许被克隆。

```java
public class Person implements Cloneable {
    // ...
}
```

##### 步骤 2：重写 `clone()` 方法，并改为 `public`

```java
@Override
public Person clone() throws CloneNotSupportedException {
    return (Person) super.clone();
}
```

> 注意：必须显式地将返回值强转为你自己的类型。

##### 步骤 3：调用 `clone()` 方法

```java
Person p1 = new Person("Tom");
Person p2 = p1.clone(); // 克隆一个新对象
```


#### 🔁 四、浅拷贝 vs 深拷贝

##### ✅ 1. 浅拷贝（默认行为）

- 基本类型字段：值拷贝
- 引用类型字段：只复制引用地址（不创建新对象）

###### 示例：
```java
class Address {
    String city;
}

class Person implements Cloneable {
    String name;
    Address address;

    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone(); // 浅拷贝
    }
}
```

执行后：
```java
Person p1 = new Person();
p1.address = new Address("Beijing");

Person p2 = p1.clone();

System.out.println(p1.address == p2.address); // true，指向同一个对象
```


##### ✅ 2. 深拷贝（需要手动实现）

- 对所有引用类型的字段也进行克隆（递归克隆）

###### 实现方式：

**方法一**：手动克隆每个引用字段

```java
@Override
public Person clone() throws CloneNotSupportedException {
    Person copy = (Person) super.clone();
    copy.address = this.address != null ? new Address(this.address.city) : null;
    return copy;
}
```

**方法二**：让引用类也实现 `Cloneable`

```java
class Address implements Cloneable {
    String city;

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    @Override
    public Person clone() throws CloneNotSupportedException {
        Person copy = (Person) super.clone();
        copy.address = this.address.clone(); // 深拷贝
        return copy;
    }
}
```

#### 💡 五、clone 的优缺点

| 特性 | 描述 |
|------|------|
| ✅ 优点 | 简单快速，适合浅拷贝 |
| ❌ 缺点 | 默认是浅拷贝，深拷贝需手动实现；容易出错；不安全；破坏封装性 |
| ✅ 使用场景 | 快速复制对象结构，如原型模式 |
| ❌ 替代方案 | 手动赋值、序列化反序列化、第三方库（如 Apache Commons Lang、Dozer、ModelMapper） |


#### 🧪 六、完整示例（带深拷贝）

```java
class Address implements Cloneable {
    String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        Person copy = (Person) super.clone();
        copy.address = this.address != null ? this.address.clone() : null; // 深拷贝
        return copy;
    }
}

// 使用
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Shanghai");
        Person p1 = new Person("Alice", addr);
        Person p2 = p1.clone();

        System.out.println(p1.name == p2.name);          // true（字符串常量池）
        System.out.println(p1.address == p2.address);    // false，深拷贝成功
    }
}
```


#### 📦 七、其他克隆方式对比

| 方式 | 是否支持深拷贝 | 性能 | 易用性 | 安全性 |
|------|----------------|------|--------|--------|
| `clone()` | 否（默认），可手动实现 | ✅ 高 | ❌ 稍复杂 | ❌ 可能破坏封装 |
| 序列化 | ✅ 支持深拷贝 | ❌ 低 | ✅ 简单 | ✅ 安全 |
| 第三方库（如 Dozer、ModelMapper） | ✅ | ✅ | ✅ | ✅ |
| 手动 set/get | ✅ | ❌ 低 | ❌ 烦琐 | ✅ 安全 |


#### 🧠 八、Java 设计哲学思考

`clone()` 被认为是 Java 中设计不太理想的一部分，因为它：

- 不够面向对象（需要强制转型）
- 容易误用（浅拷贝 vs 深拷贝）
- 不符合封装原则（暴露了内部结构）
- 异常处理繁琐（必须 try-catch）

很多框架（如 Spring、Hibernate）都避免使用 `clone()`，而是采用更清晰的方式（如 Builder 模式、序列化等）。

---

#### 📝 总结一句话：

> **`clone()` 是 Java 提供的对象复制机制，默认是浅拷贝，若要深拷贝需手动实现；虽然简单高效，但在实际开发中建议谨慎使用或考虑替代方案。**



如果你还想了解：
- 如何通过 JSON 序列化实现深拷贝？
- Java 中的原型模式（Prototype Pattern）是怎么用 `clone()` 的？
- 如何用反射实现通用克隆工具类？
- `Serializable` + `ByteArrayOutputStream` 实现深拷贝的方法？


:::

## super 和 this

`super`关键字用于访问父类中的成员变量和方法。

`this`关键字用于访问当前对象的成员变量和方法，大部分情况下可以省略。

| 关键字 | 描述 |
| --- | --- |
| `super.成员变量` | 访问父类中的成员变量 |
| `super.方法名()` | 访问父类中的方法 |
| `this.成员变量` | 访问当前对象的成员变量 |
| `this.方法名()` | 访问当前对象的方法 |

```java
public class Ellipse extends Shape{
	private double a; //短轴
	private double b;  //长轴
	public Ellipse() {
		System.out.println("Ellipse()...");
	}	
	public Ellipse(String name) {
		super(name);	//调用Shape的带参构造方法
		System.out.println("Ellipse(String)...");
	}
	public Ellipse(String name, double a, double b) {
		this(name);   //调用本类的重载构造方法
		this.a = a;
		this.b = b;		
		System.out.println("Ellipse(String,double,double)...");
	}
}

```

## 多态

多态是面向对象编程中的一个重要概念，它允许一个类在运行时表现出不同的行为。多态通过继承和接口实现。


