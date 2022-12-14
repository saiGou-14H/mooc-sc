package com.org.controller;


import com.org.model.Practice;
import com.org.model.Result;
import com.org.model.StudentCourse;
import com.org.model.StudentPractice;
import com.org.service.IStudentPracticeService;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生实践关联表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@RestController
@RequestMapping("/studentPractice")
public class StudentPracticeController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    private IStudentPracticeService studentPracticeService;
//    @Autowired
//    private IStudentClass
    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";

    /*
    * add
    * */
    @ApiOperation(value = "插入学生与实践关联-批量1")
    @GetMapping("/adStuPractice/{pra_id}/{cla_id}/{cou_id}")
    public boolean adStuPractice1(@PathVariable("pra_id") Long pra_id, @PathVariable("cla_id") Long cla_id, @PathVariable("cou_id") Long cou_id) {
        //查询该班级所有学生
        Result result = restTemplate.getForObject(REST_URL_PREFIX_DEPT + "/studentClass/shClassStu/" + cla_id, Result.class);
        List<Long> stuNames = (List<Long>) result.getData();
        System.out.println(stuNames);
        List<StudentPractice> studentPracticeList = new ArrayList<>();
        //迭代
        Iterator<Long> iterator = stuNames.iterator();
        while (iterator.hasNext()) {
            studentPracticeList.add(new StudentPractice().setStuId(iterator.next()).setPraId(pra_id)
                    .setPraDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        }
        if (!studentPracticeService.saveBatch(studentPracticeList)) return false;
//        //迭代
//        System.out.println("有了"+studentPracticeList);
//        Iterator<StudentPractice> iterator1 = studentPracticeList.iterator();
//        while (iterator1.hasNext()) {
//            System.out.println(iterator1.next());
//        }
        return true;
    }

    @ApiOperation(value = "插入学生与实践关联-批量2")
    @GetMapping("/adStuPra/{cla_id}/{stu_id}")
    public boolean adStuPractice2(@PathVariable("cla_id") Long cla_id, @PathVariable("stu_id") Long stu_id) {

        //查询实践id
        Result result = restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/classCourse/shCoursesPra/"+cla_id, Result.class);

        ArrayList<String> temp = (ArrayList<String>) result.getData();
        List<Long> praIds = temp.stream().map(Long::parseLong).collect(Collectors.toList());

        List<StudentPractice> studentPracticeList = new ArrayList<>();
        Iterator<Long> iterator = praIds.iterator();//迭代
        while (iterator.hasNext()) {
            studentPracticeList.add(new StudentPractice().setStuId(stu_id).setPraId(iterator.next())
                    .setPraDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        }
        if (!studentPracticeService.saveBatch(studentPracticeList)) return false;

        return true;
    }

    /*
    * delete
    * */

    /*
     * update
     * */

    /*
     * search
     * */

}

