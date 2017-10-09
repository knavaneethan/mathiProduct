package com.mathi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mathi.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
