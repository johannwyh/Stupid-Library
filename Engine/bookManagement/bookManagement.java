package Engine.bookManagement;
import Engine.basicOperation.basicOperation;
import Engine.Authorization.Authorization;
import libObject.Book.Book;
import java.sql.*;
import java.util.ArrayList;

public class bookManagement {
    public static void importBook(Book b) {
        PreparedStatement pstmt;
        String sql;
        ResultSet rs;
        ArrayList<String> args = new ArrayList<String>();
        try {
            args.add(b.getId());
            sql = "select * from Book where bookID = ?";
            pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
            rs = basicOperation.selectWithArgs(Authorization.currentConnection, pstmt, args);
            if (rs.next() == false) {
                sql = "insert into Book (bookID, type, title, press, year, author, price, num, stock) values(?,?,?,?,?,?,?,?,?)";
                pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                pstmt.setString(1, b.getId());
                pstmt.setString(2, b.getType());
                pstmt.setString(3, b.getTitle());
                pstmt.setString(4, b.getPress());
                pstmt.setInt(5, b.getYear());
                pstmt.setString(6, b.getAuthor());
                pstmt.setFloat(7, b.getPrice());
                pstmt.setInt(8, b.getNum());
                pstmt.setInt(9, b.getStock());
                pstmt.executeUpdate();
                pstmt.close();
            } else {
                sql = "update Book set num = num + ?, stock = stock + ? where bookID = ?";
                pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                pstmt.setInt(1, b.getNum());
                pstmt.setInt(2, b.getStock());
                pstmt.setString(3, b.getId());
                pstmt.executeUpdate();
                pstmt.close();
            }
            pstmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}