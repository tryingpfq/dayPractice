package com.tryingpfq.base;

import com.tryingpfq.transaction.enity.TranEntity;
import com.tryingpfq.transaction.service.TranService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tryingpfq
 * @date 2020/5/19
 **/
@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@Rollback
//@Transactional(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseJunit4Test {



}
