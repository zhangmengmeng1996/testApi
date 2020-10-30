package com.wechat.testcase;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelp;
import com.wechat.task.EvnTask;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @program: testAppium
 * @description: 优化代码，增加随机生成时间戳避免随机生成随机数展示
 * @author: mengmeng
 * @create: 2020-10-28 15:28
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_4 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_4.class);
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
    创建部门超过32
     */
    @ParameterizedTest
    @Description("通讯录创建部门")
    @Order(1)
    @CsvFileSource(resources="/data/createDepartment.csv",numLinesToSkip = 1)
    void creatDepartment(String creatName,String returnCode) {
        Response response=DepartmentApiObject.creatDepartment(creatName,access_token);
        if(response.path("id")!=null){
            departmentId=response.path("id").toString();
        }
        else{
            logger.error("未获取到部门id");
        }
        assertEquals(returnCode,response.path("errcode").toString());
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