package sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Matthew Erickson
 * 2/6/16
 */
public class SQLConnection {

    public static final String DEFAULT_CONFIG = "ConnectionProperties.txt";
    private static final String DB_URL = "dbUrl";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final String DB_PREFIX = "jdbc:mysql://";

    private Properties properties;
    private Connection connection;
    private String username, password, databaseUrl;

    public SQLConnection(String databaseUrl, String username, String password) throws IOException, SQLException {
        this.databaseUrl = DB_PREFIX + databaseUrl;
        this.username = username;
        this.password = password;
        initializeConnection();
    }

    public SQLConnection(String configFileName) throws IOException, SQLException {
        loadProperties(configFileName);
        initializeConnection();
    }

    public void initializeConnection() throws SQLException {
        connection = DriverManager.getConnection(databaseUrl, username, password);
    }

    private void loadProperties(String configFileName) throws IOException {
        if (configFileName == null) {
            configFileName = DEFAULT_CONFIG;
        }
        properties = new Properties();
        properties.load(new FileInputStream(configFileName));
        this.username = properties.getProperty(USER);
        this.password = properties.getProperty(PASSWORD);
        this.databaseUrl = properties.getProperty(DB_URL);
    }

    public ResultSet executeQuery(String query, Object...args) throws SQLException{
        if (args.length != countSubstrings(query, "?")) {
            return null;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setString(i + 1, args[i].toString());
        }
        ResultSet rs = null;
        try {
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            /* query failed so it was probably an update */
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }

        return rs;
    }

    public HashMap<String, ArrayList<String>> getTableColumnNames() throws SQLException {
        HashMap<String, ArrayList<String>> tableColumnNames = new HashMap<>();
        ArrayList<String> tableNames = getTableNames();
        for (String tableName : tableNames) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("describe " + tableName);
            ArrayList<ArrayList<String>> arrayList = SQLTableView.convertToArrayList(rs);
            ArrayList<String> columnNames = new ArrayList<>();
            for (ArrayList<String> list : arrayList) {
                columnNames.add(list.get(0));
            }
            /* remove table column name */
            columnNames.remove(0);
            tableColumnNames.put(tableName, columnNames);
        }
        return tableColumnNames;
    }

    public ArrayList<String> getTableNames() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("show tables");
        ArrayList<ArrayList<String>> arrayList = SQLTableView.convertToArrayList(rs);
        ArrayList<String> tableNames = new ArrayList<>();
        for (ArrayList<String> list : arrayList) {
            tableNames.add(list.get(0));
        }
        /* remove table column name */
        tableNames.remove(0);
        return tableNames;
    }

    private int countSubstrings(String query, String substring) {
        if (!query.contains(substring)) return 0;
        String reduced = query.replaceAll(substring, "");
        return (query.length() - reduced.length()) / substring.length();
    }

    public void close() {
        try {
            connection.close();
            properties = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
