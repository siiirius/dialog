package com.zhangjin.dal.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by siiiriu on 2020/8/9.
 *
 */
@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "r")
    private String r;

    @Column(name = "gmt_create")
    private Date gmtCreate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
