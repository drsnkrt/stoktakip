package com.dursun.service;

import com.dursun.dao.IPersonelDao;
import com.dursun.model.Personel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by drsnkrt on 7.09.2017.
 */
@Service
public class PersonelService implements IPersonelService {

    @Autowired
    private IPersonelDao personelDao;

    @Override
    @Transactional
    public void saveOrUpdate(Personel personel) {
        personelDao.saveOrUpdate(personel);
    }

    @Override
    @Transactional
    public void delete(Personel personel) {
        personelDao.delete(personel);
    }

    @Override
    @Transactional
    public Personel getById(long id) {
        return personelDao.getById(id);
    }

    @Override
    @Transactional
    public List<Personel> getAll() {
        return personelDao.getAll();
    }
}
