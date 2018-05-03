package com.dursun.service;

import com.dursun.dao.ExtraDao;
import com.dursun.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by drsnkrt on 6.09.2017.
 */
@Service
public class ExtraService implements IExtraService {

    @Autowired
    private ExtraDao extraDao;

    @Override
    @Transactional
    public List<Gender> getAllGender() {
        return extraDao.getAllGender();
    }

    @Override
    @Transactional
    public List<RecordStatus> getAllRecordStatus() {
        return extraDao.getAllRecordStatus();
    }

    @Override
    @Transactional
    public List<BloodGroup> getAllBloods() {
        return extraDao.getAllBloods();
    }

    @Override
    @Transactional
    public List<MaritalStatus> getAllMaritals() {
        return extraDao.getAllMaritals();
    }

    @Override
    @Transactional
    public List<Title> getAllTitles() {
        return extraDao.getAllTitles();
    }
}
