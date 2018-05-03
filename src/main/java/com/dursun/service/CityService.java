package com.dursun.service;

import com.dursun.dao.ICityDao;
import com.dursun.model.City;
import com.dursun.model.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by drsnkrt on 23.08.2017.
 */
@Service
public class CityService implements ICityService {

    @Autowired
    private ICityDao cityDao;

    @Override
    @Transactional
    public List<City> getAllCity() {
        return cityDao.getAllCity();
    }

    @Override
    @Transactional
    public City getCityById(int id) {
        return cityDao.getCityById(id);
    }

    @Override
    @Transactional
    public List<Town> getTownsByCityID(int id) {
        return cityDao.getTownsByCityId(id);
    }


}
