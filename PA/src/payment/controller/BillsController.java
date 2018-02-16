package payment.controller;

import java.net.URL;
import java.util.ResourceBundle;

import common.model.Bill;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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

    @FXML
    private GridPane menuPane;

    @FXML
    private Button menuButton;

    @FXML
    private Button chooseButton;

    @FXML
    private Button switchBillsButton;

    private ObservableList<String> list = FXCollections.observableArrayList();
    private static PaymentUser user;
    private Stage stage;    //поле для переключения сцен
    private static BillsViewer billsViewer;
    private BillInformationAndPayViewer informationViewer = new BillInformationAndPayViewer();

    @FXML
    void initialize() throws Exception {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'BillsStructure.fxml'.";
        billsViewer.loadData(list, listView, textSum, user);

        menuPane.setTranslateX(-200);
        TranslateTransition menuMoving = new TranslateTransition(Duration.millis(300), menuPane);
        menuMoving.setFromX(-200);
        menuMoving.setToX(0);

        menuButton.setOnMouseClicked(evt ->{
            menuMoving.setRate(1);
            menuMoving.play();
        });

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

    public void setBillViewer(BillsViewer setBillsViewer){
        billsViewer = setBillsViewer;
    }

}
