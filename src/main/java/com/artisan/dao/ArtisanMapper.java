package com.artisan.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.artisan.domain.Artisan;
/**
 * 
 * @author yangshangwei
 *	
 * 增加@Mapper这个注解之后，Spring 启动时会自动扫描该接口，这样就可以在需要使用时直接注入 Mapper 了
 * 
 * MybatisConfig中标注了@MapperScan , 所以这里的@Mapper不加也行
 */

@Mapper
public interface ArtisanMapper {

	Artisan selectArtisanById(@Param("id") int id );
	
}
