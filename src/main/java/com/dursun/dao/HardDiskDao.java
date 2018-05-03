package com.dursun.dao;

import com.dursun.model.HardDisk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by drsnkrt on 28.08.2017.
 */
@Component
public class HardDiskDao implements IHardDiskDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session gcs() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(HardDisk hardDisk) {
        gcs().saveOrUpdate(hardDisk);
    }

    @Override
    public void delete(HardDisk hardDisk) {
        gcs().delete(hardDisk);
    }

    @Override
    public HardDisk getById(int id) {
        Query query = gcs().createQuery("from HardDisk where id = :parametre");
        query.setParameter("parametre", id);

        List<HardDisk> list = query.list();

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<HardDisk> getAllByStatus() {

        return gcs().createQuery("from HardDisk where status = true").getResultList();

    }

    @Override
    public List<HardDisk> getAll() {
        return gcs().createQuery("from HardDisk").getResultList();
    }
}
