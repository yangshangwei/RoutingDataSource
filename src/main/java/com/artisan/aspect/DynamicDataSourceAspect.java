package com.artisan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.artisan.annotation.RouteDataSource;
import com.artisan.config.DatasourceContextHolder;

import java.lang.reflect.Method;

/**
 * 通过切面对自定义切库注解的方法进行拦截，动态的选择数据源
 * 
 * @author yangshangwei
 *
 */

@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

	/**
	 * 前置增强，方法执行前，通过JoinPoint访问连接点上下文的信息
	 * 
	 * @param joinPoint
	 */
	@Before("@annotation(com.artisan.annotation.RouteDataSource)")
	public void beforeSwitchDataSource(JoinPoint joinPoint) {
		// 获取连接点的方法签名对象
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		// 获取方法
		Method method = methodSignature.getMethod();
		// 设置默认的数据源为Master，防止切库出现异常执行失败的情况
		String dataSource = DatasourceContextHolder.DEFAULT_DATASOURCE;
		// 判断方法上是否标注了@RouteDataSource
		if (method.isAnnotationPresent(RouteDataSource.class)) {
			RouteDataSource routeDataSource = method.getDeclaredAnnotation(RouteDataSource.class);
			// 获取@RouteDataSource上的value的值
			dataSource = routeDataSource.value();
		}
		// 设置数据源
		DatasourceContextHolder.setDB(dataSource);
		log.info("setDB {}", dataSource);
	}

	/**
	 * 后置增强，清空DatasourceContextHolder，防止threadLocal误用带来的内存泄露
	 */
	@After("@annotation(com.artisan.annotation.RouteDataSource)")
	public void afterSwitchDataSource() {
		// 方法执行完成后，清除threadlocal中持有的database
		DatasourceContextHolder.clearDB();
		log.info("清空DatasourceContextHolder...");
	}
	
	/**
	@Before("@annotation(com.artisan.annotation.RouteDataSource)")
	public void beforeSwitchDataSource(JoinPoint point) {

		// 获得当前访问的class
		Class<?> className = point.getTarget().getClass();

		// 获得访问的方法名
		String methodName = point.getSignature().getName();
		// 得到方法的参数的类型
		Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
		String dataSource = DatasourceContextHolder.DEFAULT_DATASOURCE;
		try {
			// 得到访问的方法对象
			Method method = className.getMethod(methodName, argClass);

			// 判断是否存在@DS注解
			if (method.isAnnotationPresent(RouteDataSource.class)) {
				RouteDataSource annotation = method.getAnnotation(RouteDataSource.class);
				// 取出注解中的数据源名
				dataSource = annotation.value();
			}
		} catch (Exception e) {
			log.error("routing datasource exception, " + methodName, e);
		}
		// 切换数据源
		DatasourceContextHolder.setDB(dataSource);
	}
	**/
}
