package com.artisan.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = { "com.artisan.dao" }) // 扫描的mybatis接口类的包名
public class MybatisConfig {

	@Autowired
	@Qualifier(DataSources.MASTER_DB)
	private DataSource masterDB;

	@Autowired
	@Qualifier(DataSources.SLAVE_DB)
	private DataSource slaveDB;

	/**
	 * 动态数据源
	 */
	@Bean(name = "dynamicDataSource")
	public DataSource dynamicDataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		// 默认数据源
		dynamicDataSource.setDefaultTargetDataSource(masterDB);

		// 配置多数据源
		Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
		dataSourceMap.put(DataSources.MASTER_DB, masterDB);
		dataSourceMap.put(DataSources.SLAVE_DB, slaveDB);
		dynamicDataSource.setTargetDataSources(dataSourceMap);

		return dynamicDataSource;
	}

	@Bean
	@ConfigurationProperties(prefix = "mybatis")
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 配置数据源，关键配置
		sqlSessionFactoryBean.setDataSource(dynamicDataSource());
		// 解决配置到配置文件中通过*配置找不到mapper文件的问题。 如果不设置这一行，在配置文件中，只能使用数组的方式一个个的罗列出来，并且要指定具体的文件名
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean;
	}

}
