package com.dursun.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by drsnkrt on 24.07.2017.
 */
@Entity
@Table(name = "t_main_board")
public class MainBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "brand", length = 15)
    private String brand;

    @Column(name = "model", length = 20)
    private String model;

    @Temporal(TemporalType.DATE)
    @Column(name = "in_stock_date")
    private Date inStockDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailsId")
    private MainBoardDetails mainBoardDetails;

    public MainBoardDetails getMainBoardDetails() {
        return mainBoardDetails;
    }

    public void setMainBoardDetails(MainBoardDetails mainBoardDetails) {
        this.mainBoardDetails = mainBoardDetails;
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

    public Date getInStockDate() {
        return inStockDate;
    }

    public void setInStockDate(Date inStockDate) {
        this.inStockDate = inStockDate;
    }
}
