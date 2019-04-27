package guiFrame.pageCtrl.boOrRePage;
import java.util.Optional;

import Engine.basicOperation.basicOperation;
import guiFrame.controlManager.controlManager;
import guiFrame.pageCtrl.pageCtrl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import Engine.bookManagement.bookManagement;

public class boOrRePage extends pageCtrl
{
    @FXML
    private TextField cardId;
    @FXML
    private TextField bookId;
    @FXML
    private TextField borrowDate;
    @FXML
    private TextField dueDate;
    @FXML
    private TextField returnDate;
    
    public void showCardInfo()
    {
        System.out.println("card ID : "+cardId.getText());
    }
    public void showBookInfo()
    {
        System.out.println("book ID : "+bookId.getText());
    }
    public void toBorrow()
    {
        String Bid=bookId.getText();
        String Cid=cardId.getText();
        String bDate=borrowDate.getText();
        String dDate=dueDate.getText();
        String message="User with ID "+Cid+" want to borrow book with ID "+Bid+"\n"+
                    "borrowDate="+bDate+"\n"+"dueDate="+dDate;
        //System.out.println("User with ID "+Cid+" want to borrow book with ID "+Bid);

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,message);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            boolean state = bookManagement.borrowBook(Bid,Cid,bDate, dDate);
            if(state)message="Done!";
            else message="Failed! Please Check Again! ";
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
        String rDate=returnDate.getText();

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