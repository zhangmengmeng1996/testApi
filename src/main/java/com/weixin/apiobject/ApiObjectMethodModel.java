package com.weixin.apiobject;
import io.restassured.response.Response;

import java.util.HashMap;
import static io.restassured.RestAssured.given;

/**
 * @program: testAppium
 * @description:代表运行一个单一的http接口
 * @author: mengmeng
 * @create: 2020-11-02 12:22
 **/
public class ApiObjectMethodModel {
    public String method="get";
    public String url;
    public HashMap<String,Object> query;
    public String save;
    public HashMap<String,Object> json;
    public String post;
    public String get;
    /*
   发起请求获取相应
     */
    public  Response run() {
        if(post!=null){
            return given()
                    .queryParams(query)
                    .post(post)
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();}
         if (get!=null){
             return given()
                     .queryParams(query)
                     .get(get)
                     .then()
                     .log()
                     .all()
                     .extract()
                     .response();
         }
        Response response=given()
                .queryParams(query)
                .request(method, url)
                .then()
                .log()
                .all()
                .extract()
                .response();
        return response;
    }
}
