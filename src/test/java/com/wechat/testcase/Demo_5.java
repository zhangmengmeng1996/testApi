package com.wechat.testcase;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelp;
import com.wechat.task.EvnTask;
import com.wechat.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
//执行命令
// allure serve allure-results
/**
 * @program: testAppium
 * @description: 优化代码，增加随机生成时间戳避免随机生成随机数展示
 * @author: mengmeng
 * @create: 2020-10-28 15:28
 **/
//执行顺序
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("企业微信接口测试用例")
@Feature("部门相关功能测试")
public class Demo_5 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_5.class);
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
    @Description("清除数据的内容")
    @Story("清除全部部门")
    @DisplayName("清除部门")
    void evnClear(){
        //清除全部的部门
        EvnTask.evnClick(access_token);
    }
    /*
    创建部门超过32
     */
    @ParameterizedTest
    @io.qameta.allure.Description("Description这个测试方法会创建部门，csv文件驱动 ")
    @DisplayName("DisplayName通讯录创建部门")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("TmsLink test-1")
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
    @io.qameta.allure.Description("Description这个测试方法会更新部门 ")
    @DisplayName("DisplayName通讯录更新部门")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("TmsLink test-1")
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
    @io.qameta.allure.Description("Description这个测试方法会查询部门，csv文件驱动 ")
    @DisplayName("DisplayName通讯录查询部门")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TmsLink test-1")
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
        assertAll("返回值的校验！",
                ()->assertEquals("1",selectResponse.path("errcode").toString()),
                ()->assertEquals("2",selectResponse.path("department.id[0]").toString()));
        //assertEquals("0",selectResponse.path("errcode").toString());
        //assertEquals(departmentId,selectResponse.path("department.id[0]").toString());
    }

    @Test
    @io.qameta.allure.Description("Description这个测试方法会删除部门，csv文件驱动 ")
    @DisplayName("DisplayName通讯录删除部门")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TmsLink test-1")
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