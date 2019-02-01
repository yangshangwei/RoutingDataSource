package com.artisan.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artisan.annotation.RouteDataSource;
import com.artisan.config.DataSources;
import com.artisan.dao.ArtisanMapper;
import com.artisan.domain.Artisan;
import com.artisan.service.ArtisanService;

@Service
public class ArtisanServiceImpl implements ArtisanService {
	
	@Autowired
	private ArtisanMapper artisanMapper;
	

	@Override
	@RouteDataSource(DataSources.MASTER_DB)
	public Artisan getArtisanListFromMaster(int id) {
		return artisanMapper.selectArtisanById(id);
	}

	@Override
	@RouteDataSource(DataSources.SLAVE_DB)
	public Artisan getArtisanListFromSlave(int id) {
		return artisanMapper.selectArtisanById(id);
	}

}
