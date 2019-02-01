package com.artisan.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource{
	
	@Override
    protected Object determineCurrentLookupKey() {
        log.info("数据源为{}", DatasourceContextHolder.getDB());
        return DatasourceContextHolder.getDB();
    }
}
