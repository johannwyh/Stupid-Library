package guiFrame.pageCtrl.userPage;
import guiFrame.controlManager.controlManager;
import guiFrame.pageCtrl.pageCtrl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import Engine.Authorization.Authorization;
import Engine.userManagement.userManagement;

public class userPage extends pageCtrl
{
    @FXML
    private TextField cardId;
    public void showCardInfo()
    {
        System.out.println("card ID : "+cardId.getText());
    }
    public void insertUser()
    {
        System.out.println("want to insert user : "+cardId.getText());
        userManagement.insertUser(cardId.getText(), "", "", "", "", "");
        Authorization.printAccount();
    }
    public void deleteUser()
    {
        System.out.println("want to delete user : "+cardId.getText());
        userManagement.deleteUser(cardId.getText());
        Authorization.printAccount();
    }
}