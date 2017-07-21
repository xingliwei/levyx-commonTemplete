package com.xlw.levyx.api.repository;

import com.xlw.levyx.api.model.redis.StudentHash;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by levyx on 2017/7/21.
 * @Desc：定义此接口前必须配置redisTemplate
 * @Desc：CurdRepository提供了基础的增删改查功能
 * @Warn：注意spring-data-redis和spring-data-commons的配合，不是所有版本都能配合使用，使用错误的版本可能会导致启动报错等一些未知错误
 *
 */
public interface StudentRepository extends CrudRepository<StudentHash,String>{
}
