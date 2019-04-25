package libObject.Account;
public class Account         //root class of account. Containing basic info of an account.
{
    private String accountId,accountPassword,accountName,accountTel;
    private boolean isAdmin=false;
    public String getId()
    {
        return accountId;
    }
    public boolean getPassword(String x)
    {
        return accountPassword.equals(x);
    }
    public String getName()
    {
        return accountName;
    }
    public String getTel()
    {
        return accountTel;
    }
    public boolean isAdmin()
    {
        return isAdmin;
    }
    public String getPass()
    {
        return accountPassword;
    }
    public void setId(String x)
    {
        accountId=x;
    }
    public void setPassword(String x)
    {
        accountPassword=x;
    }

    public void setName(String x)
    {
        accountName=x;
    }
    public void setTel(String x)
    {
        accountName=x;
    }
    public void setAdmin()
    {
        isAdmin=true;
    }
    public Account(String id,String pwd,String name,String tel)
    {
        accountId=id;
        accountPassword=pwd;
        accountName=name;
        accountTel=tel;
    }
    public Account(String id, String pwd) {
        accountId = id;
        accountPassword = pwd;
    }
    public Account(String id) {
        accountId = id;
    }
}