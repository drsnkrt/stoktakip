package com.dursun.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by drsnkrt on 28.08.2017.
 */

@Entity
@Table(name = "t_harddisk")
public class HardDisk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "in_stock_date")
    private String inStockDate;

    @Column(name = "status")
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInStockDate() {
        return inStockDate;
    }

    public void setInStockDate(String inStockDate) {
        this.inStockDate = inStockDate;
    }
}
