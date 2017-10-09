package com.mathi.service;

import java.util.List;

import com.mathi.entity.Shop;

public interface ShopService {
	
	public Shop create(Shop shop);
	public Shop delete(int id);
	public List<Shop> findAll();
	public Shop update(Shop shop);
	public Shop findById(int id);

}
