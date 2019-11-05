
public class Customer {

    private String name;
    private int mobileNumber;
    private String email;

    private int tID; //here or other class (add in booking class)

    public Customer(String name, int mobileNumber, String email){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobileNumber(int number) {
        this.mobileNumber = number;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
