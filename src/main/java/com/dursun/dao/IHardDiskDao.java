package com.dursun.dao;

import com.dursun.model.HardDisk;

import java.util.List;

/**
 * Created by drsnkrt on 28.08.2017.
 */
public interface IHardDiskDao {

    void save(HardDisk hardDisk);

    void delete(HardDisk hardDisk);

    HardDisk getById(int id);

    List<HardDisk> getAllByStatus();

    List<HardDisk> getAll();

}
