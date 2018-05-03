package com.dursun.dao;

import com.dursun.model.Personel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by drsnkrt on 7.09.2017.
 */
@Component
public class PersonelDao implements IPersonelDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session gcs() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void saveOrUpdate(Personel personel) {
        gcs().saveOrUpdate(personel);
    }

    @Override
    public void delete(Personel personel) {
        gcs().delete(personel);
    }

    @Override
    public Personel getById(long id) {

        Query query = gcs().createQuery("from Personel where id = :parametre");
        query.setParameter("parametre", id);

        List<Personel> list = query.list();

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Personel> getAll() {
        return gcs().createQuery("from Personel").getResultList();
    }
}
