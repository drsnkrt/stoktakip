package com.dursun.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by drsnkrt on 7.09.2017.
 */
@Entity
@Table(name = "t_personel")
public class Personel {

    @Id
    @Column(name = "tckn", unique = true)
    private long tckn;

    @Column(name = "name")
    private String name;

    @Column(name = "surName")
    private String surName;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "city")
    private String city;

    @Column(name = "town")
    private String town;

    @Column(name = "maritalStatus")
    private String maritalStatus;

    @Column(name = "title")
    private String title;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "recordStatus")
    private String recordStatus;

    @Column(name = "address")
    private String address;


    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "image")
    private String image;

    public Personel() {
    }

    public long getTckn() {
        return tckn;
    }

    public void setTckn(long tckn) {
        this.tckn = tckn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
