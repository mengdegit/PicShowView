package com.joker.picshowview.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.joker.picshowview.gen.DaoSession;
import com.joker.picshowview.gen.UserDao;

/**
 * Created by aa on 2017/7/31.
 */
@Entity
public class User {

    @Id(autoincrement = true)
    @Unique
    private Long id;

    private Long userId;
    @NotNull
    private String name;
    @Transient
    private int tem;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Generated(hash = 672567375)
    public User(Long id, Long userId, @NotNull String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
    @Generated(hash = 586692638)
    public User() {
    }

}
