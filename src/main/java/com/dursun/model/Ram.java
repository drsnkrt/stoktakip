package com.dursun.model;

import net.sf.ehcache.config.PersistenceConfiguration;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by drsnkrt on 24.08.2017.
 */
@Entity
@Table(name = "t_ram")
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "type")
    private String type;

    @Temporal(TemporalType.DATE)
    @Column(name = "in_stock_date")
    private Date inStockDate;

    @Override
    public String toString() {
        return brand;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getInStockDate() {
        return inStockDate;
    }

    public void setInStockDate(Date inStockDate) {
        this.inStockDate = inStockDate;
    }
}
