package yzy.zyuanyuz.routingdatasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yzy.zyuanyuz.routingdatasource.config.RoutingDataSource;
import yzy.zyuanyuz.routingdatasource.service.OneService;
import yzy.zyuanyuz.routingdatasource.service.TwoService;
import yzy.zyuanyuz.routingdatasource.util.CreateDataSourceUtil;

import static yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants.DS_ONE;
import static yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants.DS_TWO;

/**
 * @author zyuanyuz
 * @since 2019/8/31 17:46
 */
@RestController
public class HelloController {
  @Autowired OneService oneService;

  @Autowired TwoService twoService;

  @Autowired RoutingDataSource routingDataSource;

  @GetMapping("/one")
  public String getOne() {
    RoutingDataSource.RoutingDatasourceContext.setDataSourceLocal(DS_ONE);
    return oneService.dbName();
  }

  @GetMapping("/two")
  public String getTwo() {
    RoutingDataSource.RoutingDatasourceContext.setDataSourceLocal(DS_TWO);
    return twoService.dbName();
  }

  @GetMapping("/fresh")
  public String testFreshDataSource() {
    routingDataSource.updateDataSource(
        DS_ONE,
        CreateDataSourceUtil.createSimpleDataSource(
            "jdbc:mysql://127.0.0.1:3306/crashcource?useUnicode=true&characterEncoding=gbk&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            "root",
            "2210"));
    return oneService.dbName();
  }
}
