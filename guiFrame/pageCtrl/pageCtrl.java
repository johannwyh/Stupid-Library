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
import guiFrame.controlManager.controlManager;

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
        System.out.println("exit!!");
        System.exit(0);
        return;
    }
    public void toHelp()
    {
        return;
    }
    public void toMainPage()
    {
        controlManager.showPage("mainPage");
    }
    public void toImport()
    {
        controlManager.showPage("importPage");
    }
    public void toSearch()
    {
        controlManager.showPage("searchPage");
    }
    public void toBoOrRe()
    {
        controlManager.showPage("boOrRePage");
    }
    public void toUserManagement()
    {
        controlManager.showPage("cardManagementPage");
    }
    public void toLogOut()
    {
        System.out.println("logOut!!");
        controlManager.showPage("logIn");
    }
}