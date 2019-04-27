package Engine.bookManagement;
import Engine.basicOperation.basicOperation;
import Engine.Authorization.Authorization;
import libObject.Book.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

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

    public static String borrowBook(String bookID, String cardID, String borrowDate, String dueDate) {
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
                return "cannot borrow more than one certain book";
            } else {
                sql = "select * from Book where bookID = ?";
                pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                pstmt.setString(1, bookID);
                rs = pstmt.executeQuery();
                if (rs.next() == false) {
                    return "no such book";
                } else if (rs.getInt("stock") == 0) {
                    sql = "select dueDate from entry where bookID = ? and returnDate is null";
                    pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
                    pstmt.setString(1, bookID);
                    rs = pstmt.executeQuery();
                    ArrayList<String> dueDates = new ArrayList<String>();
                    while (rs.next()) {
                        String date = rs.getString("dueDate");
                        dueDates.add(date);
                    }
                    Collections.sort(dueDates);
                    return "no book left, the earlist due date is " + dueDates.get(0);
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

                    return "succeed";
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "error";
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

    // for all String, if not required, use ""
    // for all int and float, if not required, use MIN or MAX
    public static ArrayList<Book> searchBook(String bookID, String type, String name,
                                            String press, int minYear, int maxYear,
                                            String author, float minPrice, float maxPrice) {
        String sql = "select * from Book where";
        ArrayList<String> args = new ArrayList<String>(); 
        sql += " year >= ?"; args.add(Integer.toString(minYear));
        sql += " and year <= ?"; args.add(Integer.toString(maxYear));
        sql += " and price >= ?"; args.add(Float.toString(minPrice));
        sql += " and price <= ?"; args.add(Float.toString(maxPrice));
        if (bookID.equals("") == false) {
            sql += " and bookID = ?";
            args.add(bookID);
        }
        if (type.equals("") == false) {
            sql += " and type = ?";
            args.add(type);
        }
        if (name.equals("") == false) {
            sql += " and title = ?";
            args.add(name);
        }
        if (press.equals("") == false) {
            sql += " and press = ?";
            args.add(press);
        }
        if (author.equals("") == false) {
            sql += " and author = ?";
            args.add(author);
        }

        try {
            Connection conn = basicOperation.getConnection();
            PreparedStatement pstmt = (PreparedStatement)Authorization.currentConnection.prepareStatement(sql);
            ResultSet rs = basicOperation.selectWithArgs(conn, pstmt, args);
            ArrayList<Book> ret = new ArrayList<Book>();
            while (rs.next()) {
                Book tmp = new Book(rs.getString("bookID"),
                                    rs.getString("type"),
                                    rs.getString("title"),
                                    rs.getString("press"),
                                    rs.getInt("year"),
                                    rs.getString("author"),
                                    rs.getFloat("price"),
                                    rs.getInt("num"),
                                    rs.getInt("stock"));
                ret.add(tmp);
            }
            return ret;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
    public static Book getBookById(String id)
    {
        Book temp=null;
        try
        {
            Connection conn = basicOperation.getConnection();
            String sql = "select * from Book where bookID = ?";
            PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ArrayList<String> args = new ArrayList<String>();
            args.add(id);
            ResultSet target=basicOperation.selectWithArgs(conn, pstmt, args);
            if(target.next()) 
            {
                String bookID = target.getString("bookID");
                String type = target.getString("type");
                String title = target.getString("title");
                String press = target.getString("press");
                int year = target.getInt("year");
                String author = target.getString("author");
                float price = target.getFloat("price");
                int num = target.getInt("num");
                int stock = target.getInt("stock");
                temp = new Book(bookID, type, title, press, year, author, price, num, stock);
            }
        }catch(Exception e)
        {
            e.printStackTrace();   
        }
        return temp;
    }
}