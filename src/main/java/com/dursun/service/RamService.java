package com.dursun.service;

import com.dursun.dao.IRamDao;
import com.dursun.model.Ram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by drsnkrt on 24.08.2017.
 */
@Service
public class RamService implements IRamService {

    @Autowired
    private IRamDao ramDao;


    @Override
    @Transactional
    public void save(Ram ram) {
        ramDao.save(ram);
    }

    @Override
    @Transactional
    public void delete(Ram ram) {
        ramDao.delete(ram);
    }

    @Override
    @Transactional
    public Ram getById(int id) {
        return ramDao.getById(id);
    }

    @Override
    @Transactional
    public List<Ram> getAll() {
        return ramDao.getAll();
    }
}
