package yzy.zyuanyuz.routingdatasource.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static yzy.zyuanyuz.routingdatasource.config.DataSourceConfig.dataSourceTwo;

/**
 * @author zyuanyuz
 * @since 2019/9/12 22:44
 */
@Component("routingDataSource")
public class RoutingDataSource extends AbstractRoutingDataSource implements InitializingBean {
  @Autowired DataSource dataSourceOne;

  Map<Object, Object> dataSourcesMap;

  @Override
  public void afterPropertiesSet() {
    dataSourcesMap = new ConcurrentHashMap<>();
    dataSourcesMap.put(DataSourceConstants.DS_ONE, dataSourceOne);
    dataSourcesMap.put(DataSourceConstants.DS_TWO, dataSourceTwo());
    super.setTargetDataSources(dataSourcesMap);
    //super.setDefaultTargetDataSource(dataSourcesMap.get(DataSourceConstants.DS_ONE));
    super.afterPropertiesSet();
  }

  /**
   * update the datasource in the datasource map
   *
   * @param dataSourceKey
   * @param aDataSource
   */
  public void updateDataSource(String dataSourceKey, DataSource aDataSource) {
    dataSourcesMap.put(dataSourceKey, aDataSource);

    /*↓refresh the datasource map resolvedDataSources in AbstractRoutingDataSource, resolve may thread unsafe↓*/
    super.afterPropertiesSet();
  }

  @Override
  protected Object determineCurrentLookupKey() {
    return RoutingDatasourceContext.getContextKey();
  }

  public static class RoutingDatasourceContext {
    private RoutingDatasourceContext() {}

    private static ThreadLocal<Object> dataSourceLocal = new ThreadLocal<>();
//        ThreadLocal.withInitial(() -> DataSourceConstants.DS_ONE);

    public static Object getContextKey() {
      return dataSourceLocal.get();
    }

    public static void setDataSourceLocal(Object key) {
      dataSourceLocal.set(key);
    }
  }
}
