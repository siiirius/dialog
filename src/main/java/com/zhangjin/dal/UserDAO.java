package com.zhangjin.dal;

import com.zhangjin.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by siiiriu on 2020/8/9.
 */
public interface UserDAO extends JpaRepository<UserEntity, Long> {


    List<UserEntity> findByName(String name);
}
