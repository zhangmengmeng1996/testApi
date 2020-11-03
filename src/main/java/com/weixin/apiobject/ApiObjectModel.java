package com.weixin.apiobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: testAppium
 * @description:yaml的model基本读取
 * @author: mengmeng
 * @create: 2020-11-02 12:19
 **/
public class ApiObjectModel {
    public String name;
    public HashMap<String,ApiObjectMethodModel> methods;
    //jackson读取文件
    /*
    代表每一个apiobject
     */
    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
         return objectMapper.readValue(new File(path),ApiObjectModel.class);

    }
    /*
    运行
     */
    public Response run(ApiObjectMethodModel method){

        Response response=method.run();
        return response;
    }
    public void run(){

    }
}
