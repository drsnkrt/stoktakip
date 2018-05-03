package com.dursun.dao;

import com.dursun.model.Personel;

import java.util.List;

/**
 * Created by drsnkrt on 7.09.2017.
 */
public interface IPersonelDao {

    void saveOrUpdate(Personel personel);

    void delete(Personel personel);

    Personel getById(long id);

    List<Personel> getAll();
}
