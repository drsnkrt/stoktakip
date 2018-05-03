package com.dursun.service;

import com.dursun.dao.IMainBoardDao;
import com.dursun.model.MainBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by drsnkrt on 24.07.2017.
 */
@Service
public class MainBoardServiceImpl implements IMainBoardService {

    @Autowired
    private IMainBoardDao mainBoardDao;

    @Override
    @Transactional
    public void save(MainBoard mainBoard) {
        mainBoardDao.save(mainBoard);
    }

    @Override
    @Transactional
    public void delete(MainBoard mainBoard) {
        mainBoardDao.delete(mainBoard);
    }


    @Override
    @Transactional
    public MainBoard getById(int id) {

        return mainBoardDao.getById(id);
    }

    @Override
    @Transactional
    public List<MainBoard> getAll() {
        return mainBoardDao.getAll();
    }
}
