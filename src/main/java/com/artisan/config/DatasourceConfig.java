package com.artisan.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DatasourceConfig {
	
	//destroy-method="close"：当数据库连接不使用的时候,将该连接重新放到数据池中
    @Bean(name=DataSources.MASTER_DB,destroyMethod="close")
    @ConfigurationProperties(prefix = "spring.datasource-master")
    public DataSource dataSource() {
    	// 创建数据源
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name=DataSources.SLAVE_DB,destroyMethod="close")
    @ConfigurationProperties(prefix = "spring.datasource-slave")
    public DataSource dataSourceSlave() {
    	// 创建数据源
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

}
