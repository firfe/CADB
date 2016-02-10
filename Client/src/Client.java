import control.Manager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Matthew Erickson
 * 2/6/16
 */
public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Manager.stage = primaryStage;
        Manager.goLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
