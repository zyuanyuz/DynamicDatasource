package yzy.zyuanyuz.routingdatasource.util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * @author zyuanyuz
 * @since 2019/9/12 22:58
 */
public abstract class CreateDataSourceUtil {

  /**
   * with lots of default datasource config,if you want a custom dataSource,please write a function
   *
   * @param url
   * @param username
   * @param password
   * @return
   */
  public static DataSource simpleDataSource(String url, String username, String password) {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);

    dataSource.setMaxActive(5);
    dataSource.setMinIdle(1);
    dataSource.setInitialSize(1);
    dataSource.setMaxWait(600000);
    dataSource.setTimeBetweenEvictionRunsMillis(60000L);
    dataSource.setMinEvictableIdleTimeMillis(300000);
    dataSource.setValidationQuery("select 1");

    dataSource.setTestWhileIdle(true);
    dataSource.setTestOnBorrow(true);
    dataSource.setTestOnReturn(true);

    return dataSource;
  }

  public static class DataSourceBuilder{

  }

}
