package yzy.zyuanyuz.routingdatasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yzy.zyuanyuz.routingdatasource.mapper.two.TwoMapper;
import yzy.zyuanyuz.routingdatasource.service.TwoService;

/**
 * @author zyuanyuz
 * @since 2019/9/17 19:23
 */
@Service
public class TwoServiceImpl implements TwoService {
  @Autowired TwoMapper twoMapper;

  @Override
  @Transactional
  public String dbName() {
    return twoMapper.selectDatabase();
  }
}
