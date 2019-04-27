package guiFrame.pageCtrl.importPage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;

import guiFrame.pageCtrl.pageCtrl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        String message;
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
            Book tmp = new Book(id, category, name, press, year, author, price, num,num);
            message = "Import Books : \n"+tmp.toString();
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,message);
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK)
            {
                bookManagement.importBook(tmp);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Result");
                info.setHeaderText("SPLibrary");
                info.setContentText("Done!");
                info.show();
            }
            //System.out.println("Info : "+getStringInfo());
        }catch(Exception e)
        {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Result");
            info.setHeaderText("SPLibrary");
            info.setContentText("Invalid Format! Check Again!");
            info.show();
        }
        return;
    }
    public void toImportFromCSV()
    {
        File myFile=fileChooser.showOpenDialog(controlManager.getStage());
        String buf[];
        ArrayList<Book> bookList = new ArrayList<Book>();
        if(myFile!=null)
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(myFile.getAbsolutePath()),"UTF-8"));
                String temp=in.readLine();
                boolean valid=true;
                while(temp!=null)
                {
                    if(temp.trim().equals(""))
                    {
                        temp=in.readLine();
                        continue;
                    }
                    buf=temp.split(",");
                    if(buf.length!=8)
                    {
                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                        info.setTitle("Result");
                        info.setHeaderText("SPLibrary");
                        info.setContentText("Invalid Format! Check Again!");
                        info.show();
                        valid=false;
                        break;
                    }
                    try
                    {
                        for(int i=0;i<8;i++)
                        {
                            buf[i]=buf[i].trim();
                        }
                        year=Integer.parseInt(buf[0]);
                        num=Integer.parseInt(buf[1]);
                        price=Float.parseFloat(buf[2]);
                        id=buf[3];
                        category=buf[4];
                        name=buf[5];
                        press=buf[6];
                        author=buf[7];
                    }catch(Exception e)
                    {
                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                        info.setTitle("Result");
                        info.setHeaderText("SPLibrary");
                        info.setContentText("Invalid Format! Check Again!");
                        info.show();
                        valid=false;
                        break;
                    }
                    bookList.add(new Book(id, category, name, press, year, author, price, num, num));
                    temp=in.readLine();
                }
                in.close();
                if(valid)
                {
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Import all the books in the list ? ");
                    Optional<ButtonType> result = confirmation.showAndWait();
                    if(result.isPresent() && result.get() == ButtonType.OK)
                    {
                        for(Book k : bookList)bookManagement.importBook(k);
                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                        info.setTitle("Result");
                        info.setHeaderText("SPLibrary");
                        info.setContentText("Done!");
                        info.show();
                    }
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
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