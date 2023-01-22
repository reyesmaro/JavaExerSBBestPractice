package com.maro.crud;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maro.crud.repository.CityRepository;
import com.maro.crud.repository.CountryRepository;

@SpringBootTest
public class CityRepoTest {
	
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Test
	public void testCityByID() {
		System.out.println("Test1:"+cityRepository.getCityWithCountry(4050));
		System.out.println("Test2:"+cityRepository.findAllById(List.of(4050)));
		System.out.println(countryRepository.findById("USA"));
	}
}
