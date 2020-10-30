package com.wechat.testcase;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelp;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * @program: testAppium
 * @description: 测试
 * @author: mengmeng
 * @create: 2020-10-27 17:45
 **/
public class TestClass {
    private static final Logger logger=LoggerFactory.getLogger(TestClass.class);
    public static String access_token;
    public static String departmentId;
    /*
    获取所需access_token
     */
    @BeforeAll
    public static void
    getMethod() {
        access_token = TokenHelp.getAccessToken();
        logger.info("access_token: " + access_token);
    }
    /*
    线程安全
     */

    @Description("通讯录创建部门")
    @RepeatedTest(3)//执行次数
    @Execution(CONCURRENT)//CONCURRENT表示支持多线程
    void creatDepartment() {
        Response response=DepartmentApiObject.creatDepartment("11ame11",access_token);
        if(response.path("id")!=null){
            departmentId=response.path("id").toString();
        }
        else{
            logger.error("未获取到部门id");
        }
        assertEquals("0" +
                "",response.path("errcode").toString());
    }
}
