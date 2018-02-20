package payment.controller;

import java.net.URL;
import java.util.ResourceBundle;

import payment.model.Bill;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import payment.model.User;
import payment.view.BillInformationAndPayViewer;
import payment.view.BillsViewer;
import javafx.scene.text.Text;


public class BillsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<BillsViewer.HBoxBill> listView;

    @FXML
    private Text textSum;

    @FXML
    private GridPane menuPane;

    @FXML
    private Button menuButton;

    @FXML
    private Button choseButton;

    @FXML
    private Button switchBillsButton;

    private static User user;
    private Stage stage;    //поле для переключения сцен
    private static BillsViewer billsViewer;
    private BillInformationAndPayViewer informationViewer;
    private int sumOfChosedBills;
    private int sumOfAllBills;
    private static boolean flagListeners = false;

    @FXML
    void initialize() throws Exception {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'BillsStructure.fxml'.";
        billsViewer.loadData(listView, textSum, user);

        menuPane.setTranslateX(-200);
        TranslateTransition menuMoving = new TranslateTransition(Duration.millis(300), menuPane);
        menuMoving.setFromX(-200);
        menuMoving.setToX(0);

        menuButton.setOnMouseClicked(evt ->{
            menuMoving.setRate(1);
            menuMoving.play();
        });

        choseButton.setOnMouseClicked(evt ->{
            onChoseButtonClick();
        });
    }

    public void onSelectCheckBox(BillsViewer.HBoxBill hbox){
        if(hbox.getCheckBox().isSelected()) {
            hbox.setOpacity(1);
            sumOfChosedBills += hbox.getBill().getSum();
            BillsViewer.setSumText(textSum, sumOfChosedBills);
        }
        else{
            hbox.setOpacity(0.5);
            sumOfChosedBills -= hbox.getBill().getSum();
            BillsViewer.setSumText(textSum, sumOfChosedBills);
        }
    }

    public void onButtonPayClick(Bill bill) throws Exception{
        informationViewer = new BillInformationAndPayViewer(user, bill);
        informationViewer.showScene(stage);
    }

    private void onChoseButtonClick(){
        sumOfAllBills = 0;
        for (BillsViewer.HBoxBill hbox: listView.getItems()) {
            hbox.getCheckBox().setSelected(false);
            hbox.getChildren().add(0, hbox.getCheckBox());
            if(!flagListeners) {
                hbox.getCheckBox().selectedProperty().addListener(evt -> onSelectCheckBox(hbox));
            }
            hbox.setOpacity(0.5);
            sumOfAllBills += hbox.getBill().getSum();
        }
        flagListeners = true;
        choseButton.setText("Отменить");
        switchBillsButton.setText("Оплатить выбранные");
        choseButton.setOnMouseClicked(evt-> onCancelButtonClick());
        sumOfChosedBills = 0;
        BillsViewer.setSumText(textSum, sumOfChosedBills);
    }

    private void onCancelButtonClick(){
        for (BillsViewer.HBoxBill hbox: listView.getItems()) {
            hbox.getChildren().remove(0);
            hbox.setOpacity(1);
        }
        choseButton.setText("Выбрать");
        switchBillsButton.setText("Пришедшие счета");
        choseButton.setOnMouseClicked(evt-> onChoseButtonClick());
        BillsViewer.setSumText(textSum, sumOfAllBills);
        sumOfChosedBills = 0;
    }

    public void setUser(User newUser){
        user = newUser;
    }

    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }

    public void setBillViewer(BillsViewer setBillsViewer){
        billsViewer = setBillsViewer;
    }

}
