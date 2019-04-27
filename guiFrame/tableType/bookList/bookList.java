package guiFrame.tableType.bookList;

import javafx.beans.property.SimpleStringProperty;

public class bookList
{
    //private String bookId,bookType,bookTitle,bookPress,bookAuthor;
    //private int bookYear,bookNum,bookStock;
    //private float bookPrice;
    
    private SimpleStringProperty bookId,bookType,bookTitle,
    bookPress,bookAuthor,bookYear,bookNum,bookStock,bookPrice;
    public bookList(String bookId,String bookType,String bookTitle,String bookPress,
    String bookAuthor,int bookYear,int bookNum,int bookStock,float bookPrice)
    {
        this.bookId=new SimpleStringProperty(bookId);
        this.bookType=new SimpleStringProperty(bookType);
        this.bookTitle=new SimpleStringProperty(bookTitle);
        this.bookPress=new SimpleStringProperty(bookPress);
        this.bookAuthor=new SimpleStringProperty(bookAuthor);
        this.bookYear=new SimpleStringProperty(""+bookYear);
        this.bookNum=new SimpleStringProperty(""+bookNum);
        this.bookStock=new SimpleStringProperty(""+bookStock);
        this.bookPrice=new SimpleStringProperty(""+bookPrice);
    }
    public String getBookId()
    {
        return bookId.get();
    }
    public String getBookType()
    {
        return bookType.get();
    }
    public String getBookTitle()
    {   
        return bookTitle.get();
    }
    public String getBookPress()
    {
        return bookPress.get();
    }
    public String getBookAuthor()
    {
        return bookAuthor.get();
    }
    public String getBookYear()
    {
        return bookYear.get();
    }
    public String getBookNum()
    {
        return bookNum.get();
    }
    public String getBookPrice()
    {
        return bookPrice.get();
    }
    public String getBookStock()
    {
        return bookStock.get();
    }
}