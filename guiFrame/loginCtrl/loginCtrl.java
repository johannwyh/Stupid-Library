package guiFrame.loginCtrl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import guiFrame.controlManager.controlManager;
import Engine.Authorization.Authorization;
public class loginCtrl implements Initializable
{
    @FXML
    private Button login;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    static private String stdUsername="administer";
    static private String stdPassword="admin";

    private Alert message = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        
    }
    static public void touch()
    {
        
    }
    public void toExit()
    {
        System.exit(0);
        return;
    }
    public void showHelpInfo()
    {
        //System.exit(0);
        return;
    }
    public void toLogin()
    {
        String inUser = username.getText();
        String inPwd = password.getText();
        if(Authorization.checkAuthorization(inUser, inPwd))
        {
            Authorization.logIn(inUser);
            loginSuccessfully();
        }
        else
        {
            wrongPasswd();
        }
        return;
    }
    private void loginSuccessfully()
    {   
        controlManager.showPage("mainPage");
    }
    private void wrongPasswd()
    {
        message.setTitle("login failed");
        message.setHeaderText("SPLibrary");
        message.setContentText("incorrect password");
        message.show();
    }
}