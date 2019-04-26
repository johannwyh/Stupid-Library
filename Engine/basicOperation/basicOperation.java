package Engine.basicOperation;
import java.sql.*;
import Engine.Authorization.*;
import libObject.Account.*;
import Engine.serverInfo.*;
import java.util.ArrayList;

public class basicOperation {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection)DriverManager.getConnection(serverInfo.DB_URL, serverInfo.USER, serverInfo.PASS);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static ResultSet select(String sql) {
        Connection conn = getConnection();
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
        return rs;
    }
    public static ResultSet selectWithArgs(String sql, ArrayList<String> args) {
        Connection conn = getConnection();
        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            for(int i = 0; i < args.size(); i++) {
                pstmt.setString(i + 1, args.get(i));
            }
            rs = pstmt.executeQuery();
            pstmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}