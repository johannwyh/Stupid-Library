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

    public static boolean borrowBook(String bookID, String cardID, String borrowDate, String dueDate) {
        PreparedStatement pstmt;
        String sql;
        ResultSet rs;
        ArrayList<String> args = new ArrayList<String>();
        try {
            sql = "select * from entry where cardID = ? and bookID = ? and returnDate is null";
            pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
            pstmt.setString(1, cardID);
            pstmt.setString(2, bookID);
            rs = pstmt.executeQuery();
            if (rs.next() != false) {
                return false;
            } else {
                sql = "select * from Book where bookID = ?";
                pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                pstmt.setString(1, bookID);
                rs = pstmt.executeQuery();
                if (rs.next() == false) {
                    return false;
                } else if (rs.getInt("stock") == 0) {
                    return false;
                } else {
                    sql = "insert into entry (cardID, bookID, borrowDate, dueDate, returnDate, adminID) values(?, ?, ?, ?, ?, ?)";
                    pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                    pstmt.setString(1, cardID);
                    pstmt.setString(2, bookID);
                    pstmt.setString(3, borrowDate);
                    pstmt.setString(4, dueDate);
                    pstmt.setString(5, null);
                    pstmt.setString(6, Authorization.currentAccount.getId());
                    pstmt.executeUpdate();

                    sql = "update book set stock = stock - 1 where bookID = ?";
                    pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                    pstmt.setString(1, bookID);
                    pstmt.executeUpdate();

                    return true;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }
    
    public static boolean returnBook(String bookID, String cardID, String returnDate) {
        PreparedStatement pstmt;
        String sql;
        ResultSet rs;
        ArrayList<String> args = new ArrayList<String>();
        try {
            sql = "select * from entry where bookID = ? and cardID = ? and returnDate is null";
            pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
            pstmt.setString(1, bookID);
            pstmt.setString(2, cardID);
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                return false;
            } else {
                sql = "update entry set returnDate = ? where bookID = ? and cardID = ? and returnDate is null";
                pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                pstmt.setString(1, returnDate);
                pstmt.setString(2, bookID);
                pstmt.setString(3, cardID);
                pstmt.executeUpdate();

                sql = "update book set stock = stock + 1 where bookID = ?";
                pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                pstmt.setString(1, bookID);
                pstmt.executeUpdate();
                
                return true;
            } 
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}