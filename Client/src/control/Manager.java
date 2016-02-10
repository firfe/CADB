package control;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.Home;
import scene.Login;
import sql.SQLConnection;

/**
 * Matthew Erickson
 * 2/7/16
 */
public class Manager {

    public static Stage stage;
    public static SQLConnection sqlConnection;

    public static void changeScene(final Scene newScene, final String title) {
        Platform.runLater(() -> {
            stage.setScene(newScene);
            stage.centerOnScreen();
            stage.setTitle(title);
            stage.show();
        });
    }

    public static void goLogin() {
        changeScene(Login.createScene(), Login.TITLE);
    }

    public static void goHome() {
        changeScene(Home.createScene(), Home.TITLE);
    }

    public static void closeConnection() {
        sqlConnection.close();
        sqlConnection = null;
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            /* exception ignored */
        }
    }

}
