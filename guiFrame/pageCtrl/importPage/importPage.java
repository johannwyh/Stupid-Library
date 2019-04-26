package guiFrame.pageCtrl.importPage;
import java.io.File;

import guiFrame.pageCtrl.pageCtrl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import guiFrame.controlManager.controlManager;
import libObject.Book.Book;
import Engine.bookManagement.bookManagement;

public class importPage extends pageCtrl
{
    @FXML
    private TextField bookId;
    @FXML
    private TextField bookCategory;
    @FXML
    private TextField bookName;
    @FXML
    private TextField bookPress;
    @FXML
    private TextField bookYear;
    @FXML
    private TextField bookAuthor;
    @FXML
    private TextField bookPrice;
    @FXML
    private TextField bookNum;
    static private int year,num;
    static private float price;
    static private String id,category,name,press,author;
    static private FileChooser fileChooser=new FileChooser();
    public void toSubmitImport()
    {
        try
        {
            year=Integer.parseInt(bookYear.getText());
            num=Integer.parseInt(bookNum.getText());
            price=Float.parseFloat(bookPrice.getText());
            id=bookId.getText();
            category=bookCategory.getText();
            name=bookName.getText();
            press=bookPress.getText();
            author=bookAuthor.getText();
            Book tmp = new Book(id, category, name, press, year, author, price, num, num);
            bookManagement.importBook(tmp);
            System.out.println("Info : "+getStringInfo());
        }catch(Exception e)
        {
            System.out.println("Invalid info format");
        }
        return;
    }
    public void toImportFromCSV()
    {
        File myFile=fileChooser.showOpenDialog(controlManager.getStage());
    }
    private String getStringInfo()
    {
        StringBuffer x= new StringBuffer();
        x.append("( "+id+" , ");
        x.append(category+" , ");
        x.append(name+" , ");
        x.append(press+" , ");
        x.append(year+" , ");
        x.append(author+" , ");
        x.append(price+" , ");
        x.append(num+" ) ");
        return x.toString();
    }
}