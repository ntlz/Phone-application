package payment.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import payment.model.PaymentUser;
import payment.view.BillsViewer;
import javafx.scene.text.Text;


public class BillsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    @FXML
    private Text textSum;

    ObservableList<String> list = FXCollections.observableArrayList();

    private static PaymentUser user;

    @FXML
    void initialize() throws Exception {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'BillsStructure.fxml'.";
        BillsViewer.setBills(user);
        BillsViewer.loadData(list, listView, textSum);
    }

    public static void onButtonPayClick(ActionEvent event){
        System.out.println("Event " + event);
    }

    public static void setUser(PaymentUser newUser){
        user = newUser;
    }
}
