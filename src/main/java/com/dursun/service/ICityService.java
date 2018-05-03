package com.dursun.service;

import com.dursun.model.City;
import com.dursun.model.Town;

import java.util.List;

/**
 * Created by drsnkrt on 23.08.2017.
 */
public interface ICityService {

    List<City> getAllCity();

    City getCityById(int id);

    List<Town> getTownsByCityID(int id);
}
