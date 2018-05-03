package com.dursun.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by drsnkrt on 23.08.2017.
 */
@Entity
@Table(name = "T_CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CityId", unique = true)
    private int cityId;

    @Column(name = "cityName")
    private String cityName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
//    @JoinColumn(name = "townId")
    private List<Town> towns;

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }

}
