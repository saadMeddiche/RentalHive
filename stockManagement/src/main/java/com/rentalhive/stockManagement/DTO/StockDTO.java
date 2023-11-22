package com.rentalhive.stockManagement.DTO;

public class StockDTO {
    private String registrationNumber;

/*
    private Status status;
*/

/*
    private User added_by;
*/
/*
private Equipment equipment;
*/


    public StockDTO() {
    }

    public StockDTO( String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}
