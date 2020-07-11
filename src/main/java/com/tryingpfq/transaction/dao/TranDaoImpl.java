package com.tryingpfq.transaction.dao;

import com.tryingpfq.transaction.enity.TranEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tryingpfq
 * @date 2020/5/19
 **/
@Repository
@Transactional(rollbackFor = Exception.class)
public class TranDaoImpl implements ITranDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(TranEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            session.getTransaction().commit();// 如果这里提交了的话 应该是不会回滚的 但不确定 暂时的理解
            int i = 5;
            int a = 1 / (i - 5);
        } catch (Exception e) {
            transaction.rollback();
        }

    }

    @Override
    public void update(TranEntity entity) {
        save(entity);
    }

    @Override
    public void delete(TranEntity entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        //session.close();
    }
}
