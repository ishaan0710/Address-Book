import java.sql.*;

public class PersonalInformation {

    private String name;
    private String address;
    private String mobile = "";
    public Connection connection;

    public PersonalInformation(String name, String address, String mobile) {
        this.name = name;
        this.address = address;
        if(checkMobile(mobile))
            this.mobile = mobile;
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
        HandleDatabase.addInformation(connection,this);
    }

    public PersonalInformation(String name, String address) {
        this(name, address, "");
    }

    public PersonalInformation(String name) {
        this(name, "", "");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    private boolean checkMobile(String mobile) {
        if(mobile.length()==10) {
            for(int i = 0;i<mobile.length();i++) {
                if(mobile.charAt(i)>'9' || mobile.charAt(i)<'0') {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        PersonalInformation info = new PersonalInformation("Ishaan", "Swarn Jayanti Nagar","8923407986");


    }

}
