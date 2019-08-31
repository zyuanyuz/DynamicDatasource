package yzy.zyuanyuz.routingdatasource.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author zyuanyuz
 * @since 2019/8/31 18:30
 */
@Component
public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean
    implements InitializingBean {
  @Autowired
  @Qualifier("routingDataSource")
  DataSource routingDataSource;

  @Override
  public void afterPropertiesSet() throws Exception {
    super.setTypeAliasesPackage("yzy.zyuanyuz.routingdatasource.mapper");
    super.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**Mapper.xml"));
    super.setDataSource(routingDataSource);
    super.afterPropertiesSet();
  }
}
