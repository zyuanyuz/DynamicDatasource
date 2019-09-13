package yzy.zyuanyuz.routingdatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import yzy.zyuanyuz.routingdatasource.util.CreateDataSourceUtil;

import javax.sql.DataSource;

/**
 * @author zyuanyuz
 * @since 2019/8/31 15:06
 */
@Configuration
public class DataSourceConfig {
  /**
   * create a datasource configured in config file
   *
   * @return
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.druid.one")
  public DataSource dataSourceOne() {
    return DruidDataSourceBuilder.create().build();
  }

  /**
   * second way to create a datasource with a function,can be init in the program runtime
   *
   * @return
   */
  @Bean
  public DataSource dataSourceTwo() {
    return CreateDataSourceUtil.createSimpleDataSource(
        "jdbc:mysql://127.0.0.1:3306/devdb?useUnicode=true&characterEncoding=gbk&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        "root",
        "2210");
  }

  @Bean
  public PlatformTransactionManager transactionManager(
      @Qualifier("routingDataSource") DataSource routingDataSource) {
    return new DataSourceTransactionManager(routingDataSource);
  }
}
