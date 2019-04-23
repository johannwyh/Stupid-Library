package guiFrame.controlManager;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Engine.Authorization.Authorization;

public class controlManager //Manage the page switching.
{
    static private HashMap<String,page> myPage = new HashMap<String,page>();
    static private Stage currentStage;
    static public void setStage(Stage myStage)   //set the stage.
    {
        currentStage=myStage;
    }
    static public Stage getStage()
    {
        return currentStage;
    }
    static public void pushPage(String pageName,String iconPath,String resourcePath,String title)  //insert page
    {
        myPage.put(pageName, new page(iconPath,resourcePath,title));
    } 
    static public void showPage(String pageName)        //load the page
    {
        if(myPage.containsKey(pageName)==false)
        {
            System.out.println("FATAL ERROR : NO SUCH PAGE");
            System.exit(0);
        }
        else
        {
            if(pageName.equals("searchPage")==false&&
            pageName.equals("mainPage")==false&&
            pageName.equals("logIn")==false&&
            Authorization.currentAccount.isAdmin()==false)
            {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("No Authorization");
                message.setHeaderText("SPLibrary");
                message.setContentText("Insure that you have the acess right.");
                message.show();
            }
            else myPage.get(pageName).show(currentStage);
        }
    }
}
class page          //page object.
{
    String iconPath;
    String resourcePath;
    String title;
    public page(String iconPath,String resourcePath,String title)
    {
        this.iconPath=iconPath;
        this.resourcePath=resourcePath;
        this.title=title;
    }
    public void show(Stage primaryStage)     //load the page in the target stage.
    {
        try
        {            
            EventHandler<WindowEvent> handler = new EventHandler<WindowEvent>() 
            {
                public void handle(WindowEvent event) 
                {
                    System.exit(0);
                }
            };
            System.out.println(resourcePath+" "+iconPath+" "+title);
            primaryStage.setOnCloseRequest(handler);
            primaryStage.getIcons().add(new Image(iconPath));
            Parent root = FXMLLoader.load(getClass().getResource(resourcePath)); 
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();              
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}