package guiFrame.tablePage.recordPage;
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

public class recordPage
{
    static private TableColumn cidCol = new TableColumn("cardId");
    static private TableColumn bidCol = new TableColumn("bookId");
    static private TableColumn dbCol = new TableColumn("dateBorrow");
    static private TableColumn ddCol = new TableColumn("dateDue");
    static private TableColumn drCol = new TableColumn("dateReturn");
    static private TableColumn spCol = new TableColumn("supervisior");
    static private Stage pane = new Stage();
    static private Label label= new Label();
    static private TableView<libRecord> table = new TableView<libRecord>();

    public static void init()
    {
       pane.setTitle("Record");
       pane.setMinWidth(650);
       pane.setMaxWidth(650);
       pane.setMinHeight(650);
       pane.setMaxHeight(650);
       label.setFont(new Font("Arial",20));

       table.setEditable(false);
       table.getColumns().addAll(cidCol,bidCol,dbCol,ddCol,drCol,spCol);

       cidCol.setMinWidth(100);
       bidCol.setMinWidth(100);
       dbCol.setMinWidth(100);
       ddCol.setMinWidth(100);
       drCol.setMinWidth(100);
       spCol.setMinWidth(100);
       cidCol.setCellValueFactory(new PropertyValueFactory<>("cardId"));
       bidCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
       dbCol.setCellValueFactory(new PropertyValueFactory<>("dateBorrow"));
       ddCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
       drCol.setCellValueFactory(new PropertyValueFactory<>("dateReturn"));
       spCol.setCellValueFactory(new PropertyValueFactory<>("supervisior"));
       final VBox vbox = new VBox();
       vbox.setSpacing(5);
       vbox.setPadding(new Insets(10, 0, 0, 10));
       vbox.getChildren().addAll(label, table);
       Scene scene = new Scene(new Group());
       ((Group) scene.getRoot()).getChildren().addAll(vbox);
       pane.setScene(scene);
    }
    public static void show(String id,ObservableList<libRecord> data)
    {
        label.setText("Record Of Card : "+id);
        table.setItems(data);
        pane.show();
    }
}