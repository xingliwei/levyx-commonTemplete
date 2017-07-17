package com.xlw.levyx.api.controller;

import com.xlw.levyx.api.model.ObjectResponse;
import com.xlw.levyx.api.model.Response;
import com.xlw.levyx.api.service.StudentScoreService;
import com.xlw.levyx.mapper.model.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by levyx on 2017/7/14.
 */
@RestController
@RequestMapping(value = "student/score")
public class StudentScoreController {
    @Autowired
    private StudentScoreService studentScoreService;

    @RequestMapping(value = "getOne")
    public Response getOne(@RequestParam(value="scoreId",required = true)Integer id){
        ObjectResponse<StudentScore> response = new ObjectResponse<>();
        response.setInfo(studentScoreService.getById(id));
        return response;
    }

    @RequestMapping(value = "test")
    public void test(){
        studentScoreService.testTransaction();
    }
}
