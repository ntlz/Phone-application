package scene1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;


public class SceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> listView;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'sceneBills.fxml'.";
        loadData();

    }

    private void loadData(){
        list.removeAll(list);
        String a = "1234";
        String b = "1234";
        String c = "1234";
        String d = "1234";

        list.addAll(a,b,c,d);

        listView.getItems().addAll(list);
    }

}
