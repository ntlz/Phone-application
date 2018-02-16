package other.scene1;

import common.model.Bill;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import payment.controller.BillsController;
import payment.model.PaymentUser;
import payment.view.BillsViewer;

import java.time.LocalDateTime;
import java.util.Date;

public class Main extends Application {

    private static BillsViewer billsViewer;
    private static PaymentUser user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        user = new PaymentUser("1234", "123456");
        user.addBill("1", "Кошка", "ООО Мармелад", new Date(), new Date(),
                Bill.Currency.RUBLE, 1000 );
        user.addBill("2", "Собака", "ООО Мармелад", new Date(), new Date(),
                Bill.Currency.RUBLE, 2000);
        user.addBill("3", "Попугай", "ООО Мармелад", new Date(), new Date(),
                Bill.Currency.RUBLE, 4000);

        billsViewer = new BillsViewer();
        billsViewer.loadScene(primaryStage, user);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
