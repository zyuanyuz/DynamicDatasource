package yzy.zyuanyuz.routingdatasource.util;

import com.alibaba.druid.pool.DruidDataSource;
import yzy.zyuanyuz.routingdatasource.commons.domain.DataSourceConfigItem;

import javax.sql.DataSource;

/**
 * @author zyuanyuz
 * @since 2019/9/12 22:58
 */
public abstract class CreateDataSourceUtil {
  /**
   * @param url
   * @param username
   * @param password
   * @return
   */
  public static DataSource createSimpleDataSource(String url, String username, String password) {
    return createCustomeDataSource(DataSourceConfigItem.builder(url, username, password).build());
  }

  /**
   * create a custom dataSource with parameters
   *
   * @param configItem the Object contain the parameters to init a dataSource
   * @return
   */
  public static DataSource createCustomeDataSource(DataSourceConfigItem configItem) {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(configItem.getUrl());
    dataSource.setUsername(configItem.getUsername());
    dataSource.setPassword(configItem.getPassword());

    dataSource.setMaxActive(configItem.getMaxActive());
    dataSource.setMinIdle(configItem.getMinIdle());
    dataSource.setInitialSize(configItem.getInitialSize());
    dataSource.setMaxWait(configItem.getMaxWait());
    dataSource.setTimeBetweenEvictionRunsMillis(configItem.getTimeBetweenEvictionRunsMillis());
    dataSource.setMinEvictableIdleTimeMillis(configItem.getMinEvictableIdleTimeMillis());
    dataSource.setValidationQuery(configItem.getValidationQuery());

    dataSource.setTestWhileIdle(configItem.isTestWhileIdle());
    dataSource.setTestOnBorrow(configItem.isTestOnBorrow());
    dataSource.setTestOnReturn(configItem.isTestOnReturn());

    return dataSource;
  }
}
