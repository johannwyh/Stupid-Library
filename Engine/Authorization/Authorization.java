package Engine.Authorization;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import Engine.basicOperation.basicOperation;
import java.util.ArrayList;
import libObject.Account.Account;
import libObject.Account.User.User;
import libObject.Account.Administer.Administer;
import Engine.serverInfo.*;
import java.sql.*;

public class Authorization
{
    private static ArrayList<Account> account=new ArrayList<Account>();
    public static Account currentAccount;
    public static Connection currentConnection;

    public static void printAccount() {
        System.out.println("========= Start of Account =========");
        for (Account t:account)
            System.out.println(t.getId());
        System.out.println("========= End   of Account =========");
    }
    
    public static void init()
    {
        serverInfo.init("./data/admin.txt");
        dragFromBackend();
        importFromList("./data/userList.txt");   
        printAccount();
    }

    private static boolean insertUser(User t) {
        if (existID(t.getId()))
            return false;
        else {
            account.add((Account)t);
            try {
                Connection conn = null;
                Class.forName(serverInfo.JDBC_DRIVER);
                conn = DriverManager.getConnection(serverInfo.getUrl());
                String sql = "insert into user (cardID, name, dept, type, passwd) values(?, ?, ?, ?, ?)";
                PreparedStatement pstmt;
                System.out.println("##### Inserting User " + t.getId());
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                pstmt.setString(1, t.getId());
                pstmt.setString(2, t.getName());
                pstmt.setString(3, t.getDepartment());
                pstmt.setString(4, t.getType());
                pstmt.setString(5, t.getPass());
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private static boolean deleteUser(User t) {
        if (existID(t.getId()) == false)
            return false;
        else {
            account.remove((Account)t);
            try {
                Connection conn = null;
                Class.forName(serverInfo.JDBC_DRIVER);
                conn = DriverManager.getConnection(serverInfo.getUrl());
                String sql = "delete from user where cardID = ?";
                PreparedStatement pstmt;
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                pstmt.setString(1, t.getId());
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static boolean insertAdmin(Administer t) {
        if (existID(t.getId()))
            return false;
        else {
            account.add((Account)t);
            try {
                Connection conn = null;
                Class.forName(serverInfo.JDBC_DRIVER);
                conn = DriverManager.getConnection(serverInfo.getUrl());
                String sql = "insert into admin (id, passwd, name, tel) values(?, ?, ?, ?)";
                PreparedStatement pstmt;
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                pstmt.setString(1, t.getId());
                pstmt.setString(2, t.getPass());
                pstmt.setString(3, t.getName());
                pstmt.setString(4, t.getTel());
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static boolean deleteAdmin(Administer t) {
        if (existID(t.getId()) == false)
            return false;
        else {
            account.remove((Account)t);
            try {
                Connection conn = null;
                Class.forName(serverInfo.JDBC_DRIVER);
                conn = DriverManager.getConnection(serverInfo.getUrl());
                String sql = "delete from admin where id = ?";
                PreparedStatement pstmt;
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                pstmt.setString(1, t.getId());
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public static boolean insertAccount(Account worker, Account acct) {
        if (acct.isAdmin() == true || worker.isAdmin() == false) return false;
        return insertUser((User)acct);
    }

    public static boolean deleteAccount(Account worker, String id) {
        if (worker.isAdmin() == false) return false;
        for(Account t:account) {
            if (t.isAdmin()) continue;
            if (t.getId().equals(id))
                return deleteUser((User)t);
        }
        return false;
    }

    public static boolean existID(String ID) {
        for(Account t:account) {
            if (t.getId().equals(ID)) return true;
        }
        return false;
    }

    private static void dragFromBackend() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(serverInfo.JDBC_DRIVER);
            System.out.println("check connection 1");
            conn = DriverManager.getConnection(serverInfo.getUrl());
            System.out.println("check connection 2");
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        //System.out.println("START DRUG");
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "select * from user";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String cardID = rs.getString("cardID");
                String name = rs.getString("name");
                String dept = rs.getString("dept");
                String type = rs.getString("type");
                String passwd = rs.getString("passwd");
                if (existID(cardID) == true) continue;
                //public User(String id,String pwd,String name,String depart,String type)
                User tmp = new User(cardID, passwd, name, dept, type);
                account.add(tmp);
            }
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "select * from admin";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String id = rs.getString("id");
                String passwd = rs.getString("passwd");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                if (existID(id) == true) continue;
                Administer tmp = new Administer(id, passwd, name, tel);
                account.add(tmp);
            }
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("END DRUG");
    }

    public static boolean checkAuthorization(String id,String pwd)
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
            if(t.getId().equals(id)) {
                currentAccount=t;
                currentConnection = basicOperation.getConnection();
            }
        }

    }

    public static void logOut()
    {
        currentAccount=null;
        try {
            currentConnection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public static void importFromList(String filePath)
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
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
                }
                else
                {
                    tempAccount=new User(buf[1], buf[2], buf[3], buf[4],buf[5],buf[6]);
                }
                if (existID(tempAccount.getId()) == false) {
                    if (tempAccount.isAdmin())
                        insertAdmin((Administer)tempAccount);
                    else
                        insertUser((User)tempAccount);
                }
                temp=in.readLine();
            }
            in.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}