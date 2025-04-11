public class Book {
    private final String name;
    private final String author;
    private final double price;
    private boolean isBorrowed;

    // 构造函数，默认未借出
    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.isBorrowed = false; // 默认未借出
    }

    // 构造函数，可以指定是否借出
    public Book(String name, String author, double price, boolean isBorrowed) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.isBorrowed = isBorrowed;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    // 重写 toString 方法，方便打印图书信息
    @Override
    public String toString() {
        return String.join(", ",
                name,
                author,
                String.format("%.1f", price) + "元",
                isBorrowed ? "未还" : "可借"
        );
    }
}
