package guiFrame.tablePage.bookListPage;
import guiFrame.tableType.bookList.bookList;
import guiFrame.tableType.libRecord.libRecord;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class bookListPage
{
    //private String bookId,bookType,bookTitle,bookPress,bookAuthor;
    //private int bookYear,bookNum,bookStock;
    //private float bookPrice;
    static private TableColumn bidCol = new TableColumn("bookId");
    static private TableColumn btypeCol = new TableColumn("bookType");
    static private TableColumn btitleCol = new TableColumn("bookTitle");
    static private TableColumn bpressCol = new TableColumn("bookPress");

    static private TableColumn bauthorCol = new TableColumn("bookAuthor");
    static private TableColumn byearCol = new TableColumn("bookYear");
    static private TableColumn bnumCol = new TableColumn("bookNum");
    static private TableColumn bstockCol = new TableColumn("bookStock");

    static private TableColumn bpriceCol = new TableColumn("bookPrice");

    static private Stage pane = new Stage();
    static private Label label= new Label();
    static private TableView<bookList> table = new TableView<bookList>();

    public static void init()
    {
       pane.setTitle("Search Result");
       pane.setMinWidth(950);
       pane.setMaxWidth(950);
       pane.setMinHeight(650);
       pane.setMaxHeight(650);
       label.setFont(new Font("Arial",20));

       table.setEditable(false);
       table.getColumns().addAll(bidCol,btypeCol,btitleCol,bpressCol,bauthorCol,byearCol,bnumCol,bstockCol,bpriceCol);

       bidCol.setMinWidth(100);
       bidCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
       btypeCol.setMinWidth(100);
       btypeCol.setCellValueFactory(new PropertyValueFactory<>("bookType"));
       btitleCol.setMinWidth(100);
       btitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
       bpressCol.setMinWidth(100);
       bpressCol.setCellValueFactory(new PropertyValueFactory<>("bookPress"));
       bauthorCol.setMinWidth(100);
       bauthorCol.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
       byearCol.setMinWidth(100);
       byearCol.setCellValueFactory(new PropertyValueFactory<>("bookYear"));
       bnumCol.setMinWidth(100);
       bnumCol.setCellValueFactory(new PropertyValueFactory<>("bookNum"));
       bstockCol.setMinWidth(100);
       bstockCol.setCellValueFactory(new PropertyValueFactory<>("bookStock"));
       bpriceCol.setMinWidth(100);
       bpriceCol.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

       final VBox vbox = new VBox();
       vbox.setSpacing(5);
       vbox.setPadding(new Insets(10, 0, 0, 10));
       vbox.getChildren().addAll(label, table);
       Scene scene = new Scene(new Group());
       ((Group) scene.getRoot()).getChildren().addAll(vbox);
       pane.setScene(scene);
    }
    public static void show(ObservableList<bookList> data)
    {
        label.setText("Search Result : ");
        table.setItems(data);
        pane.show();
    }
}