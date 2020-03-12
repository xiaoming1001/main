package com.study.shiwu.config;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.study.shiwu.databaseioc.DynamicDataSource;
import com.study.shiwu.enums.DataBaseType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;  //这个一定要引对
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.study.shiwu.dao")
public class MybatisConfig {
    @Autowired
    private Environment env;

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource myTestDb2DataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("datasource.users.driverClassName"));
        props.put("url", env.getProperty("datasource.users.url"));
        props.put("username", env.getProperty("datasource.users.username"));
        props.put("password", env.getProperty("datasource.users.password"));
        System.out.println("数据源一已启动 ："+env.getProperty("datasource.users.url"));
        return  DruidDataSourceFactory.createDataSource(props);
    }
    @Bean
    public DataSource myTestDbDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("datasource.person.driverClassName"));
        props.put("url", env.getProperty("datasource.person.url"));
        props.put("username", env.getProperty("datasource.person.username"));
        props.put("password", env.getProperty("datasource.person.password"));
        System.out.println("数据源一已启动 ："+env.getProperty("datasource.person.url"));
        return  DruidDataSourceFactory.createDataSource(props);
    }



    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("myTestDbDataSource") DataSource myTestDbDataSource,
                                        @Qualifier("myTestDb2DataSource") DataSource myTestDb2DataSource) {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataBaseType.test, myTestDbDataSource);
        targetDataSources.put(DataBaseType.test_db, myTestDb2DataSource);
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(myTestDbDataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }

    //根据数据源创建SqlSessionFactory
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        // 指定数据源(这个必须有，否则报错)
        fb.setDataSource(ds);
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
       /* 最关键的就是这一句代码：
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:Mapper.xml"));
        让mybatis到这个指定的路径去找到他的mapper.xml文件。对！效果其实是和yml中的
        mybatis.mapperLocations:是一样的。但是在配置多个数据源时候我们只能手动去配置。而这个配置文件里面的路径写不写都没有影响了*/
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));

        return fb.getObject();
    }


}
