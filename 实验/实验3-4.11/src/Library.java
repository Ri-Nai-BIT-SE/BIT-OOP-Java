import java.util.ArrayList;

public class Library {
    private final ArrayList<Book> books;

    // 构造函数
    public Library() {
        this.books = new ArrayList<>();
    }

    // 添加一本书到图书馆
    public void addBook(Book book) {
        books.add(book);
    }

    // 根据书名查找同名的书
    public ArrayList<Book> FindBook(String BookName) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().equals(BookName)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // 获取馆藏所有书的详细信息
    public ArrayList<Book> GetAllBooks() {
        return books;
    }

    // 打印所有图书信息
    public void displayAllBooks() {
        System.out.println("图书馆所有图书信息：");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
