package guiFrame.tableType.libRecord;
import javafx.beans.property.SimpleStringProperty;
public class libRecord
{
    public SimpleStringProperty cardId,bookId,dateBorrow,dateDue,dateReturn,supervisior;
    public libRecord(String cardId,String bookId,String dateBorrow,String dateDue,String dateReturn,String supervisior)
    {
        this.cardId=new SimpleStringProperty(cardId);
        this.bookId=new SimpleStringProperty(bookId);
        this.dateBorrow=new SimpleStringProperty(dateBorrow);
        this.dateDue=new SimpleStringProperty(dateDue);
        this.dateReturn=new SimpleStringProperty(dateReturn);
        this.supervisior=new SimpleStringProperty(supervisior);
    }
}