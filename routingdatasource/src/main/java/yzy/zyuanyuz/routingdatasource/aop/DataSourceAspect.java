package yzy.zyuanyuz.routingdatasource.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants;
import yzy.zyuanyuz.routingdatasource.config.RoutingDataSource;

/**
 * @author zyuanyuz
 * @since 2019/8/31 15:46
 */
@Aspect
@Component
public class DataSourceAspect {
  @Before("execution( * yzy.zyuanyuz.routingdatasource.mapper.one.*.*(..))")
  public void switchToDataSourceOne() {
    System.out.println("DS_ONE");
    RoutingDataSource.RoutingDatasourceContext.setDataSourceLocal(DataSourceConstants.DS_ONE);
  }

  @Before("execution( * yzy.zyuanyuz.routingdatasource.mapper.two.*.*(..))")
  public void switchToDataSourceTwo() {
    System.out.println("DS_TWO");
    RoutingDataSource.RoutingDatasourceContext.setDataSourceLocal(DataSourceConstants.DS_TWO);
  }
}
