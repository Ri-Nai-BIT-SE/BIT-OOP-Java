#import "@preview/bit-undergraduate-thesis-template:0.1.0" as template

#show: template.paper.with(
  subject: "面向对象技术与方法",
  title: "第七周作业",
  title-en: "",
  has_cover: false,
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

= 银行账户取款

== 作业要求
模拟多个用户从同一个银行账户并发取款的场景，使用 `synchronized` 关键字保证线程安全。

== 实现
- `BankAccount.java`: 定义银行账户类，包含余额和同步的取款方法。
- `BankWithdrawTest.java`: 创建多个线程模拟并发取款，并验证账户余额的正确性。

通过在 `BankAccount` 的取款方法上使用 `synchronized` 关键字，确保了每次只有一个线程能够修改账户余额，防止了并发问题。

= 生产者消费者模型

== 作业要求
使用 `wait()`, `notify()`, `notifyAll()` 方法实现经典的生产者消费者模型。

== 实现
- `Warehouse.java`: 仓库类，包含固定容量。使用 `synchronized`, `wait()`, `notifyAll()` 控制生产和消费过程。
- `Producer.java`: 生产者线程，向仓库中添加产品。
- `Consumer.java`: 消费者线程，从仓库中取出产品。
- `ProducerConsumerTest.java`: 创建并启动生产者和消费者线程，模拟并发生产和消费。

`Warehouse` 类中的 `produce()` 和 `consume()` 方法使用了 `synchronized` 来保证互斥访问。当仓库满或空时，线程分别调用 `wait()` 进入等待状态；当状态改变后，通过 `notifyAll()` 唤醒其他等待的线程。 
