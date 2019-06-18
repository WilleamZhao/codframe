package com.tlkj.cod.dao.annotation.aop;

import com.tlkj.cod.dao.annotation.CodDaoTransaction;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * Desc cod-dao 事物管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoTransactionAspect
 * @date 2019/6/18 11:14 PM
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CodDaoTransactionAspect {

    @Autowired
    ApplicationContext applicationContext;

    @Pointcut("@annotation(com.tlkj.cod.dao.annotation.CodDaoTransaction)")
    public void pointCut() {}

    @Around("pointCut() && @annotation(codDaoTransaction)")
    public Object advice(ProceedingJoinPoint joinPoint, CodDaoTransaction codDaoTransaction) throws Throwable{

        String dataSourceName = codDaoTransaction.name();
        dataSourceName = StringUtils.isNotBlank(dataSourceName) ? dataSourceName : CodDaoDatasourceTypeEnum.DEFAULT.name();
        Connection connection = CodDaoConnectionPool.getInstance().getDataSource(dataSourceName).getConnection();

        Object o = null;
        try {
            o = joinPoint.proceed();
            connection.commit();
        } catch (RuntimeException e){
            connection.rollback();
        } finally {
            connection.close();
        }

        return o;
    }
}
