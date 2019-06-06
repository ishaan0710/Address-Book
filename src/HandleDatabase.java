import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HandleDatabase {

    public static ResultSet executeQuery(String sql, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public static void addInformation(Connection connection, PersonalInformation information) {
        String sql = "INSERT INTO personalInformation(name, mobile, address)" +
                " VALUES('" + information.getName() + "','" + information.getMobile() + "','" + information.getAddress() + "');";
        try {
            executeQuery(sql, connection);
        } catch (SQLException e) {
            System.out.println("SQL Invalid: " + sql + e);
        }
    }

    public ArrayList<PersonalInformation> getAllInformation(Connection connection) {
        String sql = "SELECT * FROM personalInformation;";
        ArrayList<PersonalInformation> information = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(sql,connection);
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String mobile = resultSet.getString("mobile");

                information.add(new PersonalInformation(name, address, mobile));
            }
        } catch (SQLException e) {
            System.out.println("SQL Invalid " + e);
        }
        return information;
    }

    public void deleteEntry(PersonalInformation information, String name, Connection connection) {
        String sql = "DELETE FROM personalInformation WHERE name='" + name + "';";
        try {
            executeQuery(sql, connection);
        } catch (SQLException e) {
            System.out.println("Invalid SQL " + e);
        }
    }



}
