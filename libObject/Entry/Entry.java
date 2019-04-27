package libObject.Entry;
public class Entry
{
    private String cardId,bookId,dateBorrow,dateDue,dateReturn,supervisior;
    public Entry(String cid,String bid,String dateB,String dateD,String dateReturn,String sup)
    {
        cardId=cid;
        bookId=bid;
        dateBorrow=dateB;
        dateDue=dateD;
        supervisior=sup;
        this.dateReturn=dateReturn;
    }
    public String getCardId()
    {
        return cardId;
    }
    public String getBookId()
    {
        return bookId;
    }
    public String getDateB()
    {
        return dateBorrow;
    }
    public String getDateD()
    {
        return dateDue;
    }
    public String getSupervisor()
    {
        return supervisior;
    }   
    public String getDateR()
    {
        return dateReturn;
    }
}