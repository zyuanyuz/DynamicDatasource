package yzy.zyuanyuz.routingdatasource.mapper.two;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author zyuanyuz
 * @since 2019/8/31 15:40
 */
@Mapper
@Component
public interface TwoMapper {
  String selectDatabase();
}
