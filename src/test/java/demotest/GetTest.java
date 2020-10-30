package demotest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * @program: testAppium
 * @description: get请求
 * @author: mengmeng
 * @create: 2020-10-26 16:06
 **/
public class GetTest {
    //ww70c27c0a62bde38a
    //Gmc5QAzzYwqMGjGh9ynp8-aRN4L4NKmyNdvZIhcHXbE
    public static String access_token;
    @BeforeAll
    public static void getMethod(){
       access_token=given()
               .params("corpid","ww70c27c0a62bde38a","corpsecret","Gmc5QAzzYwqMGjGh9ynp8-aRN4L4NKmyNdvZIhcHXbE")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().extract().response().path("access_token");
        System.out.println(access_token);

    }
    /*
    post请求
     */
    @Test
    void postMethod(){
        given().contentType("application/json;charset=utf-8")
                .body("{\n" +
                "   \"touser\" : \"@all\",\n" +
                "   \"msgtype\" : \"text\",\n" +
                "   \"agentid\" : 1000002,\n" +
                "   \"text\" : {\n" +
                "       \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                "   },\n" +
                "}")
                .queryParam("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send").then().log().all();
    }
}

