package courseProject;

public class Customer {
    private String id;
    private String ssn;
    private String lastName;
    private String firstName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private Account account;
    
    public Customer(String id, String ssn, String lastName, String firstName, String street, String city, String state, String zip, String phone) {
        this.id = id;
        this.ssn = ssn;
        this.lastName = lastName;
        this.firstName = firstName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.account = null;
    }
    
    public String getID() { return id; }
    public void setID(String id) { this.id = id; }
    
    public String getSsn() { return ssn; }
    public void setSsn(String ssn) { this.ssn = ssn; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public void setAccount(Account account) {
    	this.account = account;
    }
    
    public Account getAccount() {
    	return account;
    }
    
    @Override
    public String toString() {
        return "Customer ID: " + id + "\n" +
                "SSN: " + ssn + "\n" +
                "Last Name: " + lastName + "\n" +
                "First Name: " + firstName + "\n" +
                "Street: " + street + "\n" +
                "City: " + city + "\n" +
                "State: " + state + "\n" +
                "Zip: " + zip + "\n" +
                "Phone: " + phone;
    }
}
