package com.dursun.dao;


import com.dursun.model.*;

import java.util.List;

/**
 * Created by drsnkrt on 6.09.2017.
 */
public interface IExtraDao {

    List<Gender> getAllGender();

    List<RecordStatus> getAllRecordStatus();

    List<BloodGroup> getAllBloods();

    List<MaritalStatus> getAllMaritals();

    List<Title> getAllTitles();

}
