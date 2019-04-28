package guiFrame.pageCtrl.boOrRePage;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Engine.basicOperation.basicOperation;
import guiFrame.controlManager.controlManager;
import guiFrame.pageCtrl.pageCtrl;
import guiFrame.tablePage.recordPage.recordPage;
import guiFrame.tableType.libRecord.libRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import libObject.Book.Book;
import libObject.Entry.Entry;
import Engine.bookManagement.bookManagement;
import Engine.userManagement.userManagement;

public class boOrRePage extends pageCtrl
{
    @FXML
    private TextField cardId;
    @FXML
    private TextField bookId;
    @FXML
    private TextField borrowDateY;
    @FXML
    private TextField borrowDateM;
    @FXML
    private TextField borrowDateD;
    @FXML
    private TextField dueDateY;
    @FXML
    private TextField dueDateM;
    @FXML
    private TextField dueDateD;
    @FXML
    private TextField returnDateY;
    @FXML
    private TextField returnDateM;
    @FXML
    private TextField returnDateD;

    private TableColumn cidCol = new TableColumn("cardId");
    private TableColumn bidCol = new TableColumn("bookId");
    private TableColumn dbCol = new TableColumn("dateBorrow");
    private TableColumn ddCol = new TableColumn("dateDue");
    private TableColumn drCol = new TableColumn("dateReturn");
    private TableColumn spCol = new TableColumn("supervisior");
    private Stage pane = new Stage();
    private Label label= new Label();
    private TableView<libRecord> table = new TableView<libRecord>();

    public void showCardInfo()
    {
        String id = cardId.getText();
        ArrayList<Entry> result = userManagement.getUserRecord(id);
        ObservableList<libRecord> data = FXCollections.observableArrayList();
        for(Entry k : result)
        {
            data.add(new libRecord(k.getCardId(),k.getBookId(),k.getDateB(),k.getDateD(),k.getDateR(),k.getSupervisor()));
        }
        recordPage.show(id,data);
    }
    public void showBookInfo()
    {
        String id = bookId.getText();
        Book result = bookManagement.getBookById(id);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        String message;
        if(result==null)
        {
            message="No Such Book ! Check It Again!";
        }
        else
        {
            message=result.toString();
        }
        info.setTitle("Result");
        info.setHeaderText("Information Of Book : "+id);
        info.setContentText(message);
        info.show();
    }
    public void toBorrow()
    {
        String Bid=bookId.getText();
        String Cid=cardId.getText();
        String bdY=borrowDateY.getText(),bdM=borrowDateM.getText(),bdD=borrowDateD.getText();
        while(bdY.length()<2)bdY="0"+bdY;
        while(bdM.length()<2)bdM="0"+bdM;
        while(bdD.length()<2)bdD="0"+bdD;
        String ddY=dueDateY.getText(),ddM=dueDateM.getText(),ddD=dueDateD.getText();
        while(ddY.length()<2)ddY="0"+ddY;
        while(ddM.length()<2)ddM="0"+ddM;
        while(ddD.length()<2)ddD="0"+ddD;
        String bDate=bdY+"/"+bdM+"/"+bdD;
        String dDate=ddY+"/"+ddM+"/"+ddD;
        String message="User with ID "+Cid+" want to borrow book with ID "+Bid+"\n"+
                    "borrowDate="+bDate+"\n"+"dueDate="+dDate;
        //System.out.println("User with ID "+Cid+" want to borrow book with ID "+Bid);

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,message);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            String state = bookManagement.borrowBook(Bid,Cid,bDate, dDate);
            message = state;
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Result");
            info.setHeaderText("SPLibrary");
            info.setContentText(message);
            info.show();
        }
    }
    public void toReturn()
    {
        String Bid=bookId.getText();
        String Cid=cardId.getText();
        String rdY=returnDateY.getText(),rdM=returnDateM.getText(),rdD=returnDateD.getText();
        while(rdY.length()<2)rdY="0"+rdY;
        while(rdM.length()<2)rdM="0"+rdM;
        while(rdD.length()<2)rdD="0"+rdD;
        String rDate=rdY+"/"+rdM+"/"+rdD;

        String message="User with ID "+Cid+" want to return book with ID "+Bid+"\n"+
                    "returnDate="+rDate;

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,message);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            boolean state = bookManagement.returnBook(Bid, Cid, rDate);
            if(state)message="Done!";
            else message="Failed! Please Check Again! ";
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Result");
            info.setHeaderText("SPLibrary");
            info.setContentText(message);
            info.show();
        }
    }

}