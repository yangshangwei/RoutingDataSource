package com.artisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 	取消自动配置数据源，使用我们这里定义的数据源配置 exclude排除
 * @author yangshangwei
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RouteDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteDataSourceApplication.class, args);
	}

}

