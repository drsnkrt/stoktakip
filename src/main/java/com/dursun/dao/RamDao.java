package com.dursun.dao;

import com.dursun.model.Ram;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by drsnkrt on 24.08.2017.
 */
@Component
public class RamDao implements IRamDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session gcs() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Ram ram) {
        gcs().saveOrUpdate(ram);
    }

    @Override
    public void delete(Ram ram) {
        gcs().delete(ram);
    }

    @Override
    public Ram getById(int id) {

        Query query = gcs().createQuery("from Ram where id = :parametre");
        query.setParameter("parametre", id);

        List<Ram> list = query.list();

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Ram> getAll() {

        return gcs().createQuery("from Ram").getResultList();

    }
}
