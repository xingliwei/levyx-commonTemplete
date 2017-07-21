package com.xlw.levyx.api.resolver;

import com.xlw.levyx.api.model.redis.StudentHash;
import com.xlw.levyx.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by levyx on 2017/7/21.
 */
@Component
public class StudentResolver {
    @Autowired
    private StudentRepository studentRepository;


    public void cachedStudent(StudentHash student){
        studentRepository.save(student);
    }

    public StudentHash find(String id){
        return studentRepository.findOne(id);
    }
}
