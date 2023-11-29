package com.rentalhive.stockManagement.generatePDF;

public class TableRow {


        private String equipment;
        private String quantity;
        private String pricePerDay;
        private String priceTotal;
        public TableRow() {

        }
        public TableRow(String equipment, String quantity, String pricePerDay, String priceTotal) {
            this.equipment = equipment;
            this.quantity = quantity;
            this.pricePerDay = pricePerDay;
            this.priceTotal = priceTotal;
        }


        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getPricePerDay() {
            return pricePerDay;
        }

        public void setPricePerDay(String pricePerDay) {
            this.pricePerDay = pricePerDay;
        }

        public String getPriceTotal() {
            return priceTotal;
        }

        public void setPriceTotal(String priceTotal) {
            this.priceTotal = priceTotal;
        }

}
