package com.zhangjin.servvice.impl;

import com.zhangjin.common.result.BaseResult;
import com.zhangjin.common.result.DataResult;
import com.zhangjin.dal.UserDAO;
import com.zhangjin.dal.entity.UserEntity;
import com.zhangjin.servvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by siiiriu on 2020/8/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public DataResult<UserEntity> register(UserEntity userEntity) {
        UserEntity save = userDAO.save(userEntity);
        return new DataResult<>(save);
    }

    @Override
    public BaseResult login(UserEntity userEntity) {
        List<UserEntity> list = userDAO.findByName(userEntity.getName());
        BaseResult error = new BaseResult(403, "name or password is incorrect.");
        if (CollectionUtils.isEmpty(list)) {
            return error;
        }
        UserEntity exist = list.get(0);
        if (Objects.equals(exist.getPassword(), userEntity.getPassword())) {
            return new BaseResult();
        }
        return error;
    }
}
