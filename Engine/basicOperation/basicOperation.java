package Engine.basicOperation;
import java.sql.*;
import libObject.Account.*;
import Engine.serverInfo.*;
import java.util.ArrayList;

public class basicOperation {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(serverInfo.JDBC_DRIVER);
            conn = (Connection)DriverManager.getConnection(serverInfo.getUrl());
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ResultSet select(Connection conn, String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        //System.out.println("=====================");
        //System.out.println(sql);
        //System.out.println("=====================");
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectWithArgs(Connection conn, PreparedStatement pstmt, ArrayList<String> args) {
        ResultSet rs = null;
        try {
            //System.out.println(args);
            for(int i = 0; i < args.size(); i++) 
            {
                pstmt.setString(i + 1, args.get(i));
            }
            rs = pstmt.executeQuery();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public static void update(Connection conn, String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateWithArgs(Connection conn, PreparedStatement pstmt, ArrayList<String> args) {
        try {
            for(int i = 0; i < args.size(); i++) {
                pstmt.setString(i + 1, args.get(i));
            }
            pstmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}