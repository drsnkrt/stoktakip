package com.dursun.dao;


import com.dursun.model.Ram;

import java.util.List;

/**
 * Created by drsnkrt on 24.08.2017.
 */
public interface IRamDao {

    void save(Ram ram);

    void delete(Ram ram);

    Ram getById(int id);

    List<Ram> getAll();


}
