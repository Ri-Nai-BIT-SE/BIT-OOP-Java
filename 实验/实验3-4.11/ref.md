### 任务要求分析
根据图片中的描述，我们需要完成以下任务：
1. **用 Java 编程实现三个类**：`Book`、`Reader` 和 `Library`。
2. **在 `main()` 方法中编写测试代码**，完成以下步骤：
   - 创建一个 `Library` 类的实例 `myLittleLibrary`，并预存三本书。
   - 显示图书馆所有图书的信息。
   - 创建一个 `Reader` 类的实例 `oneBeautifulGirl`，让她查找并借阅《Java 程序设计》。
   - 借书后显示当前图书馆所有图书的信息。
   - 还书后再次显示图书馆中图书信息。

### 类的设计
#### 1. `Book` 类
- **属性**：
  - `Name`（书名）
  - `Author`（作者）
  - `Price`（价格）
  - `IsBorrowed`（是否借出）
- **操作**：
  - 暂无显式操作，但需要提供 getter 和 setter 方法。

#### 2. `Reader` 类
- **属性**：
  - `Name`（读者姓名）
- **操作**：
  - `BorrowBook(Book book)`：借书操作，将书的状态标记为已借出。
  - `ReturnBook(Book book)`：还书操作，将书的状态标记为未借出。

#### 3. `Library` 类
- **属性**：
  - `Books`（存储所有图书的列表，类型为 `ArrayList<Book>`）
- **操作**：
  - `FindBook(String BookName)`：根据书名查找同名的书（可能有多本）。
  - `GetAllBooks()`：获取馆藏所有书的详细信息。

### 代码实现

#### 1. `Book` 类
```java
public class Book {
    private String Name;
    private String Author;
    private double Price;
    private boolean IsBorrowed;

    // 构造函数
    public Book(String name, String author, double price) {
        this.Name = name;
        this.Author = author;
        this.Price = price;
        this.IsBorrowed = false; // 默认未借出
    }

    // Getter 和 Setter 方法
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isIsBorrowed() {
        return IsBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        IsBorrowed = isBorrowed;
    }

    // 重写 toString 方法，方便打印图书信息
    @Override
    public String toString() {
        return Name + ", " + Author + ", " + Price + "元, " + (IsBorrowed ? "已借出" : "未借出");
    }
}
```

#### 2. `Reader` 类
```java
import java.util.ArrayList;

public class Reader {
    private String Name;

    // 构造函数
    public Reader(String name) {
        this.Name = name;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    // 借书操作
    public void BorrowBook(Book book) {
        if (!book.isIsBorrowed()) {
            book.setIsBorrowed(true);
            System.out.println(Name + " 借了《" + book.getName() + "》");
        } else {
            System.out.println("抱歉，《" + book.getName() + "》已经被借出！");
        }
    }

    // 还书操作
    public void ReturnBook(Book book) {
        if (book.isIsBorrowed()) {
            book.setIsBorrowed(false);
            System.out.println(Name + " 还了《" + book.getName() + "》");
        } else {
            System.out.println("《" + book.getName() + "》尚未被借出，无法还书！");
        }
    }
}
```

#### 3. `Library` 类
```java
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> Books;

    // 构造函数
    public Library() {
        this.Books = new ArrayList<>();
    }

    // 添加一本书到图书馆
    public void addBook(Book book) {
        Books.add(book);
    }

    // 根据书名查找同名的书
    public ArrayList<Book> FindBook(String BookName) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : Books) {
            if (book.getName().equals(BookName)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // 获取馆藏所有书的详细信息
    public ArrayList<Book> GetAllBooks() {
        return Books;
    }

    // 打印所有图书信息
    public void displayAllBooks() {
        System.out.println("图书馆所有图书信息：");
        for (Book book : Books) {
            System.out.println(book);
        }
    }
}
```

#### 4. `Main` 类
```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. 创建一个 Library 的实例 myLittleLibrary，并预存三本书
        Library myLittleLibrary = new Library();

        // 添加三本书
        Book book1 = new Book("Java 程序设计", "张三著", 45);
        Book book2 = new Book("Java 核心设计", "李四著", 50);
        Book book3 = new Book("Java 程序设计", "王五著", 38);

        myLittleLibrary.addBook(book1);
        myLittleLibrary.addBook(book2);
        myLittleLibrary.addBook(book3);

        // 2. 显示图书馆所有图书的信息
        System.out.println("初始图书信息：");
        myLittleLibrary.displayAllBooks();

        // 3. 创建一个 Reader 类的实例 oneBeautifulGirl
        Reader oneBeautifulGirl = new Reader("oneBeautifulGirl");

        // 查找《Java 程序设计》
        ArrayList<Book> foundBooks = myLittleLibrary.FindBook("Java 程序设计");
        if (!foundBooks.isEmpty()) {
            System.out.println("找到的《Java 程序设计》：");
            for (Book book : foundBooks) {
                System.out.println(book);
            }

            // 借阅第一本《Java 程序设计》
            oneBeautifulGirl.BorrowBook(foundBooks.get(0));
        } else {
            System.out.println("未找到《Java 程序设计》");
        }

        // 4. 借书后显示当前图书馆所有图书的信息
        System.out.println("\n借书后图书信息：");
        myLittleLibrary.displayAllBooks();

        // 5. 还书后再次显示图书馆中图书信息
        oneBeautifulGirl.ReturnBook(foundBooks.get(0));

        System.out.println("\n还书后图书信息：");
        myLittleLibrary.displayAllBooks();
    }
}
```

### 运行结果
假设运行上述代码，输出如下：

```
初始图书信息：
Java 程序设计, 张三著, 45.0元, 未借出
Java 核心设计, 李四著, 50.0元, 未借出
Java 程序设计, 王五著, 38.0元, 未借出

找到的《Java 程序设计》：
Java 程序设计, 张三著, 45.0元, 未借出
Java 程序设计, 王五著, 38.0元, 未借出

oneBeautifulGirl 借了《Java 程序设计》

借书后图书信息：
Java 程序设计, 张三著, 45.0元, 已借出
Java 核心设计, 李四著, 50.0元, 未借出
Java 程序设计, 王五著, 38.0元, 未借出

oneBeautifulGirl 还了《Java 程序设计》

还书后图书信息：
Java 程序设计, 张三著, 45.0元, 未借出
Java 核心设计, 李四著, 50.0元, 未借出
Java 程序设计, 王五著, 38.0元, 未借出
```

### 代码解释
1. **`Book` 类**：表示一本书，包含书名、作者、价格和是否借出的状态。
2. **`Reader` 类**：表示一个读者，可以进行借书和还书操作。
3. **`Library` 类**：表示一个图书馆，可以添加书籍、查找书籍和显示所有书籍。
4. **`Main` 类**：实现了题目要求的测试代码，包括创建图书馆、添加书籍、查找书籍、借书和还书等操作。

### 总结
通过上述代码，我们完成了题目要求的所有功能，包括类的设计和测试代码的实现。每个步骤都清晰地展示了图书管理系统的运作过程。如果有任何疑问或需要进一步修改，请随时告知！
