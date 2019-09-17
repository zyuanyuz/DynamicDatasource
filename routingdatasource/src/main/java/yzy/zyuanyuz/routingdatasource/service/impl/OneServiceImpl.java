package yzy.zyuanyuz.routingdatasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yzy.zyuanyuz.routingdatasource.mapper.one.OneMapper;
import yzy.zyuanyuz.routingdatasource.service.OneService;

/**
 * @author zyuanyuz
 * @since 2019/9/17 19:08
 */
@Service
public class OneServiceImpl implements OneService {
    @Autowired
    OneMapper oneMapper;

    @Override
    @Transactional
    public String dbName() {
        oneMapper.selectDatabase();
        return oneMapper.selectDatabase();
    }
}
