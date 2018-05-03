package com.dursun.dao;

import com.dursun.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by drsnkrt on 6.09.2017.
 */
@Component
public class ExtraDao implements IExtraDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session gcs() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Gender> getAllGender() {
        return gcs().createQuery("from Gender").getResultList();
    }

    @Override
    public List<RecordStatus> getAllRecordStatus() {
        return gcs().createQuery("from RecordStatus").getResultList();
    }

    @Override
    public List<BloodGroup> getAllBloods() {
        return gcs().createQuery("from BloodGroup").getResultList();
    }

    @Override
    public List<MaritalStatus> getAllMaritals() {
        return gcs().createQuery("from MaritalStatus").getResultList();
    }

    @Override
    public List<Title> getAllTitles() {
        return gcs().createQuery("from Title").getResultList();
    }


}
