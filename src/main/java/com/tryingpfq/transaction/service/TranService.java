package com.tryingpfq.transaction.service;

import com.tryingpfq.transaction.dao.ITranDao;
import com.tryingpfq.transaction.dao.TranDaoImpl;
import com.tryingpfq.transaction.enity.TranEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tryingpfq
 * @date 2020/5/19
 **/
//@Transactional
@Service
public class TranService {
    @Autowired
    private ITranDao dao;   //注意这里要基于接口的注入哦

    public void save(TranEntity entity) {
        dao.save(entity);
        int i = 5;
    }

    public void update(TranEntity entity) {
        dao.update(entity);
    }

    public void delete(TranEntity entity) {
        dao.delete(entity);
    }
}
