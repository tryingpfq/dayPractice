package com.tryingpfq.tran;

import com.tryingpfq.base.BaseJunit4Test;
import com.tryingpfq.transaction.enity.TranEntity;
import com.tryingpfq.transaction.service.TranService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tryingpfq
 * @date 2020/5/19
 **/
public class TranTest extends BaseJunit4Test {
    @Autowired
    private TranService tranService;

    @Before
    public void before(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");   //该设置用于输出jdk动态代理
    }

    @Test
    public void tranSave() {
        TranEntity entity = TranEntity.valueOf(12345678, "peng");
        try {
            tranService.save(entity);
        }finally {

        }
    }

    @Test
    public void update(){
        TranEntity entity = TranEntity.valueOf(1, "peng");
        try {
            tranService.update(entity);
        }finally {

        }
    }

    @Test
    public void delete() {
        TranEntity entity = TranEntity.valueOf(1, "peng");
        tranService.delete(entity);
    }
}
