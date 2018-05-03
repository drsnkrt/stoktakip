package com.dursun.model;

import net.sf.ehcache.config.PersistenceConfiguration;

import javax.persistence.*;
import java.util.List;

/**
 * Created by drsnkrt on 23.08.2017.
 */

@Entity
@Table(name = "T_TOWN")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "townId")
    private int townId;

    @Column(name = "townName")
    private String townName;

    @ManyToOne
    @JoinColumn(name = "CityId")
    private City city;

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return townName;
    }
}
