package payment.view;

import common.model.Bill;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import payment.controller.BillsController;
import payment.model.PaymentUser;

public class BillsViewer {

    private PaymentUser user;
    private static BillsController billsController;

    public BillsViewer(PaymentUser user) {
        this.user = user;
    }

    public static class HBoxBill extends HBox {

        private Bill bill;
        private CheckBox checkBox;
        private Label label;
        private Button button;


        public HBoxBill(Bill bill) {
            this.bill = bill;
            Pane pane = new Pane();

            checkBox = new CheckBox();
            checkBox.getStylesheets().add((getClass().getResource("css/CheckBoxStyle.css")).toExternalForm());

            Button button = new Button(String.valueOf(bill.getSum()) + " " + bill.getCurrency().getSymbol());

            label = new Label(checkDescriptionLength(bill.getDescription()));
            label.setFont(Font.font(20));

            super.setAlignment(Pos.CENTER);
            super.getChildren().addAll(label, pane, button);

            HBox.setHgrow(pane, Priority.ALWAYS);

            setOnMouseClicked(evt -> {
                try {
                    billsController.onButtonPayClick(bill);
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
            });
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public Bill getBill(){
            return bill;
        }

        private String checkDescriptionLength(String description) {
            if (description.length() > 21) {
                description = description.substring(0, 18) + "...";
            }
            return description;
        }
    }

    public void loadData(ListView<HBoxBill> listView, Text textSum, PaymentUser user) throws Exception{
        int sumOfBills = 0;
        listView.setFixedCellSize(45);
        for (Bill b:
             user.getBills()) {
            listView.getItems().add(new HBoxBill(b));
            sumOfBills += b.getSum();
        }

        setSumText(textSum, sumOfBills);
    }

    public static void setSumText(Text textSum, int sum){
        textSum.setText("Общая сумма:\n" + sum + "руб");
    }

    public void loadScene(Stage primaryStage) throws Exception{
        billsController = new BillsController();
        billsController.setBillViewer(this);
        billsController.setUser(user);
        billsController.setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("structures/BillsStructure.fxml"));
        primaryStage.setTitle("My Bills");

        Scene scene = new Scene(root, 335, 600);
        scene.getStylesheets().add((getClass().getResource("css/ButtonsStyle.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
