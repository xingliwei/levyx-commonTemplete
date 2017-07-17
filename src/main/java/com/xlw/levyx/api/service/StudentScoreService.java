package com.xlw.levyx.api.service;

import com.xlw.levyx.mapper.client.StudentScoreMapper;
import com.xlw.levyx.mapper.model.StudentScore;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by levyx on 2017/7/14.
 */
@Service
public class StudentScoreService {

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    public StudentScore getById(Integer id){
        return studentScoreMapper.selectByPrimaryKey(id);
    }

    public void testTransaction(){
        StudentScore studentScore = new StudentScore();
        studentScore.setSocre(100);
        studentScoreMapper.insertSelective(studentScore);

        if (1/0 == 2){
            System.out.println("my exception!");
        }

        StudentScore studentScore1 = new StudentScore();
        studentScore1.setSocre(99);
        studentScoreMapper.insertSelective(studentScore1);
    }
}
