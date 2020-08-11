package com.zhangjin.servvice.impl;

import com.zhangjin.common.result.BaseResult;
import com.zhangjin.common.result.DataResult;
import com.zhangjin.dal.UserDAO;
import com.zhangjin.dal.entity.UserEntity;
import com.zhangjin.servvice.UserService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by siiiriu on 2020/8/9.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public DataResult<UserEntity> register(UserEntity userEntity) {
        try {
            List<UserEntity> entity = userDAO.findByName(userEntity.getName());
            if (!CollectionUtils.isEmpty(entity)) {
                return new DataResult<>(601, "name is already in use.");
            }
            userEntity.setR(UUID.randomUUID().toString());
            userEntity.setPassword(sha(userEntity.getPassword(), userEntity.getR()));
            userEntity.setGmtCreate(new Date());
            UserEntity save = userDAO.save(userEntity);
            return new DataResult<>(save);
        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<>(500, "error");
        }
    }

    @Override
    public BaseResult login(UserEntity userEntity) {
        BaseResult error = new BaseResult(403, "name or password is incorrect.");
        try {
            List<UserEntity> list = userDAO.findByName(userEntity.getName());
            if (CollectionUtils.isEmpty(list)) {
                return error;
            }
            UserEntity exist = list.get(0);
            String sha = sha(userEntity.getPassword(), exist.getR());

            if (Objects.equals(exist.getPassword(), sha)) {
                return new BaseResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }

    private String sha(String password, String r) throws Exception {

        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update((password + r).getBytes("utf8"));
        return Hex.encodeHexString(instance.digest());
    }


}
