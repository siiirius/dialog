package com.zhangjin.servvice;

import com.zhangjin.common.result.BaseResult;
import com.zhangjin.common.result.DataResult;
import com.zhangjin.dal.entity.UserEntity;

/**
 * Created by siiiriu on 2020/8/9.
 */

public interface UserService {


    DataResult<UserEntity> register(UserEntity userEntity);


    BaseResult login(UserEntity userEntity);

}
