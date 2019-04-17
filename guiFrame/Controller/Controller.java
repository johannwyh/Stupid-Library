package guiFrame.Controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller implements Initializable
{
    @FXML
    private Button login;
    private TextField username;
    private TextField password;
    @Override
    public void initialize(URL location, ResourceBundle resources)
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
}