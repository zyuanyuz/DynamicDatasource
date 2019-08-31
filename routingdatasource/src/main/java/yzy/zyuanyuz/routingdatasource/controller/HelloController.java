package yzy.zyuanyuz.routingdatasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yzy.zyuanyuz.routingdatasource.mapper.one.OneMapper;
import yzy.zyuanyuz.routingdatasource.mapper.two.TwoMapper;

/**
 * @author zyuanyuz
 * @since 2019/8/31 17:46
 */
@RestController
public class HelloController {
  @Autowired OneMapper oneMapper;

  @Autowired TwoMapper twoMapper;

  @GetMapping("/one")
  public String getOne() {
    return String.valueOf(oneMapper.selectTest());
  }

  @GetMapping("/two")
  public String getTwo() {
    return String.valueOf(twoMapper.selectTest());
  }
}
