package com.dursun.service;

import com.dursun.model.MainBoard;

import java.util.List;

/**
 * Created by drsnkrt on 24.07.2017.
 */
public interface IMainBoardService {

    void save(MainBoard mainBoard);

    void delete(MainBoard mainBoard);

    MainBoard getById(int id);

    List<MainBoard> getAll();

}
