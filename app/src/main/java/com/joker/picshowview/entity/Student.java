package com.joker.picshowview.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by aa on 2017/8/2.
 */
@Entity
public class Student {

    @Id(autoincrement = true)
    @Unique
    private Long id;

    @Unique
    private String num;

    private int port;
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPort() {
        return this.port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1794669755)
    public Student(Long id, String num, int port, String name) {
        this.id = id;
        this.num = num;
        this.port = port;
        this.name = name;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }



}
