import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddressBook {

    private String addressBookName;
    private static Connection connection;

    static {
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myURL = "jdbc:mysql://localhost:3306/addressbook";
            Class.forName(myDriver);

            connection = DriverManager.getConnection(myURL, "root", "root");
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found " + e);
            System.exit(0);
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public AddressBook(String addressBookName) {
        this.addressBookName = addressBookName;
        //check if table exist or not
        String sql = "";
        try {
            ResultSet resultSet = HandleDatabase.executeQuery(sql, connection);
            if(resultSet.wasNull()) {
               String createTableSQL = "CREATE TABLE ADDRESSBOOK_" + addressBookName + "(" +
                       "id int(3) PRIMARY KEY AUTO_INCREMENT,\n" +
                       "name varchar(10) NOT NULL,\n" +
                       "mobile varchar(10),\n" +
                       "address varchar(30)";
            }
        } catch (SQLException e) {
            System.out.println("Invalid SQL : " + sql + " " + e);
        }
    }

    public void addEntry(PersonalInformation information) {
        String sql = "INSERT INTO ADDRESSBOOK_" + addressBookName + "(name, mobile, address)" +
                " VALUES('" + information.getName() + "','" + information.getMobile() + "','" + information.getAddress() + "');";
        try {
            HandleDatabase.executeUpdate(sql, connection);
        } catch (SQLException e) {
            System.out.println("Invalid SQL:" + sql + "  " + e);
        }
    }

    public void deleteEntry(String name) {
        String sql = "DELETE FROM ADDRESSBOOK_" + addressBookName + " WHERE name='" + name + "';";
        try {
            HandleDatabase.executeUpdate(sql, connection);
        } catch (SQLException e) {
            System.out.println("Invalid SQL: " + sql + " " + e);
        }
    }

    public ArrayList<PersonalInformation> getAllInformation() {
        String sql = "SELECT * FROM personalInformation;";
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
            System.out.println("SQL Invalid " + e);
        }
        return information;
    }

    @Override
    public String toString() {
        ArrayList<PersonalInformation> allInformation = getAllInformation();
        String output = "NAME\tMOBILE\tADDRESS";
        for(PersonalInformation information:allInformation) {
            output = output.concat(information.toString());
        }
          return output;
    }

}
