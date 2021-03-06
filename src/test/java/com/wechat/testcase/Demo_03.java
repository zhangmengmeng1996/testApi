package com.wechat.testcase;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelp;
import com.wechat.task.EvnTask;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * @program: testAppium
 * @description: 优化代码，增加随机生成时间戳避免随机生成随机数展示
 * @author: mengmeng
 * @create: 2020-10-28 15:28
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_03.class);
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
    查询全部部门id
     */
    @Test
    void evnClear(){
        //清除全部的部门
        EvnTask.evnClick(access_token);
    }
    /*
    创建部门
     */

    @Description("通讯录创建部门")
    @Order(1)
    @RepeatedTest(40)//执行次数
    @Execution(CONCURRENT)//CONCURRENT表示支持多线程
    void creatDepartment() {
        //创建名字时创建时间戳
        //线程号+时间戳避免了重复
        String backenStr=Thread.currentThread().getId()+FakerUtils.getTimeStamp();
        String creatName="createName"+backenStr;
        Response response=DepartmentApiObject.creatDepartment(creatName,access_token);
        if(response.path("id")!=null){
            departmentId=response.path("id").toString();
        }
        else{
            logger.error("未获取到部门id");
        }
        assertEquals("0",response.path("errcode").toString());
    }

    @Test
    @Description("通讯录更新部门")
    @Order(2)
    void updateDepartment() {
        Response response=DepartmentApiObject.creatDepartment(access_token);
        if(response.path("id")!=null){
            departmentId=response.path("id").toString();
        }
        else{
            logger.error("未获取到部门id");
        }
        String updateName="updateName"+FakerUtils.getTimeStamp();
        Response updateResponse = DepartmentApiObject.updateDepartment(updateName,departmentId,access_token);
        }

    @Test
    @Description("通讯录查询部门")
    @Order(3)
    void selectDepartment() {
        Response response=DepartmentApiObject.creatDepartment(access_token);
        if(response.path("id")!=null){
            departmentId=response.path("id").toString();
        }
        else{
            logger.error("未获取到部门id");
        }
        Response selectResponse =DepartmentApiObject.listDepartment(departmentId,access_token);
    }

    @Test
    @Description("通讯录删除部门")
    @Order(4)
    void deleteDepartment() {
        Response response=DepartmentApiObject.creatDepartment(access_token);
        if(response.path("id")!=null){
            departmentId=response.path("id").toString();
        }
        else{
            logger.error("未获取到部门id");
        }
        Response deleteResponse = DepartmentApiObject.deleteDepartment(departmentId,access_token);
       // assertEquals("0", response.path("errcode").toString());
    }

}