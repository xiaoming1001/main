package com.study.shiwu.databaseioc;

import com.study.shiwu.dao.DaoInt;
import com.study.shiwu.enums.DataBaseType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {
    @Pointcut("execution(* com.study.shiwu.dao.*.*(..))")
    public void JoinPoint(){
    }

    //使用定义切点表达式的方法进行点表达式的引入
    @Before("JoinPoint()")
    public void setDataSourceKey(JoinPoint point){
        //如果连接点所属的类实例是DaoInt
        if(point.getTarget() instanceof DaoInt){
            DatabaseContextHolder.setDatabaseType(DataBaseType.test);
        }else{//连接点是UserDao,可以不写是默认的数据源
            DatabaseContextHolder.setDatabaseType(DataBaseType.test_db);
        }
    }

}
