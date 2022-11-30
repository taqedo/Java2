package lesson6.Server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement statement;

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickname(String login, String password){
        String query = String.format("SELECT nickname from main \n" +
                "where login = '%s' \n" +
                "and password = '%s'", login, password);
        try {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
