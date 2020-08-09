package com.zhangjin;

import com.zhangjin.dal.TestDAO;
import com.zhangjin.dal.entity.TestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by siiiriu on 2020/8/9.
 */
@SpringBootTest
public class TestDAOTest {


    @Autowired
    private TestDAO testDAO;

    @Test
    public void test(){
        TestEntity s = new TestEntity();
        s.setName("junit test");
        TestEntity save = testDAO.save(s);

    }
}
