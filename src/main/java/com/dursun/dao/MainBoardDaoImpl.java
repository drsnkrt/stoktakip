package com.dursun.dao;


import com.dursun.model.MainBoard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by drsnkrt on 26.07.2017.
 */
@Component
public class MainBoardDaoImpl implements IMainBoardDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session gcs() {
        return sessionFactory.getCurrentSession();
    }

    public void save(MainBoard mainBoard) {
        gcs().saveOrUpdate(mainBoard);
    }

    @Override
    public void delete(MainBoard mainBoard) {
        gcs().delete(mainBoard);
    }

    @Override
    public MainBoard getById(int id) {

        Query query = gcs().createQuery("from MainBoard where id = :parametre");
        query.setParameter("parametre", id);

        List<MainBoard> list = query.list();

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }

    }


    @Override
    public List<MainBoard> getAll() {

        return gcs().createQuery("from MainBoard").getResultList();
    }
}
