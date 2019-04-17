package guiFrame;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Application;
import javafx.application.Platform;
import guiFrame.loginCtrl.loginCtrl;
import guiFrame.controlManager.controlManager;
import guiFrame.pageCtrl.pageCtrl;
import guiFrame.loginCtrl.loginCtrl;

public class guiFrame extends Application
{
    public static void  trigger(String[] args)
    {
        launch(args); //automatically call the method : start , and pass the Current Stage(window).
    }
    public void start(Stage primaryStage)  
    {
        touch();
        controlManager.setStage(primaryStage);  //set the handle of our window..
        controlManager.pushPage("logIn", "/view/icon.png", "/view/login.fxml", "SPLibrary-logIn");
        controlManager.pushPage("mainPage", "/view/icon.png", "/view/mainPage.fxml", "SPLibrary-mainPage");
        //push all the pages we design into the controlManager.
        controlManager.showPage("logIn"); //initially, we render the login page.
    }
    private void touch()       
    {
        /*check if all the pages' controller is working..  
        Just call their's empty method.. The runtime system wil automatically compile them..*/
        try
        {
            pageCtrl.touch();
            loginCtrl.touch();
        }
        catch(Exception e)
        {
            System.out.println("the controller system is broken.");
            System.exit(0);
        }
    }
}