package Engine.userManagement;
import java.sql.*;
import java.util.ArrayList;
import Engine.serverInfo.serverInfo;
import libObject.Account.Account;
import libObject.Account.User.User;
import libObject.Entry.Entry;
import Engine.Authorization.Authorization;
import Engine.basicOperation.basicOperation;

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
        try {
            String sql = "select * from entry where cardID = ? and returnDate is null";
            PreparedStatement pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            } else if (Authorization.deleteAccount(Authorization.currentAccount, id)) {
                sql = "delete from entry where cardID = ?";
                pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static User searchUser(String id)
    {
        User result=null;

        try
        {
            Connection conn=basicOperation.getConnection();
            String sql="select * from user where cardID = ?";
            PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ArrayList<String> args = new ArrayList<String>();
            args.add(id);
            ResultSet target=basicOperation.selectWithArgs(conn, pstmt, args);
            if(target.next()) 
            {
                String cardID = target.getString("cardID");
                String name = target.getString("name");
                String dept = target.getString("dept");
                String type = target.getString("type");
                String passwd =target.getString("passwd");
                result= new User(cardID, passwd, name, dept, type);
            }
        }catch(Exception e)
        {
            System.out.println("There is something wrong when searching the user...");
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<Entry> getUserRecord(String id)
    {
        ArrayList<Entry> result = new ArrayList<Entry>();
        try
        {
            Connection conn=basicOperation.getConnection();
            String sql="select * from entry where cardID = ?";
            PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ArrayList<String> args = new ArrayList<String>();
            args.add(id);
            ResultSet target=basicOperation.selectWithArgs(conn, pstmt, args);
            while(target.next()) 
            {
                String cardID = target.getString("cardID");
                String bookID = target.getString("bookID");
                String borrowDate = target.getString("borrowDate");
                String returnDate = target.getString("returnDate");
                String adminID =target.getString("adminID");
                String dueDate = target.getString("dueDate");
                result.add(new Entry(cardID,bookID,borrowDate,dueDate,returnDate,adminID));
            }
        }catch(Exception e)
        {
            System.out.println("There is something wrong when getting the record...");
            e.printStackTrace();
        }
        return result;
    }
}