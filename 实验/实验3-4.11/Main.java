import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // (1) 创建一个 Library 的实例 myLittleLibrary，其中预存有以下三本书
        Library myLittleLibrary = new Library();
        Book book1 = new Book("Java程序设计", "张三著", 45.0);
        Book book2 = new Book("Java核心设计", "李四著", 50.0);
        Book book3 = new Book("Java程序设计", "王五著", 38.0, true);
        myLittleLibrary.addBook(book1);
        myLittleLibrary.addBook(book2);
        myLittleLibrary.addBook(book3);

        // (2) 显示图书馆所有图书的信息
        System.out.println("--- 初始图书馆图书信息 ---");
        myLittleLibrary.displayAllBooks();
        System.out.println();

        // (3) 创建一个 Reader 类的实例 oneBeautifulGril
        Reader oneBeautifulGril = new Reader("oneBeautifulGril");

        // (4) oneBeautifulGril 借了张三著的那一本书时，显示当前图书馆所有图书的信息
        System.out.println("--- oneBeautifulGril 查找并借阅《Java程序设计》 ---");
        ArrayList<Book> searchResult = myLittleLibrary.FindBook("Java程序设计");
        if (!searchResult.isEmpty()) {
            // 找到张三著的那本书
            Book bookToBorrow = null;
            for (Book book : searchResult) {
                if (book.getAuthor().equals("张三著")) {
                    bookToBorrow = book;
                    break;
                }
            }
            if (bookToBorrow != null) {
                oneBeautifulGril.BorrowBook(bookToBorrow);
                System.out.println("\n--- 借阅后图书馆图书信息 ---");
                myLittleLibrary.displayAllBooks();
            } else {
                System.out.println("未找到张三著的《Java程序设计》");
            }
        } else {
            System.out.println("图书馆没有《Java程序设计》这本书。");
        }
        System.out.println();

        // (5) oneBeautifulGril 把书还了，再次显示图书馆中图书信息
        System.out.println("--- oneBeautifulGril 还书 ---");
        // 假设我们知道她借的是 book1
        oneBeautifulGril.ReturnBook(book1);
        System.out.println("\n--- 还书后图书馆图书信息 ---");
        myLittleLibrary.displayAllBooks();
    }
} 
