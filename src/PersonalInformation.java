
public class PersonalInformation {

    private String name;
    private String address;
    private String mobile = "";

    public PersonalInformation(String name, String address, String mobile) {
        this.name = name;
        this.address = address;
        if(checkMobile(mobile))
            this.mobile = mobile;
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

    @Override
    public String toString() {
        return getName() + "\t" + getMobile() + "\t" + getAddress() + "\n";
    }

    private boolean checkMobile(String mobile) {
        if(mobile.length()==10) {
            char[] numerals = mobile.toCharArray();
            for(char num : numerals) {
                if(num>'9' || num<'0') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
