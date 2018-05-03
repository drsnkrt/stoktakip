package com.dursun.dao;

import com.dursun.model.MainBoard;

import java.util.List;

/**
 * Created by drsnkrt on 26.07.2017.
 */
public interface IMainBoardDao {

    void save(MainBoard mainBoard);

    void delete(MainBoard mainBoard);

    MainBoard getById(int id);

    List<MainBoard> getAll();


}
