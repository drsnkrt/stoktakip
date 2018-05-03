package com.dursun.model;

import javax.persistence.*;

/**
 * Created by drsnkrt on 7.09.2017.
 */
@Entity
@Table(name = "t_maritalstatus")
public class MaritalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "marital")
    private String marital;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return marital;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }
}
