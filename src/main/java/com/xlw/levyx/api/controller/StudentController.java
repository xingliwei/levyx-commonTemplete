package com.xlw.levyx.api.controller;

import com.xlw.levyx.api.convert.DateConvert;
import com.xlw.levyx.api.model.Response;
import com.xlw.levyx.api.service.StudentService;
import com.xlw.levyx.mapper.model.BaseStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by levyx on 2017/7/17.
 */
@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Response save(@RequestBody BaseStudent baseStudent,
                         @RequestParam(value = "birth")Date birth){
        studentService.save(baseStudent);
        return new Response();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new DateConvert());
    }

}
