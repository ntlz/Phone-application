package payment.view;

import common.model.Bill;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import payment.controller.BillsController;
import payment.model.PaymentUser;

import java.util.ArrayDeque;
import java.util.Queue;

public class BillsViewer {

    private static PaymentUser user;        //пользователь данного кабинета
    private static Queue<Bill> bills;       //очередь для выдачи счетов пользователя
    private static boolean flag = false;    //флаг для отрисовки кнопок счетов

    static class BillLine extends ListCell<String> {
        Bill bill;
        HBox hbox = new HBox();
        Label label = new Label("empty");
        Pane pane = new Pane();
        Button button = new Button();
        String lastItem;

        private BillLine(Bill bill) {
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
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    BillsController.onButtonPayClick(event);
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
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    BillsController.onButtonPayClick(event);
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

    public static void loadData(ObservableList<String> list, ListView<String> listView, Text textSum) throws Exception{
        list.removeAll();
        int sum = 0;
        for (Bill b: user.getBills()) {
            list.add(b.getDescription());
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

    public static void setBills(PaymentUser newUser){
        user = newUser;
        bills = new ArrayDeque<>();
        bills.addAll(newUser.getBills());
    }

    private static void setSumText(Text textSum, int sum){
        textSum.setText("Общая сумма:\n" + sum + "руб");
    }
}
