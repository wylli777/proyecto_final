package mypackage;

import java.sql.Timestamp;

// Clase de Renta
public class Rental {
    private int rentalId;
    private int customerId;
    private int inventoryId;
    private String rentalDate;
    private String returnDate;

    // Constructor
    public Rental(int rentalId, int customerId, int inventoryId, String rentalDate, String returnDate) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.inventoryId = inventoryId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Rental(int rentalId2, Timestamp rentalDate2, int inventoryId2, int customerId2, Timestamp returnDate2,
            int staffId) {
        //TODO Auto-generated constructor stub
    }

    // Getters y Setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Rental{rentalId=" + rentalId + ", customerId=" + customerId + ", inventoryId=" + inventoryId + ", rentalDate='" + rentalDate + "', returnDate='" + returnDate + "'}";
    }
}