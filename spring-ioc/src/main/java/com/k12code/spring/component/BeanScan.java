package com.k12code.spring.component;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Carl
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.k12code.spring.component.component")
@Configuration
public class BeanScan {

    @Bean
    public Bean2 bean2(){
        return new Bean2();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://k12code.online/ry-cloud");
        druidDataSource.setUsername("carl");
        druidDataSource.setPassword("carl");
        return druidDataSource;
    }
}
