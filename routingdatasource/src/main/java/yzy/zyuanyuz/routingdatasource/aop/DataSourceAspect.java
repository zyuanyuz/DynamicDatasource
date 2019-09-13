package yzy.zyuanyuz.routingdatasource.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants;
import yzy.zyuanyuz.routingdatasource.config.DataSourceConfig;
import yzy.zyuanyuz.routingdatasource.config.RoutingDataSource;

/**
 * @author zyuanyuz
 * @since 2019/8/31 15:46
 */
@Aspect
@Component
public class DataSourceAspect {

  @Pointcut("execution( * yzy.zyuanyuz.routingdatasource.mapper.one.*.*(..))")
  public void oneAspect() {}

  @Before("oneAspect()")
  public void switchToDataSourceOne() {
    RoutingDataSource.RoutingDatasourceContext.setDataSourceLocal(DataSourceConstants.DS_ONE);
  }

  @Pointcut("execution( * yzy.zyuanyuz.routingdatasource.mapper.two.*.*(..))")
  public void twoAspect() {}

  @Before("twoAspect()")
  public void switchToDataSourceTwo() {
    RoutingDataSource.RoutingDatasourceContext.setDataSourceLocal(DataSourceConstants.DS_TWO);
  }
}
