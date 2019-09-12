package yzy.zyuanyuz.routingdatasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyuanyuz
 * @since 2019/9/12 22:44
 */
@Component("routingDataSource")
public class RoutingDataSource extends AbstractRoutingDataSource {
  @Autowired DataSource dataSourceOne;

  @Autowired DataSource dataSourceTwo;

  Map<Object, Object> dataSourcesMap;

  public RoutingDataSource() {
    dataSourcesMap = new HashMap<>();
    dataSourcesMap.put(DataSourceConstants.DS_ONE, dataSourceOne);
    dataSourcesMap.put(DataSourceConstants.DS_TWO, dataSourceTwo);
    super.setTargetDataSources(dataSourcesMap);
    super.setDefaultTargetDataSource(dataSourcesMap.get(DataSourceConstants.DS_ONE));
  }

  /**
   * update the datasource in the datasource map
   *
   * @param dataSourceKey
   * @param aDataSource
   */
  public void updateDataSource(String dataSourceKey, DataSource aDataSource) {
    if (dataSourcesMap.get(dataSourceKey) != null) dataSourcesMap.put(dataSourceKey, aDataSource);
  }

  @Override
  protected Object determineCurrentLookupKey() {
    return RoutingDatasourceContext.getContextKey();
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
