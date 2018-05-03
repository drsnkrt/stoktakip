package com.dursun.model;

import javax.persistence.*;

/**
 * Created by drsnkrt on 5.09.2017.
 */
@Entity
@Table(name = "t_recordstatus")
public class RecordStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status;

    @Override
    public String toString() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
