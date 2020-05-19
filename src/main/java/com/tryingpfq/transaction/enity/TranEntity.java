package com.tryingpfq.transaction.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author tryingpfq
 * @date 2020/5/19
 **/
@Entity(name = "TranEntity")
public class TranEntity {
    @Id
    private int id;

    @Column
    private String name;


    public static TranEntity valueOf(int id, String name) {
        TranEntity entity = new TranEntity();
        entity.setId(id);
        entity.setName(name);
        return entity;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
