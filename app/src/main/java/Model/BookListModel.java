package Model;

public class BookListModel {

    private String BookId;
    private String BookName;
    private String BookAuth;
    private String Qty;
    private String BookDes;
    private String Price;
    private String BookStatus;

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookAuth() {
        return BookAuth;
    }

    public void setBookAuth(String bookAuth) {
        BookAuth = bookAuth;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getBookDes() {
        return BookDes;
    }

    public void setBookDes(String bookDes) {
        BookDes = bookDes;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getBookStatus() {
        return BookStatus;
    }

    public void setBookStatus(String bookStatus) {
        BookStatus = bookStatus;
    }
}
