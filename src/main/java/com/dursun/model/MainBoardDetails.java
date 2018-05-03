package com.dursun.model;

import javax.persistence.*;

/**
 * Created by drsnkrt on 21.08.2017.
 */
@Entity
@Table(name = "t_main_board_details")
public class MainBoardDetails {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "serial_number", length = 30)
    private String serialNumber;

    @Column(name = "processor_type")
    private String processorType;

    @Column(name = "image")
    private String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

}
