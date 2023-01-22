package com.maro.crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maro.crud.entity.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country, String>{
	Country findByName(String name); 
}
