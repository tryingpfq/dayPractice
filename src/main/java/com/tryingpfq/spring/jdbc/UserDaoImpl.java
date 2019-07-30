package com.tryingpfq.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2019/7/30 10:41
 */

public class UserDaoImpl implements IUserDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        //jdbcTemplate.update("insert into user(name,age,sex) values (?,?,?)");
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
