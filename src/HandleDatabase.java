import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleDatabase {

    public static ResultSet executeQuery(String sql, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public static void executeUpdate(String sql, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

}
