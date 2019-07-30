package com.tryingpfq.spring.jdbc;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2019/7/30 10:40
 */
public interface IUserDao {
    public void save(User user);

    public List<User> getUsers();
}
