package com.weixin.apiobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @program: testAppium
 * @description: 驱动测试方法读取测试用例yaml文件提供读取测试用例执行
 * @author: mengmeng
 * @create: 2020-11-02 23:56
 **/
public class ApiTestCaseModel {
    public String name;
    public String description;
    public List<HashMap<String,Object>> steps;
    /*
    读取文件转换成apitestmodel的对象
     */
    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiTestCaseModel.class);
    }
    /*
    运用stream流来执行数据
     */
    public void run(BaseApi baseApi){
        steps.stream().forEach(step->{
       //     step.entrySet().forEach(entry->{
      //         baseApi.run(entry.getKey(),entry.getValue().toString());
       //     });
          baseApi.run(step.get("api").toString(),step.get("action").toString());
        });
    }
}
