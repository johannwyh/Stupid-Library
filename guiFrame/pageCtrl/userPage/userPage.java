package guiFrame.pageCtrl.userPage;
import guiFrame.controlManager.controlManager;
import guiFrame.pageCtrl.pageCtrl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
    }
    public void deleteUser()
    {
        System.out.println("want to delete user : "+cardId.getText());
    }
}