package com.weixin.apiobject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class ApiTestCaseModelTest {
    public static BaseApi baseApi;
    public static ApiTestCaseModel apiTestCaseModel;
    @BeforeAll
    static void beforeAll() throws IOException {
        baseApi=new BaseApi();
        baseApi.load("C:/Users/ZhangMengm/.ssh/workspace/testApi/src/main/resources/com.weixin.apiobject/api");
       apiTestCaseModel=ApiTestCaseModel.load("C:/Users/ZhangMengm/.ssh/workspace/testApi/src/main/resources/com.weixin.apiobject/test_add.yaml");

    }

    @Test
    void load() throws IOException {
        assertThat(apiTestCaseModel.name,equalTo("add"));
    }

    @Test
    void run() {
        apiTestCaseModel.run(baseApi);

    }
}