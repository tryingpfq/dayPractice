package com.tryingpfq.transaction.dao;

import com.tryingpfq.transaction.enity.TranEntity;

/**
 * @author tryingpfq
 * @date 2020/5/19
 **/
public interface ITranDao {

    void save(TranEntity entity);

    void update(TranEntity entity);

    void delete(TranEntity entity);
}
