package com.xlw.levyx.api.service;

import com.xlw.levyx.api.model.redis.StudentHash;
import com.xlw.levyx.api.resolver.StudentResolver;
import com.xlw.levyx.api.utils.CommonUtils;
import com.xlw.levyx.mapper.client.StudentMapper;
import com.xlw.levyx.mapper.model.Student;
import com.xlw.levyx.mapper.model.StudentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by levyx on 2017/7/17.
 */
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentResolver studentResolver;

    public void save(Student baseStudent){
        if (StringUtils.isBlank(baseStudent.getId())){
            baseStudent.setId(CommonUtils.uuid());
            studentMapper.insertSelective(baseStudent);
        } else {
            studentMapper.updateByPrimaryKeySelective(baseStudent);
        }
    }

    public List<Student> page(Integer limit,Integer offset){
        StudentExample example = new StudentExample();
        example.setLimitStart(offset);
        example.setLimitEnd(limit);
        return studentMapper.selectByExample(example);
    }

    public Student getStudent(String id){
        Student student = null;
        StudentHash studentHash = studentResolver.find(id);
        if (studentHash == null){
            student = studentMapper.selectByPrimaryKey(id);
            if (student != null){
                studentResolver.cachedStudent(StudentHash.from(student));
            }
        } else {
            student = StudentHash.to(studentHash);
        }
        return student;
    }
}
