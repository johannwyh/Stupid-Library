package guiFrame.tableType.libRecord;
import javafx.beans.property.SimpleStringProperty;
public class libRecord
{
    private SimpleStringProperty cardId,bookId,dateBorrow,dateDue,dateReturn,supervisior;
    public libRecord(String cardId,String bookId,String dateBorrow,String dateDue,String dateReturn,String supervisior)
    {
        this.cardId=new SimpleStringProperty(cardId);
        this.bookId=new SimpleStringProperty(bookId);
        this.dateBorrow=new SimpleStringProperty(dateBorrow);
        this.dateDue=new SimpleStringProperty(dateDue);
        this.dateReturn=new SimpleStringProperty(dateReturn);
        this.supervisior=new SimpleStringProperty(supervisior);
    }
    public String getCardId()
    {
        return this.cardId.get();
    }
    public String getBookId()
    {
        return this.bookId.get();
    }
    public String getDateBorrow()
    {
        return this.dateBorrow.get();
    }
    public String getDateDue()
    {
        return this.dateDue.get();
    }
    public String getDateReturn()
    {
        return this.dateReturn.get();
    }
    public String getSupervisior()
    {
        return this.supervisior.get();
    }
}