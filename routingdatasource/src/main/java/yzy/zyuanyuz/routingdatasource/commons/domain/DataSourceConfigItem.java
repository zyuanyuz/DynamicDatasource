package yzy.zyuanyuz.routingdatasource.commons.domain;

import lombok.Data;

/**
 * @author zyuanyuz
 * @since 2019/9/12 23:12
 */
@Data
public class DataSourceConfigItem {
  private String url;
  private String username;
  private String password;
  private int maxActive;
  private int minIdle;
  private int initialSize;
  private long maxWait;
  private long timeBetweenEvictionRunsMillis;
  private long minEvictableIdleTimeMillis;
  private String validationQuery;
  private boolean testWhileIdle;
  private boolean testOnBorrow;
  private boolean testOnReturn;

  public static Builder builder(String url, String username, String password) {
    return new Builder(url, username, password);
  }

  public static class Builder {
    private String url;
    private String username;
    private String password;
    private int maxActive = 5;
    private int minIdle = 1;
    private int initialSize = 1;
    private long maxWait = 600000L;
    private long timeBetweenEvictionRunsMillis = 60000L;
    private long minEvictableIdleTimeMillis = 300000L;
    private String validationQuery = "select 1";
    private boolean testWhileIdle = true;
    private boolean testOnBorrow = true;
    private boolean testOnReturn = true;

    public Builder(String url, String username, String password) {
      this.url = url;
      this.username = username;
      this.password = password;
    }

    public Builder maxActive(int maxActive) {
      this.maxActive = maxActive;
      return this;
    }

    public Builder minIdle(int minIdle) {
      this.minIdle = minIdle;
      return this;
    }

    public Builder initialSize(int initialSize) {
      this.initialSize = initialSize;
      return this;
    }

    public Builder maxWait(long maxWait) {
      this.maxWait = maxWait;
      return this;
    }

    public Builder timeBetweenEvictionRunsMills(long timeBetweenEvictionRunsMillis) {
      this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
      return this;
    }

    public Builder minEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
      this.timeBetweenEvictionRunsMillis = minEvictableIdleTimeMillis;
      return this;
    }

    public Builder validationQuery(String validationQuery) {
      this.validationQuery = validationQuery;
      return this;
    }

    public Builder testWhileIdle(boolean testWhileIdle) {
      this.testWhileIdle = testWhileIdle;
      return this;
    }

    public Builder testOnBorrow(boolean testOnBorrow) {
      this.testOnBorrow = testOnBorrow;
      return this;
    }

    public Builder testOnReturn(boolean testOnReturn) {
      this.testOnReturn = testOnReturn;
      return this;
    }

    public DataSourceConfigItem build() {
      DataSourceConfigItem dataSourceConfigItem = new DataSourceConfigItem();
      dataSourceConfigItem.setUrl(this.url);
      dataSourceConfigItem.setUsername(this.username);
      dataSourceConfigItem.setPassword(this.password);
      dataSourceConfigItem.setMaxActive(this.maxActive);
      dataSourceConfigItem.setMinIdle(this.minIdle);
      dataSourceConfigItem.setInitialSize(this.initialSize);
      dataSourceConfigItem.setMaxWait(this.maxWait);
      dataSourceConfigItem.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
      dataSourceConfigItem.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
      dataSourceConfigItem.setValidationQuery(this.validationQuery);
      dataSourceConfigItem.setTestWhileIdle(this.testWhileIdle);
      dataSourceConfigItem.setTestOnBorrow(this.testOnBorrow);
      dataSourceConfigItem.setTestOnReturn(this.testOnReturn);
      return dataSourceConfigItem;
    }
  }
}
