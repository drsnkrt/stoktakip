package com.dursun.dao;

import com.dursun.model.City;
import com.dursun.model.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by drsnkrt on 23.08.2017.
 */
@Component
public class CityDaoImpl implements ICityDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session gcs() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<City> getAllCity() {
        return gcs().createQuery("from City").getResultList();
    }

    @Override
    public City getCityById(int id) {

        Query query = gcs().createQuery("from City where id = :parametre");
        query.setParameter("parametre", id);

        List<City> list = query.list();

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Town> getTownsByCityId(int id) {

        Query query = gcs().createQuery("from Town where cityId = :parametre");
        query.setParameter("parametre", id);

        List<Town> list = query.list();

        if (list.size() == 0) {
            return null;
        } else {
            return query.list();
        }
    }

}
