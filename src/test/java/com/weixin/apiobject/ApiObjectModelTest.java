package com.weixin.apiobject;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class ApiObjectModelTest {
    public static  ApiObjectModel apiObjectModel;
    @BeforeAll
    static void beforeAll(){
        try {
            apiObjectModel=ApiObjectModel.load("C:/Users/ZhangMengm/.ssh/workspace/testApi/src/main/resources/com.weixin.apiobject/api/wework_api.yaml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void load() {
        assertThat(apiObjectModel.name,equalTo("wework"));
    }

    @Test
    void run() {
        Response response=apiObjectModel.methods.get("get_token").run();
        response.then().statusCode(200);
    }
}