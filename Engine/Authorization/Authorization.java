package Engine.Authorization;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import libObject.Account.Account;
import libObject.Account.User.User;
import libObject.Account.Administer.Administer;
public class Authorization
{
    private static ArrayList<Account> account=new ArrayList<Account>();
    public static Account currentAccount;
    public static void init()
    {
       getList();
    }
    public static boolean  checkAuthorization(String id,String pwd)
    {
        for(Account t:account)
        {
            if(t.getId().equals(id)&&t.getPassword(pwd))return true;
        }
        return false;
    }
    public static void logIn(String id)
    {
        for(Account t:account)
        {
            if(t.getId().equals(id))currentAccount=t;
        }
    }
    public static void logOut()
    {
        currentAccount=null;
    }
    private static void getList()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./data/userList.txt"),"UTF-8"));
            String temp=in.readLine();
            String buf[];
            Account tempAccount;
            while(temp!=null)
            {
                System.out.println(temp);
                buf=temp.split(",");
                if(buf[0].equals("A"))
                {
                    tempAccount=new Administer(buf[1], buf[2], buf[3], buf[4]);
                    //public Administer(String id,String pwd,String name,String tel)
                }
                else
                {
                    tempAccount=new User(buf[1], buf[2], buf[3], buf[4],buf[5],buf[6]);
                    //public User(String id,String pwd,String name,String tel,String depart,String type)
                }
                account.add(tempAccount);
                temp=in.readLine();
            }
            in.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}