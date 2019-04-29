package guiFrame.tableType.bookList;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class bookList
{
    //private String bookId,bookType,bookTitle,bookPress,bookAuthor;
    //private int bookYear,bookNum,bookStock;
    //private float bookPrice;
    
    private SimpleStringProperty bookId,bookType,bookTitle,bookPress,bookAuthor;
    private SimpleIntegerProperty bookYear,bookNum,bookStock;
    private SimpleFloatProperty bookPrice;

    public bookList(String bookId,String bookType,String bookTitle,String bookPress,
    String bookAuthor,int bookYear,int bookNum,int bookStock,float bookPrice)
    {
        this.bookId=new SimpleStringProperty(bookId);
        this.bookType=new SimpleStringProperty(bookType);
        this.bookTitle=new SimpleStringProperty(bookTitle);
        this.bookPress=new SimpleStringProperty(bookPress);
        this.bookAuthor=new SimpleStringProperty(bookAuthor);
        this.bookYear=new SimpleIntegerProperty(bookYear);
        this.bookNum=new SimpleIntegerProperty(bookNum);
        this.bookStock=new SimpleIntegerProperty(bookStock);
        this.bookPrice=new SimpleFloatProperty(bookPrice);
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
    public int getBookYear()
    {
        return bookYear.get();
    }
    public int getBookNum()
    {
        return bookNum.get();
    }
    public float getBookPrice()
    {
        return bookPrice.get();
    }
    public int getBookStock()
    {
        return bookStock.get();
    }
}