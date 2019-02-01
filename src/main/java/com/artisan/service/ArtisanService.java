package com.artisan.service;


import com.artisan.domain.Artisan;

public interface ArtisanService {
	
	Artisan getArtisanListFromMaster(int id);
	
	Artisan getArtisanListFromSlave(int id);
}
