package com.wechat.testcase;

import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @program: testAppium
 * @description: 部门相关接口测试
 * @author: mengmeng
 * @create: 2020-10-27 21:14
 **/
public class Demo_1 {
    private static final Logger logger = LoggerFactory.getLogger(TestClass.class);
    public static String access_token;

    @BeforeAll
    public static void getMethod() {
        access_token = given()
                .params("corpid", "ww70c27c0a62bde38a", "corpsecret", "VYft5rbVYNG4psycXuuS7fN1jDrH8wP-d5_VsIi6iEg")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().extract().response().path("access_token");
        logger.info("access_token: " + access_token);

    }

    /*
    创建部门
     */
    @Test
    @Description("通讯录创建部门")
    void creatDepartment() {
        String creatBody = "{\n" +
                "   \"name\": \"广州研发中心4\",\n" +
                "   \"parentid\": 1,\n" +
                "}";

        given().contentType("application/json;charset=utf-8")
                .body(creatBody)
                .queryParam("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log()
                .all()
                .body("errcode", equalTo(0));

    }

    @Test
    @Description("通讯录更新部门")
    void updateDepartment() {
        String updateBody = "{\n" +
                "   \"name\": \"广州心4\",\n" +
                "   \"id\": 3,\n" +
                "}";
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body(updateBody)
                .queryParam("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then()
                .log()
                .all().extract().response();
        ;

    }

    @Test
    @Description("通讯录查询部门")
    void selectDepartment() {
        Response response = given()
                .params("id", "3", "access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log()
                .all().extract().response();
    }

    @Test
    @Description("通讯录删除部门")
    void deleteDepartment() {
        Response response = given()
                .params("id", "3", "access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log()
                .all().extract().response();
        assertEquals("0", response.path("errcode"));
    }

}
