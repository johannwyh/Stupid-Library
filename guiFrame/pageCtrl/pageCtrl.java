package guiFrame.pageCtrl;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class pageCtrl implements Initializable
{
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
}