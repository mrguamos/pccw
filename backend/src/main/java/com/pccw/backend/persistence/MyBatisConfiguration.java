package com.pccw.backend.persistence;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;

import java.time.LocalDateTime;

public class MyBatisConfiguration extends Configuration {

    @Override
    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (sqlCommandType == SqlCommandType.UPDATE) {
            boundSql.setAdditionalParameter("__updatedAt", LocalDateTime.now());
        }
        return super.newParameterHandler(mappedStatement, parameterObject, boundSql);
    }


}
