package yzy.zyuanyuz.routingdatasource.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants;
import yzy.zyuanyuz.routingdatasource.config.DataSourceConfig;

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
    DataSourceConfig.RoutingDatasourceContext.setDataSourceLocal(DataSourceConstants.DS_ONE);
  }

  @Pointcut("execution( * yzy.zyuanyuz.routingdatasource.mapper.two.*.*(..))")
  public void twoAspect() {}

  @Before("twoAspect()")
  public void switchToDataSourceTwo() {
    DataSourceConfig.RoutingDatasourceContext.setDataSourceLocal(DataSourceConstants.DS_TWO);
  }
}
