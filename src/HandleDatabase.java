import java.sql.*;

public class HandleDatabase {

    public static ResultSet executeQuery(String sql, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public static void executeUpdate(String sql, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement = connection.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    public static Connection getConnection(String user,String pass) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myURL = "jdbc:mysql://localhost:3306/addressbook";
        Class.forName(myDriver);
        return DriverManager.getConnection(myURL, user, pass);
    }

}
