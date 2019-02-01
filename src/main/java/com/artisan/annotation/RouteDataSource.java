package com.artisan.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.artisan.config.DataSources;

/**
 * 
 *    自定义注解，用于切换数据源，默认MASTER_DB
 * @author yangshangwei
 *
 */

@Documented
@Retention(RUNTIME)
@Target({ METHOD })
public @interface RouteDataSource {
	
	String value() default DataSources.MASTER_DB;
	
}
