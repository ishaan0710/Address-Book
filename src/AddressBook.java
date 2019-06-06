import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddressBook {

    private String addressBookName;
    private static Connection connection;

    static {
        try {
            connection = HandleDatabase.getConnection("root","root");
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found " + e);
            System.exit(0);
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public AddressBook(String addressBookName) throws SQLException{
        this.addressBookName = addressBookName;
        //check if table exist or not
        String sql = "SHOW TABLES LIKE 'addressbook_" + addressBookName + "';";
        try {
            ResultSet resultSet = HandleDatabase.executeQuery(sql, connection);
            if(!resultSet.next()) {
               String createTableSQL = "CREATE TABLE ADDRESSBOOK_" + addressBookName + "(" +
                       "id int(3) PRIMARY KEY AUTO_INCREMENT, " +
                       "name varchar(10) NOT NULL, " +
                       "mobile varchar(10), " +
                       "address varchar(30))";
               HandleDatabase.executeUpdate(createTableSQL, connection);
            }
        } catch (SQLException e) {
            throw new SQLException("Invalid SQL : " + e);
        }
    }

    public void addEntry(PersonalInformation information) throws SQLException{
        String sql = "INSERT INTO ADDRESSBOOK_" + addressBookName + "(name, mobile, address)" +
                " VALUES('" + information.getName() + "','" + information.getMobile() + "','" + information.getAddress() + "');";
        try {
            HandleDatabase.executeUpdate(sql, connection);
        } catch (SQLException e) {
            throw new SQLException("Invalid SQL:" + sql + "  " + e);
        }
    }

    public void deleteEntry(String name) throws SQLException{
        String sql = "DELETE FROM ADDRESSBOOK_" + addressBookName + " WHERE name='" + name + "';";
        try {
            HandleDatabase.executeUpdate(sql, connection);
        } catch (SQLException e) {
            throw new SQLException("Invalid SQL: " + sql + " " + e);
        }
    }

    public ArrayList<PersonalInformation> getAllInformation() throws SQLException {
        String sql = "SELECT * FROM ADDRESSBOOK_" + addressBookName + ";";
        ArrayList<PersonalInformation> information = new ArrayList<>();
        try {
            ResultSet resultSet = HandleDatabase.executeQuery(sql,connection);
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String mobile = resultSet.getString("mobile");

                information.add(new PersonalInformation(name, address, mobile));
            }
        } catch (SQLException e) {
            throw new SQLException("SQL Invalid " + e);
        }
        return information;
    }

    @Override
    public String toString() {
        ArrayList<PersonalInformation> allInformation;
        try {
            allInformation = getAllInformation();
        } catch (SQLException e) {
            return "";
        }
        String output = "NAME\tMOBILE\tADDRESS\n";
        for(PersonalInformation information:allInformation) {
            output = output.concat(information.toString());
        }
          return output;
    }

}
