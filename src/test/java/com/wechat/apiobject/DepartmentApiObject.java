package com.wechat.apiobject;

import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * @program: testAppium
 * @description: 部门相关的方法对象
 * @author: mengmeng
 * @create: 2020-10-29 14:11
 **/
public class DepartmentApiObject {
    public static Response creatDepartment(String creatName,String access_token){
        String creatBody = "{\n" +
                "   \"name\": \""+creatName+"\",\n" +
                "   \"parentid\": 1,\n" +
                "}";

        Response response=given().contentType("application/json;charset=utf-8")
                .body(creatBody)
                .queryParam("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log()
                .all()
                .extract()
                .response();
        return response;

    }
    /*
    重写
     */
    public static Response creatDepartment(String access_token){
        String creatName="createName"+FakerUtils.getTimeStamp();
        return DepartmentApiObject.creatDepartment(creatName,access_token);
    }
    public static Response updateDepartment(String updateName,String departmentId,String access_token){
        String updateBody = "{\n" +
                "" +
                "   \"name\": \""+updateName+"\",\n" +
                "   \"id\": "+departmentId+",\n" +
                "}";
        Response updateResponse = given()
                .contentType("application/json;charset=utf-8")
                .body(updateBody)
                .queryParam("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then()
                .log()
                .all().extract().response();
        return updateResponse;
    }
    /*
    根据id查询部门
     */
    public static Response listDepartment(String departmentId,String access_token){
        Response listResponse=given()
                .params("id", departmentId, "access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log()
                .all().extract().response();
        return listResponse;
    }
    /*
    删除部门
     */
    public static Response deleteDepartment(String departmentId,String access_token){
        Response deleteResponse=given()
                .params("id", departmentId, "access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log()
                .all().extract().response();
        return deleteResponse;
    }
}
