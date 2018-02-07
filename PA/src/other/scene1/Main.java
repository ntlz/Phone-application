package other.scene1;

import common.model.Bill;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import payment.controller.BillsController;
import payment.model.PaymentUser;

import java.util.Date;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        PaymentUser user = new PaymentUser("1234", "123456");
        user.addBill("1", "Кошка", "ООО Мармелад", new Date(1000), new Date(2000),
                Bill.Currency.RUBLE, 1000 );
        user.addBill("2", "Собака", "ООО Мармелад", new Date(1000), new Date(2000),
                Bill.Currency.RUBLE, 2000);
        user.addBill("3", "Попугай", "ООО Мармелад", new Date(1000), new Date(2000),
                Bill.Currency.RUBLE, 4000);
        BillsController.setUser(user);
        Parent root = FXMLLoader.load(getClass().getResource("../../payment/view/structures/BillsStructure.fxml"));
        primaryStage.setTitle("My Bills");
        Scene scene = new Scene(root, 335, 600);
        scene.getStylesheets().add((getClass().getResource("../../payment/view/css/BillsStyle.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
