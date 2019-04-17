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

public class guiFrame extends Application
{
    public static void  trigger(String[] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage)
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
            
            primaryStage.setOnCloseRequest(handler);
            primaryStage.getIcons().add(new Image("/view/icon.png"));
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml")); 
            primaryStage.setTitle("SPLibrary");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}