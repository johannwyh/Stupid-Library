package libObject.Account.User;
import libObject.Account.Account;
public class User extends Account       //user object
{
    public User(String id,String pwd,String name,String tel,String depart,String type)
    {
        super(id,pwd,name,tel);
        this.department=depart;
        this.userType=type;
    }
    public User(String id,String pwd,String name,String depart,String type)
    {
        super(id);
        this.setName(name);
        this.setPassword(pwd);
        this.department=depart;
        this.userType=type;
        this.department=depart;
        this.userType=type;
    }
    public User(String id, String name, String depart, String type) {
        super(id);
        this.setPassword("");
        this.setName(name);
        this.department = depart;
        this.userType = type;
    }
    private String department;
    private String userType;
    public String getDepartment()
    {
        return department;
    }
    public String getType()
    {
        return userType;
    }
}