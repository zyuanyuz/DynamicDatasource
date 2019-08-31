package yzy.zyuanyuz.routingdatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyuanyuz
 * @since 2019/8/31 15:06
 */
@Configuration
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.druid.one")
  public DataSource dataSourceOne() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.druid.two")
  public DataSource dataSourceTwo() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean("routingDataSource")
  public DataSource routingDataSource() {
    RoutingDataSource routingDataSource = new RoutingDataSource();
    Map<Object, Object> dataSources = new HashMap<>();
    dataSources.put(DataSourceConstants.DS_ONE, dataSourceOne());
    dataSources.put(DataSourceConstants.DS_TWO, dataSourceTwo());
    routingDataSource.setTargetDataSources(dataSources);

    routingDataSource.setDefaultTargetDataSource(dataSources.get(DataSourceConstants.DS_ONE));
    return routingDataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager(
      @Qualifier("routingDataSource") DataSource routingDataSource) {
    return new DataSourceTransactionManager(routingDataSource);
  }

  public static class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
      return RoutingDatasourceContext.getContextKey();
    }
  }

  public static class RoutingDatasourceContext {
    private RoutingDatasourceContext() {}

    private static ThreadLocal<Object> dataSourceLocal =
        ThreadLocal.withInitial(() -> DataSourceConstants.DS_ONE);

    public static Object getContextKey() {
      return dataSourceLocal.get();
    }

    public static void setDataSourceLocal(Object key) {
      dataSourceLocal.set(key);
    }
  }
}
