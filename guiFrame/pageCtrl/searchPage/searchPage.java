package guiFrame.pageCtrl.searchPage;
import java.io.File;

import guiFrame.pageCtrl.pageCtrl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import guiFrame.controlManager.controlManager;

public class searchPage extends pageCtrl
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
    private TextField bookYearLB;
    @FXML
    private TextField bookYearUB;
    @FXML
    private TextField bookAuthor;
    @FXML
    private TextField bookPriceLB;
    @FXML
    private TextField bookPriceUB;
    static private int yearLB,yearUB;
    static private float priceLB,priceUB;
    static private String id,category,name,press,author;
    static private final int small=-99999999;
    static private final int big=99999999;
    public void getSearchResult()
    {
        String temp;
        try
        {
            temp=bookYearLB.getText();
            yearLB=Integer.parseInt(temp);
        }catch(Exception e)
        {
            yearLB=small;
        }
        try
        {
            temp=bookYearUB.getText();
            yearUB=Integer.parseInt(temp);
        }catch(Exception e)
        {
            yearUB=big;
        }
        try
        {
            temp=bookPriceLB.getText();
            priceLB=Float.parseFloat(temp);
        }catch(Exception e)
        {
            priceLB=small;
        }
        try
        {
            temp=bookPriceUB.getText();
            priceUB=Float.parseFloat(temp);
        }catch(Exception e)
        {
            priceUB=big;
        }
        id=bookId.getText();
        category=bookCategory.getText();
        name=bookName.getText();
        press=bookPress.getText();
        author=bookAuthor.getText();
        System.out.println("Info : "+getStringInfo());
        return;
    }

    private String getStringInfo()
    {
        StringBuffer x= new StringBuffer();
        x.append("( "+id+" , ");
        x.append(category+" , ");
        x.append(name+" , ");
        x.append(press+" , ");
        x.append(yearLB+" ~ "+yearUB+" , ");
        x.append(author+" , ");
        x.append(priceLB+" ~ "+priceUB+" ) ");
        return x.toString();
    }
}