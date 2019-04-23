package libObject.Book;
public class Book
{
    private String bookId,bookType,bookTitle,bookPress,bookAuthor;
    private int bookYear,bookNum;
    private float bookPrice;
    public Book(String id,String type,String title,String press,int year,String author,float price,int num)
    {
        bookId=id;
        bookType=type;
        bookTitle=title;
        bookPress=press;
        bookAuthor=author;
        bookYear=year;
        bookPrice=price;
        bookNum=num;
    }
    public String getId()
    {
        return bookId;
    }
    public String getType()
    {
        return bookType;
    }
    public String getTitle()
    {
        return bookTitle;
    }
    public String getPress()
    {
        return bookPress;
    }
    public String getAuthor()
    {
        return bookAuthor;
    }
    public int getYear()
    {
        return bookYear;
    }
    public int getNum()
    {
        return bookNum;
    }
    public float getPrice()
    {
        return bookPrice;
    }
}