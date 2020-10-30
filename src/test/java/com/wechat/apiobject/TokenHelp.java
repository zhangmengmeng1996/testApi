package com.wechat.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @program: testAppium
 * @description: 获取token
 * @author: mengmeng
 * @create: 2020-10-30 13:51
 **/
public class TokenHelp {
    /*
    获取token
     */
    public static String getAccessToken(){
       String  access_token = given()
                .params("corpid", "ww70c27c0a62bde38a", "corpsecret", "VYft5rbVYNG4psycXuuS7fN1jDrH8wP-d5_VsIi6iEg")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().extract().response().path("access_token");
       return access_token;
    }
}
