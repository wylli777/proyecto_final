package mypackage;
public class Customer {
    private int id;
    private int storeId;
    private String firstName;
    private String lastName;
    private int addressId;

    // Constructor
    public Customer(int id, int storeId, String firstName, String lastName, int addressId) {
        this.id = id;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", storeId=" + storeId + ", firstName='" + firstName + "', lastName='" + lastName + "', addressId=" + addressId + '}';
    }
}
