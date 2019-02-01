package com.artisan.config;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 使用ThreadLocal管理当前线程使用的数据源连接
 * 
 * @author yangshangwei
 *
 */
@Slf4j
public class DatasourceContextHolder {

	public static final String DEFAULT_DATASOURCE = DataSources.MASTER_DB;

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
	
	/**
	 * 设置数据源
	 * @param dbType
	 */
	public static void setDB(String dbType) {
		contextHolder.set(dbType);
		log.info("切换到数据源{}", dbType);
	}

	/**
	 * 获取数据源
	 */
	public static String getDB() {
		return contextHolder.get();
	}

	/**
	 * 清除数据源
	 */
	public static void clearDB() {
		contextHolder.remove();
	}
}
