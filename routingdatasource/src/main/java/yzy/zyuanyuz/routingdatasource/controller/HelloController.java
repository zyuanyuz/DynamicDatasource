package yzy.zyuanyuz.routingdatasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yzy.zyuanyuz.routingdatasource.config.RoutingDataSource;
import yzy.zyuanyuz.routingdatasource.mapper.one.OneMapper;
import yzy.zyuanyuz.routingdatasource.mapper.two.TwoMapper;
import yzy.zyuanyuz.routingdatasource.util.CreateDataSourceUtil;

import static yzy.zyuanyuz.routingdatasource.commons.constants.DataSourceConstants.DS_ONE;

/**
 * @author zyuanyuz
 * @since 2019/8/31 17:46
 */
@RestController
public class HelloController {
  @Autowired OneMapper oneMapper;

  @Autowired TwoMapper twoMapper;

  @Autowired RoutingDataSource routingDataSource;

  @GetMapping("/one")
  public String getOne() {
    return oneMapper.selectDatabase();
  }

  @GetMapping("/two")
  public String getTwo() {
    return twoMapper.selectDatabase();
  }

  @GetMapping("/testFreshDataSource")
  public String testFreshDataSource() {
    routingDataSource.updateDataSource(
        DS_ONE,
        CreateDataSourceUtil.createSimpleDataSource(
            "jdbc:mysql://127.0.0.1:3306/devdb?useUnicode=true&characterEncoding=gbk&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            "root",
            "root"));
    return oneMapper.selectDatabase();
  }
}
