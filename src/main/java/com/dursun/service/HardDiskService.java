package com.dursun.service;

import com.dursun.dao.HardDiskDao;
import com.dursun.model.HardDisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by drsnkrt on 28.08.2017.
 */
@Service
public class HardDiskService implements IHardDiskService {

    @Autowired
    private HardDiskDao hardDiskDao;

    @Override
    @Transactional
    public void save(HardDisk hardDisk) {
        hardDiskDao.save(hardDisk);
    }

    @Override
    @Transactional
    public void delete(HardDisk hardDisk) {
        hardDiskDao.delete(hardDisk);
    }

    @Override
    @Transactional
    public HardDisk getById(int id) {
        return hardDiskDao.getById(id);
    }

    @Override
    @Transactional
    public List<HardDisk> getAllByStatus() {
        return hardDiskDao.getAllByStatus();
    }

    @Override
    @Transactional
    public List<HardDisk> getAll() {
        return hardDiskDao.getAll();
    }
}
