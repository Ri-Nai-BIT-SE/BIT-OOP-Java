public class Reader {
    private final String name;

    // 构造函数
    public Reader(String name) {
        this.name = name;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    // 借书操作
    public void BorrowBook(Book book) {
        if (!book.isIsBorrowed()) {
            book.setIsBorrowed(true);
            System.out.println(String.format("%s 借了%s的《%s》", name, book.getAuthor(), book.getName()));
        } else {
            System.out.println(String.format("抱歉，%s的《%s》已经被借出！", book.getAuthor(), book.getName()));
        }
    }

    // 还书操作
    public void ReturnBook(Book book) {
        if (book.isIsBorrowed()) {
            book.setIsBorrowed(false);
            System.out.println(String.format("%s 还了%s的《%s》", name, book.getAuthor(), book.getName()));
        } else {
            System.out.println(String.format("%s的《%s》尚未被借出，无法还书！", book.getAuthor(), book.getName()));
        }
    }
}
