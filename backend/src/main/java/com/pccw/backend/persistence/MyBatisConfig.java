package com.pccw.backend.persistence;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.pccw")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        var config = new MyBatisConfiguration();
        config.setMapUnderscoreToCamelCase(true);
        config.getTypeHandlerRegistry().register(String.class, StringWhiteSpaceTypeHandler.class);
        config.setCacheEnabled(false);
        config.setLocalCacheScope(LocalCacheScope.STATEMENT);
        factoryBean.addPlugins();
        config.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(config);
        return factoryBean.getObject();
    }

}
