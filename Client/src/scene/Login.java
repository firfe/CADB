package scene;

import control.Manager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sql.SQLConnection;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Matthew Erickson
 * 2/7/16
 */
public class Login {

    public static final String TITLE = "Database - Login";
    private static TextField dbField;
    private static TextField userField;
    private static PasswordField passField;
    private static ProgressIndicator progress;
    private static Text responseText;

    public static Scene createScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Login");
        title.setFont(Font.font("Tahoma", 20));
        gridPane.add(title, 0, 0, 2, 1);

        Label userLabel = new Label("Username:");
        userField = new TextField();
        userField.setPromptText("Enter username");
        gridPane.addRow(1, userLabel, userField);

        Label passLabel = new Label("Password:");
        passField = new PasswordField();
        passField.setPromptText("Enter password");
        gridPane.addRow(2, passLabel, passField);

        Label dbLabel = new Label("Database URL:");
        dbField = new TextField();
        dbField.setPromptText("[host]:[port]/[database name]");
        gridPane.addRow(3, dbLabel, dbField);

        HBox submitBox = new HBox(10);
        Button submit = new Button("Submit");
        Platform.runLater(submit::requestFocus);
        progress = new ProgressIndicator();
        progress.setVisible(false);
        submitBox.getChildren().addAll(submit, progress);
        gridPane.add(submitBox, 1, 4, 2, 1);

        SubmitLogin submitLogin = new SubmitLogin();
        userField.setOnAction(submitLogin);
        passField.setOnAction(submitLogin);
        dbField.setOnAction(submitLogin);
        submit.setOnAction(submitLogin);

        responseText = new Text();
        responseText.setStyle("-fx-color: red");
        gridPane.add(responseText, 0, 5, 2, 1);

        return new Scene(gridPane);
    }

    private static class SubmitLogin implements EventHandler<ActionEvent> {

        private static final String SUCCESS = "Login successful";
        private static final String NET_ERROR = "Network error";
        private static final String LOGIN_ERROR = "Invalid credentials";

        @Override
        public void handle(ActionEvent event) {
            Platform.runLater(() -> progress.setVisible(true));

            new Thread(() -> {
                String response = openConnection();
                new Thread(new TempText(responseText, response)).start();
                if (response.equals(SUCCESS)) {
                    Manager.sleep(1000);
                    Manager.goHome();
                }
                Platform.runLater(() -> progress.setVisible(false));
            }).start();
        }

        private String openConnection() {
            String userText = userField.getText();
            String passText = passField.getText();
            String databaseUrl = dbField.getText();
            try {
                Manager.sqlConnection = new SQLConnection(databaseUrl, userText, passText);
                return SUCCESS;
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return NET_ERROR;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                if (e.getErrorCode() == 1045) return LOGIN_ERROR;
                return e.getMessage();
            }
        }

    }

    private static class TempText implements Runnable {

        private static final int TIME = 2000;
        private Text item;
        private String text;

        public TempText(Text item, String text) {
            this.item = item;
            this.text = text;
        }

        @Override
        public void run() {
            Platform.runLater(() -> item.setText(text));
            Manager.sleep(TIME);
            Platform.runLater(() -> item.setText(""));
        }
    }

}
