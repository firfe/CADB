package sql;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Matthew Erickson
 * 2/6/16
 */
public class SQLTableView extends TableView {

    public static TableView<ObservableList<String>> create(ResultSet resultSet) throws SQLException {
        return create(convertToArrayList(resultSet));
    }

    public static TableView<ObservableList<String>> create(String fileName) throws SQLException, FileNotFoundException {
        return create(convertToArrayList(fileName));
    }

    public static TableView<ObservableList<String>> create(ArrayList<ArrayList<String>> data) throws SQLException {
        ObservableList<ObservableList<String>> tableData = FXCollections.observableArrayList();
        TableView<ObservableList<String>> tableView = new TableView<>();

        /* add columns names */
        final ArrayList<String> colNames = data.get(0);
        for (int i = 0; i < colNames.size(); i++) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(colNames.get(i));
            column.prefWidthProperty().bind(tableView.widthProperty().divide(colNames.size()).subtract(1.0));
            final int finalI = i;
            column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(finalI)));
            tableView.getColumns().add(column);
        }

        /* fill in row data */
        for (int i = 1; i < data.size(); i++) {
            tableData.add(FXCollections.observableArrayList(data.get(i)));
        }
        tableView.setItems(tableData);
        tableView.setRowFactory(param -> {
            final TableRow<ObservableList<String>> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ObservableList<String> rowData = row.getItem();
                    Stage stage = new Stage();
                    VBox vBox = new VBox(10);
                    vBox.setPadding(new Insets(10));
                    for (int i = 0; i < rowData.size(); i++) {
                        Text t = new Text(colNames.get(i) + ": " + rowData.get(i));
                        vBox.getChildren().add(t);
                    }
                    stage.setScene(new Scene(vBox));
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
            });
            return row;
        });
        return tableView;
    }

    public static ArrayList<ArrayList<String>> convertToArrayList(ResultSet rs) throws SQLException {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();

        /* add columns names */
        ArrayList<String> colNames = new ArrayList<>();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            colNames.add(metaData.getColumnName(i + 1));
        }
        data.add(colNames);

        /* add row data */
        while (rs.next()) {
            ArrayList<String> row = new ArrayList<>();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String cellData = rs.getString(i+1);
                if (cellData == null) cellData = "null";
                row.add(cellData);
            }
            data.add(row);
        }

        rs.close();
        return data;
    }

    private static ArrayList<ArrayList<String>> convertToArrayList(String fileName) throws SQLException, FileNotFoundException {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) break;
            String[] attrs = line.split(",");
            ArrayList<String> list = new ArrayList<>();
            Collections.addAll(list, attrs);
            data.add(list);
        }

        in.close();
        return data;
    }

}
