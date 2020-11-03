package com.weixin.apiobject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: testAppium
 * @description:
 * @author: mengmeng
 * @create: 2020-11-02 15:51
 **/
public class BaseApi {
    List<ApiObjectModel> apis=new ArrayList<>();
    //读取文件并可以过滤
    /*
    加载所有的挨批文件
     */
    public  void load(String dir){
        Arrays.stream(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //filter
                return name.contains("_api");
            }
        })).forEach(path-> {
            try {
                System.out.println(dir+"/"+path);
                apis.add(ApiObjectModel.load(dir+"/"+path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    /*
    根据测试用例提供的api object从自己的数据中检索对用的api并调用对应的方法找到与测试用例名字相同的接口，
    根据对应action找到相应的接口执行对应的参数
     */
    public void run(String name,String action){
        apis.stream().filter(apiObjectModel -> apiObjectModel.name.equals(name))
                .forEach(apiObjectModel -> {
                    apiObjectModel.methods.get(action).run();
                });
    }
}
