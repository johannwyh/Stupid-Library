package guiFrame.pageCtrl.userPage;
import guiFrame.controlManager.controlManager;
import guiFrame.pageCtrl.pageCtrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

import Engine.Authorization.Authorization;
import Engine.userManagement.userManagement;

public class userPage extends pageCtrl
{
    @FXML
    private TextField cardId;
    @FXML
    private TextField Passwd;
    @FXML
    private TextField Name;
    @FXML
    private TextField Department;
    @FXML
    private TextField myType;

    public void showCardInfo()
    {
        System.out.println("card ID : "+cardId.getText());
    }
    public void insertUser()
    {
        String id = cardId.getText();
        String pwd = Passwd.getText();
        String name = Name.getText();
        String department = Department.getText();
        String type = myType.getText();
        String message = "Insert user with id="+id+", password="+pwd+", name="+name+
        ", department="+department+", type="+type+" ?";
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,message);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            boolean state = userManagement.insertUser(id, pwd, name, department, type);   
            if(state) message="Successfully insert the new card";
            else message="Failed. ID have existed or Wrong Format.";
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Result");
            info.setHeaderText("SPLibrary");
            info.setContentText(message);
            info.show();
        }
        //Authorization.printAccount();
    }
    public void deleteUser()
    {
        String id = cardId.getText();
        String message = "Delete user with id="+id+" ?";
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,message);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            boolean state=userManagement.deleteUser(id);
            if(state) message="Successfully delete the card";
            else message="Failed. ID doesn't existe or Wrong Format.";
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Result");
            info.setHeaderText("SPLibrary");
            info.setContentText(message);
            info.show();
        }
        //System.out.println("want to delete user : "+cardId.getText());
        Authorization.printAccount();
    }
}