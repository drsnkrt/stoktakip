package com.dursun.service;

import com.dursun.model.Personel;

import java.util.List;

/**
 * Created by drsnkrt on 7.09.2017.
 */
public interface IPersonelService {

    void saveOrUpdate(Personel personel);

    void delete(Personel personel);

    Personel getById(long id);

    List<Personel> getAll();
}
