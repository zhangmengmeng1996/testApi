package com.weixin.apiobject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class BaseApiTest {
    public static BaseApi baseApi;
    @BeforeAll
    static void beforAll(){
        baseApi=new BaseApi();
        baseApi.load("C:/Users/ZhangMengm/.ssh/workspace/testApi/src/main/resources/com.weixin.apiobject/api");

    }

    @Test
    void load() {
        //harmcrest断言
        assertThat(baseApi.apis.size(), equalTo(2));
    }

    @Test
    void run() {
        baseApi.run("wework","get_token");
        baseApi.run("tags","list");

    }
}