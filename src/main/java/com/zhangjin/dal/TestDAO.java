package com.zhangjin.dal;

import com.zhangjin.dal.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by siiiriu on 2020/8/9.
 */
public interface TestDAO extends JpaRepository<TestEntity, Long>{
}
