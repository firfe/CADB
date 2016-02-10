package scene;

import control.Manager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sql.SQLTableView;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Matthew Erickson
 * 2/6/16
 */
public class Home {

    private static final double HEIGHT = 500.0;
    private static final double WIDTH = 800.0;
    public static final String TITLE = "Database - Home";

    private static Scene home;

    private static BorderPane tableContainer;
    private static TableView tableView;

    private static TextArea queryField;

    public static Scene createScene() {
        if (home != null) return home;

        VBox root = new VBox(10);
        root.setCache(true);
        root.setCacheHint(CacheHint.SCALE);
        root.setAlignment(Pos.CENTER);

        /* top pane for UI controls */
        HBox fileHBox = new HBox(20);
        fileHBox.setPadding(new Insets(25, 25, 25, 25));
        fileHBox.setAlignment(Pos.CENTER);

        /* sql query fields */
        BorderPane queryGrid = new BorderPane();

        Label queryLabel = new Label("Query:");
        queryLabel.setPadding(new Insets(10));
        queryField = new TextArea();
        Button queryButton = new Button("Submit");
        queryButton.setPadding(new Insets(10));
        SQLQuery sqlQuery = new SQLQuery();
        queryButton.setOnAction(sqlQuery);
        queryGrid.setLeft(queryLabel);
        queryGrid.setCenter(queryField);
        queryGrid.setRight(queryButton);
        root.getChildren().add(queryGrid);

        /* pane for showing data in table */
        tableContainer = new BorderPane();
        tableView = new TableView();
        tableView.setTableMenuButtonVisible(false);
        tableContainer.setCenter(tableView);
        root.getChildren().add(tableContainer);

        /* add footer */
        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER);
        Text footerText = new Text("Command Aviation Inc., 2016");
        Button exit = new Button("Exit");
        exit.setPadding(new Insets(5));
        exit.setOnAction((event) -> Manager.goLogin());
        footer.getChildren().addAll(footerText, exit);
        root.getChildren().add(footer);

        home = new Scene(root, WIDTH, HEIGHT);
        return home;
    }

    private static class SQLQuery implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String query = queryField.getText();
            try {
                ResultSet resultSet = Manager.sqlConnection.executeQuery(query);
                if (resultSet != null) {
                    tableView = SQLTableView.create(resultSet);
                    Platform.runLater(() -> tableContainer.setCenter(tableView));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
