package payment.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import payment.view.BillInformationAndPayViewer;

import java.awt.event.ActionEvent;

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

    @FXML
    void initialize() throws Exception{
        viewer.pullInformation(senderLabel, descriptionLabel, dateLabel, sumLabel, sumUpperLabel);
    }

    public static void setViewer(BillInformationAndPayViewer newViewer){
        viewer = newViewer;
    }

    public void onReturnButtonClick(){

    }
}