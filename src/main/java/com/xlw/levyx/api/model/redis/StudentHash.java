package com.xlw.levyx.api.model.redis;

import com.xlw.levyx.mapper.model.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

/**
 * Created by levyx on 2017/7/21.
 */
@RedisHash("studentHash")
public class StudentHash {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Date birth;
    private Integer gender;
    private String hobby;

    public static Student to(StudentHash hash){
        Student student = new Student();
        BeanUtils.copyProperties(hash,student);
        return student;
    }

    public static StudentHash from(Student student){
        StudentHash studentHash = new StudentHash();
        BeanUtils.copyProperties(student,studentHash);
        return studentHash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
