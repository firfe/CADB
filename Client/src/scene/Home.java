package scene;

import control.Manager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sql.SQLTableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
    private static TextField queryField;

    private static HashMap<String, ArrayList<String>> tableColumnNames;
    private static ObservableList<String> tableNames;
    private static ComboBox<String> dropDownBox;

    public static Scene createScene() {
        //if (home != null) return home;
        getFields();

        VBox root = new VBox(10);
        root.setCache(true);
        root.setCacheHint(CacheHint.SCALE);
        root.setAlignment(Pos.CENTER);

        /* sql query fields */
        BorderPane queryGrid = new BorderPane();
        queryGrid.setPadding(new Insets(20));
        dropDownBox = new ComboBox<>(tableNames);
        dropDownBox.setValue(tableNames.get(0));
        queryField = new TextField();
        Button queryButton = new Button("Submit");
        SQLQuery sqlQuery = new SQLQuery();
        queryButton.setOnAction(sqlQuery);
        queryGrid.setLeft(dropDownBox);
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
        footer.setPadding(new Insets(15));
        Text footerText = new Text("Command Aviation Inc., 2016");
        Button exit = new Button("Exit");
        exit.setOnAction((event) -> Manager.goLogin());
        footer.getChildren().addAll(footerText, exit);
        root.getChildren().add(footer);

        home = new Scene(root, WIDTH, HEIGHT);
        return home;
    }

    private static void getFields() {
        try {
            tableColumnNames = Manager.sqlConnection.getTableColumnNames();
            tableNames = FXCollections.observableArrayList();
            for (String key: tableColumnNames.keySet()) {
                if (!tableNames.contains(key)) {
                    tableNames.add(key);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
    }

    private static class SQLQuery implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String query = buildQuery();
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

        private String buildQuery() {
            StringBuilder builder = new StringBuilder("select * from ");
            String tableName = dropDownBox.getValue();
            builder.append(tableName);

            String searchText = queryField.getText();
            builder.append(" where ");
            for (String option : tableColumnNames.get(tableName)) {
                builder.append(option).append(" like '%").append(searchText).append("%' or ");
            }
            builder.append("false;");
            System.err.println("Query: " + builder.toString());
            return builder.toString();
        }
    }

}
