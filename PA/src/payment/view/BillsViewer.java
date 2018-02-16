package payment.view;

import common.model.Bill;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import payment.controller.BillsController;
import payment.model.PaymentUser;

import java.util.ArrayDeque;
import java.util.Queue;

public class BillsViewer {

    private  Queue<Bill> bills;       //очередь для выдачи счетов пользователя
    private boolean flag = false;    //флаг для отрисовки кнопок счетов
    private BillsController billsController;

    class BillLine extends ListCell<String> {
        Bill bill;
        HBox hbox = new HBox();
        Label label = new Label("empty");
        Pane pane = new Pane();
        Button button = new Button();
        String lastItem;

        private BillLine(Bill bill){
            super();
            if(bill != null){
                this.bill = bill;
                button.setText(bill.getSum() + bill.getCurrency().getSymbol());
            }
            button.setFont(Font.font(15));

            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            label.setFont(Font.font(18));
            button.setFont(Font.font(15));
            button.setOnAction(event -> {
                try {
                    billsController.onButtonPayClick(event, bill);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        private BillLine() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            label.setFont(Font.font(18));
            button.setText("-");
            button.setFont(Font.font(15));
            button.setOnAction(event -> {
                try {
                    billsController.onButtonPayClick(event, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item != null ? item : "<null>");
                setGraphic(hbox);
            }
        }

    }

    public void loadData(ObservableList<String> list, ListView<String> listView, Text textSum, PaymentUser user) throws Exception{
        setBills(user);
        list.removeAll();
        int sum = 0;
        for (Bill b: user.getBills()) {
            String description = b.getDescription();
            description = checkDescriptionLength(description);  //вывод укороченного описания, если длина больше 25 символов
            list.add(description);
            sum += b.getSum();
        }
        setSumText(textSum, sum);
        listView.setCellFactory(param -> {
            if(!bills.isEmpty() && flag) {
                Bill bill = bills.poll();
                return new BillLine(bill);
            }
            else{
                flag = true;
                return new BillLine();
            }
        });


        listView.getItems().addAll(list);
    }

    private String checkDescriptionLength(String description){
        if(description.length() > 25){
            description = description.substring(0, 22) + "...";
        }
        return description;
    }

    private static void setSumText(Text textSum, int sum){
        textSum.setText("Общая сумма:\n" + sum + "руб");
    }

    private void setBills(PaymentUser user){
        bills = new ArrayDeque<>();
        bills.addAll(user.getBills());
    }

    public void loadScene(Stage primaryStage, PaymentUser user) throws Exception{
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
