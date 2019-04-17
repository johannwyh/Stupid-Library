package guiFrame.pageCtrl.boOrRePage;
import guiFrame.controlManager.controlManager;
import guiFrame.pageCtrl.pageCtrl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class boOrRePage extends pageCtrl
{
    @FXML
    private TextField cardId;
    @FXML
    private TextField bookId;
    public void showCardInfo()
    {
        System.out.println("card ID : "+cardId.getText());
    }
    public void showBookInfo()
    {
        System.out.println("book ID : "+bookId.getText());
    }
    public void toBorrow()
    {
        System.out.println("User with ID "+cardId.getText()+" want to borrow book with ID "+
        bookId.getText());
    }
    public void toReturn()
    {
        System.out.println("User with ID "+cardId.getText()+" want to return book with ID "+
        bookId.getText());
    }
}