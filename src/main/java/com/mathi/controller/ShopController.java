package com.mathi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mathi.entity.Shop;
import com.mathi.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	@Resource
	private ShopService shopService;

    @RequestMapping(method = RequestMethod.GET)   
    public List<Shop> getAllUsers() {
    	return shopService.findAll();		
	} 
    
    @PostMapping
    @RequestMapping
	Shop createShop(@RequestBody Shop shop) {
    	return shopService.create(shop);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{shopId}")
	Shop getShop(@PathVariable Integer shopId) {
		return shopService.findById(shopId);
	}
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{shopId}")
	void deleteShop(@PathVariable Integer shopId) {
		shopService.delete(shopId);
	}
    
    @PutMapping( value = "/{shopId}")
   	void updateShop(@PathVariable Integer shopId,@RequestBody Shop shop) {
		shopService.update(shop);
   	}
}
