package com.maro.crud.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.maro.crud.entity.City;
import com.maro.crud.exception.CityNotFoundException;
import com.maro.crud.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	private City dbCity;
	
	public City saveCity(City city) {
		return cityRepository.save(city);
	}
	
	public List<City> getAllCity() {
		return cityRepository.findAll();
	}
	
	public City getCity(int id) {
		return cityRepository.findById(id).orElseThrow( () -> new CityNotFoundException("City Not found with ID " + id));
	}
	public City getCityWithCountry(int id) {
		return cityRepository.getCityWithCountry(id).orElseThrow( () -> new CityNotFoundException("City Not found with ID " + id));		
	}
	public City findByName(String name) {
		return cityRepository.findByName(name);
	}
	public List<City> findAllByCountryCode(String countryCode) {
		return cityRepository.findAllByCountryCode(countryCode);
	}

	public City updateCity(City city) {
		dbCity = cityRepository.findById(city.getId()).orElseThrow( () -> new CityNotFoundException("City Not found with ID " + city.getId()));
		return cityRepository.save(dbCity);
	}

	public void deleteCityById(int id) {
		dbCity = cityRepository.findById(id).orElseThrow( () -> new CityNotFoundException("City Not found with ID " + id));
		cityRepository.delete(dbCity);
	}
}
