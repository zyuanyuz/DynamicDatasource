package yzy.zyuanyuz.routingdatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import yzy.zyuanyuz.routingdatasource.util.CreateDataSourceUtil;

import javax.sql.DataSource;

import static yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants.DS_TWO;

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
  public static DataSource dataSourceTwo() {
    return CreateDataSourceUtil.createSimpleDataSource(
        "jdbc:mysql://127.0.0.1:3306/devdb?useUnicode=true&characterEncoding=gbk&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        "root",
        "2210");
  }

  //  @Bean
  //  public PlatformTransactionManager transactionManager(
  //      @Qualifier("routingDataSource") DataSource routingDataSource) {
  //    return new DataSourceTransactionManager(routingDataSource);
  //  }

  @Component("transactionManager")
  public static class TransactionManager extends DataSourceTransactionManager
      implements InitializingBean {
    @Autowired DataSource routingDataSource;

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
      //      super.doCleanupAfterCompletion(transaction);
      System.out.println("transaction begin!");
      super.doBegin(transaction, definition);
    }

    @Override
    public void afterPropertiesSet() {
      super.setDataSource(routingDataSource);
      super.afterPropertiesSet();
    }
  }
}
