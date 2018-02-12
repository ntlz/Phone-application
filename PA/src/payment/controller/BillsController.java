package payment.controller;

import java.net.URL;
import java.util.ResourceBundle;

import common.model.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import other.scene1.Main;
import payment.model.PaymentUser;
import payment.view.BillInformationAndPayViewer;
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

    private ObservableList<String> list = FXCollections.observableArrayList();
    private PaymentUser user;
    private Stage stage;    //поле для переключения сцен
    private BillsViewer billsViewer;
    private BillInformationAndPayViewer informationViewer = new BillInformationAndPayViewer();

    @FXML
    void initialize() throws Exception {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'BillsStructure.fxml'.";
        billsViewer = Main.getBillsViewer();
        billsViewer.setBills(Main.getUser());
        billsViewer.loadData(list, listView, textSum);

    }

    public void onButtonPayClick(ActionEvent event, Bill bill) throws Exception{
        informationViewer.setBill(bill);
        informationViewer.showScene(stage);
    }

    public void setUser(PaymentUser newUser){
        user = newUser;
    }

    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }

    public void setBillViewer(BillsViewer billsViewer){
        this.billsViewer = billsViewer;
    }

}
