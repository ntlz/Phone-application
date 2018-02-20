package payment.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import payment.model.User;
import payment.view.BillInformationAndPayViewer;
import payment.view.BillsViewer;

public class BillInformationAndPayController {

    @FXML
    private Label sumUpperLabel;

    @FXML
    private Label senderLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label sumLabel;

    @FXML
    private Button payButton;

    @FXML
    private Button payAndMessageButton;

    private static BillInformationAndPayViewer viewer;
    private static User user;
    private static Stage stage;

    @FXML
    void initialize() throws Exception{
        viewer.pullInformation(senderLabel, descriptionLabel, dateLabel, sumLabel, sumUpperLabel);
    }

    public static void setViewer(BillInformationAndPayViewer newViewer){
        viewer = newViewer;
    }

    public static void setUser(User newUser){user = newUser;}

    public static void setStage(Stage primaryStage){stage = primaryStage;}

    public void onReturnButtonClick(){
        try {
            new BillsViewer(user).loadScene(stage);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}