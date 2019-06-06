import java.sql.SQLException;
import java.util.Scanner;

public class DriverClass {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of address Book:");
        String addressBookName = scanner.next();
        AddressBook addressBook = new AddressBook(addressBookName);
        System.out.println("Enter number of entries to be included:");
        int num = scanner.nextInt();
        System.out.println("Enter name, address and mobile of person:");
        for(int i=0;i<num;i++) {
            String name = scanner.next();
            String address = scanner.next();
            String mobile = scanner.next();
            PersonalInformation information = new PersonalInformation(name, address, mobile);
            addressBook.addEntry(information);
            if(i!=num-1)
                System.out.println("Enter next detail");
        }
        System.out.println(addressBook);
    }

}
