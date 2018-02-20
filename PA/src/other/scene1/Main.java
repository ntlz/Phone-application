package other.scene1;

import payment.model.Bill;
import javafx.application.Application;
import javafx.stage.Stage;
import payment.model.User;
import payment.view.BillsViewer;

import java.util.Date;

public class Main extends Application {

    private static BillsViewer billsViewer;
    private static User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        user = new User("1234", "123456");
        user.addBillOutcome("1", "Кошка лялялялялялялялял", "ООО Мармелад", new Date(), new Date(),
                Bill.Currency.RUBLE, 1000 );
        user.addBillOutcome("2", "Собака", "ООО Мармелад", new Date(), new Date(),
                Bill.Currency.RUBLE, 2000);
        user.addBillOutcome("3", "Попугай", "ООО Мармелад", new Date(), new Date(),
                Bill.Currency.RUBLE, 4000);

        billsViewer = new BillsViewer(user);
        billsViewer.loadScene(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
