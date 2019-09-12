package yzy.zyuanyuz.routingdatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
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

  @Bean
  public PlatformTransactionManager transactionManager(
      @Qualifier("routingDataSource") DataSource routingDataSource) {
    return new DataSourceTransactionManager(routingDataSource);
  }
}
