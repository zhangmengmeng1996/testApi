package com.wechat.task;

import com.wechat.apiobject.DepartmentApiObject;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @program: testAppium
 * @description: 环境管理任务
 * @author: mengmeng
 * @create: 2020-10-30 13:55
 **/
public class EvnTask {
    private static final Logger logger = LoggerFactory.getLogger(EvnTask.class);
    public static void evnClick(String access_token){
        Response response = DepartmentApiObject.listDepartment("",access_token);
        ArrayList<Integer> departmentIdList=response.path("department.id");
        for(int departmentId:departmentIdList){
            logger.info(departmentId+"");
            //根据部门id依次删除部门
            DepartmentApiObject.deleteDepartment(String.valueOf(departmentId),access_token);
        }
    }
}
