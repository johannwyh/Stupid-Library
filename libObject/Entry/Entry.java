package libObject.Entry;
public class Entry
{
    private String cardId,dateBorrow,dateDue,supervisior;
    public Entry(String id,String dateB,String dateD,String sup)
    {
        cardId=id;
        dateBorrow=dateB;
        dateDue=dateD;
        supervisior=sup;
    }
    public String getId()
    {
        return cardId;
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
}