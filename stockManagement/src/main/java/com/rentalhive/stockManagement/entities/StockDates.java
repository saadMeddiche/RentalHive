package com.rentalhive.stockManagement.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StockDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Stock stock;

    private LocalDateTime date_reservation;

    private LocalDateTime date_expiration;

    public StockDates() {

    }

    public StockDates(Stock stock, LocalDateTime date_reservation, LocalDateTime date_expiration) {
        this.stock = stock;
        this.date_reservation = date_reservation;
        this.date_expiration = date_expiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public LocalDateTime getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDateTime date_reservation) {
        this.date_reservation = date_reservation;
    }

    public LocalDateTime getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDateTime date_expiration) {
        this.date_expiration = date_expiration;
    }
}
