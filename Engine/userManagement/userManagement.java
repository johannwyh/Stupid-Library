package Engine.userManagement;
import java.sql.*;
import Engine.serverInfo.serverInfo;
import libObject.Account.User.User;
import Engine.Authorization.Authorization;

public class userManagement 
{
    public static boolean insertUser(String id,String pwd,String name,String depart,String type) {
        User tmp;
        tmp = new User(id, pwd, name, depart, type);
        //public User(String id,String pwd,String name,String depart,String type)
        return Authorization.insertAccount(Authorization.currentAccount, tmp);
    }
    public static boolean deleteUser(String id) 
    {
        return Authorization.deleteAccount(Authorization.currentAccount, id);
    }
}