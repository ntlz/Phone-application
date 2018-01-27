package other.enterApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login page.fxml"));
        Scene s = new Scene(root, 462, 670);
        Image logo = new Image("other/enterApp/PAlogo.png");
        ImageView img= new ImageView(logo);
        primaryStage.setScene(s);
        primaryStage.getIcons().add(logo); //картинка на иконку
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
