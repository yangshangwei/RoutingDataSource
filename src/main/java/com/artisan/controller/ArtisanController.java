package com.artisan.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artisan.domain.Artisan;
import com.artisan.service.ArtisanService;

@RestController
public class ArtisanController {
	
	@Autowired
	private ArtisanService artisanService ;
	
	@GetMapping("/getDataFromMaster")
	public Artisan getDataFromMaster(int id) {
		return artisanService.getArtisanListFromMaster(id);
	}
	
	@GetMapping("/getDataFromRep")
	public Artisan getDataFromRep(int id) {
		return artisanService.getArtisanListFromSlave(id);
	}

}
