package yzy.zyuanyuz.routingdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("yzy.zyuanyuz.routingdatasource.mapper.*")
public class RoutingdatasourceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RoutingdatasourceApplication.class, args);
  }
}
