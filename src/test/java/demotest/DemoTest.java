package demotest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * @program: testAppium
 * @description: 测试
 * @author: mengmeng
 * @create: 2020-10-26 14:55
 **/
public class DemoTest {
    @Test
    void fun(){
        given()
                .get("https://www.baidu.com")
                .then()
                .statusCode(200)
                .log()
                .all();
    }
}
