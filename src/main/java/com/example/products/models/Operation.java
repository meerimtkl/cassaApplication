package com.example.products.models;

import com.example.products.enums.OperStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class Operation {
    private Integer id;
    private LocalDateTime addDate;

    private double total;
    private OperStatus status;

    public Integer getId() {
        return id;
    }

    public Operation() {
    }

    public Operation(LocalDateTime addDate, double total) {
        this.addDate = addDate;
        this.total = total;
    }

    public Operation(Integer id, LocalDateTime addDate, double total, OperStatus status) {
        this.id = id;
        this.addDate = addDate;
        this.total = total;
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OperStatus getStatus() {
        return status;
    }

    public void setStatus(OperStatus status) {
        this.status = status;
    }
}
