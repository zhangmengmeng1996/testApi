package com.wechat.testcase;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: testAppium
 * @description: 测试
 * @author: mengmeng
 * @create: 2020-10-27 17:45
 **/
public class TestClass {
    private static final Logger logger=LoggerFactory.getLogger(TestClass.class);
    @Test
    void test(){
        logger.info("hello word");
    }
}
