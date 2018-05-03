package com.dursun.model;

import javax.persistence.*;

/**
 * Created by drsnkrt on 7.09.2017.
 */
@Entity
@Table(name = "t_bloodgroup")
public class BloodGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Override
    public String toString() {
        return blood;
    }

    @Column(name = "blood")
    private String blood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

}
