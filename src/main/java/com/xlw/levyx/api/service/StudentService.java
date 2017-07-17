package com.xlw.levyx.api.service;

import com.xlw.levyx.api.utils.CommonUtils;
import com.xlw.levyx.mapper.client.BaseStudentMapper;
import com.xlw.levyx.mapper.model.BaseStudent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by levyx on 2017/7/17.
 */
@Service
public class StudentService {
    @Autowired
    private BaseStudentMapper baseStudentMapper;

    public void save(BaseStudent baseStudent){
        if (StringUtils.isBlank(baseStudent.getId())){
            baseStudent.setId(CommonUtils.uuid());
            baseStudentMapper.insertSelective(baseStudent);
        } else {
            baseStudentMapper.updateByPrimaryKeySelective(baseStudent);
        }
    }
}
