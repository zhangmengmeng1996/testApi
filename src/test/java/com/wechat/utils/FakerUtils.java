package com.wechat.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: testAppium
 * @description:获取时间戳
 * @author: mengmeng
 * @create: 2020-10-28 15:53
 **/
public class FakerUtils {
    private static final Logger logger=LoggerFactory.getLogger(FakerUtils.class);
    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }
    @Test
    void test(){
        logger.info(getTimeStamp());
    }
}
