package com.rentalhive.stockManagement.embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class Discount {

    @Positive
    @Max(100)
    @Min(0)
    @NotNull(message="The discount can not be null")
    private Double percentage;

    public Discount(){
    }
    public Discount(Double percentage){
        this.percentage = percentage;
    }


    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }



    public String getDiscount(){

        StringBuilder discount = new StringBuilder();

        discount.append("-");
        discount.append(this.percentage);
        discount.append("%");

        return discount.toString();
    }

    public Double getDiscountPrefix(){
        return this.percentage / 100;
    }


    @Override
    public String toString() {
        return "Discount{" +
                "percentage=" + getDiscount() +
                '}';
    }
}
