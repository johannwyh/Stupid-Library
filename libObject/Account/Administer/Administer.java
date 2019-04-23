package libObject.Account.Administer;
import libObject.Account.Account;
public class Administer extends Account // administer object
{
    public Administer(String id,String pwd,String name,String tel)
    {
        super(id,pwd,name,tel);
        setAdmin();
    }
}