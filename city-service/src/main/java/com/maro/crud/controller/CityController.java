package com.maro.crud.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maro.crud.dto.APIResponse;
import com.maro.crud.entity.City;
import com.maro.crud.service.CityService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CityController {
	@Autowired
	private CityService cityService;

	@GetMapping("/getAllCities")
	private ResponseEntity<APIResponse<List<City>>> getAllCity() {
		APIResponse<List<City>> apiResponse = APIResponse.<List<City>>builder()
											.status("SUCCESS")
											.results(cityService.getAllCity())
											.build();
		return new ResponseEntity<APIResponse<List<City>>>(apiResponse , HttpStatus.OK);
	}
	
	@GetMapping("/getCityById/{id}")
	private ResponseEntity<APIResponse<City>> getCityById(@PathVariable int id) {
		log.info("CityController::getCityById id {}" , id);
		APIResponse<City> apiResponse = APIResponse.<City>builder()
											.status("SUCCESS")
//											.results(cityService.getCity(id))
											.results(cityService.getCityWithCountry(id))
											.build();
		log.info("CityController::getCityById response {}" , apiResponse);
		return new ResponseEntity<APIResponse<City>>(apiResponse , HttpStatus.OK);
	}
	
	@GetMapping("/getCityByName/{name}")
	private ResponseEntity<APIResponse<City>> getCityByName(@PathVariable String name) {
		APIResponse<City> apiResponse = APIResponse.<City>builder()
										.status("SUCCESS")
										.results(cityService.findByName(name))
										.build();
		return new ResponseEntity<APIResponse<City>>(apiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getCitiesByCountryCode/{countryCode}")
	private ResponseEntity<APIResponse<List<City>>> getCitiesByCountryCode(@PathVariable String countryCode) {
		APIResponse<List<City>> apiResponse = APIResponse.<List<City>>builder()
											.status("SUCCESS")
											.results(cityService.findAllByCountryCode(countryCode))
											.build();
		return new ResponseEntity<APIResponse<List<City>>>(apiResponse , HttpStatus.OK);
	}
	
	@PostMapping("/addCity")
	private ResponseEntity<APIResponse<City>> addCity(@RequestBody City city) {
		APIResponse<City> apiResponse = APIResponse.<City>builder()
										.status("SUCCESS")
										.results(cityService.saveCity(city))
										.build();
		return new ResponseEntity<APIResponse<City>>(apiResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCity")
	private ResponseEntity<APIResponse<City>> updateCity(@Valid @RequestBody City city) {
		APIResponse<City> apiResponse = APIResponse.<City>builder()
				.status("SUCCESS")
				.results(cityService.updateCity(city))
				.build();
		return new ResponseEntity<APIResponse<City>>(apiResponse, HttpStatus.OK);
	}
	@DeleteMapping("/deleteCity/{id}")
	private ResponseEntity<APIResponse<City>> deleteCity(@PathVariable int id) {
		cityService.deleteCityById(id);
		return new ResponseEntity<APIResponse<City>>(APIResponse.<City>builder()
				.status("SUCCESS")
				.results(null)
				.build(), HttpStatus.NO_CONTENT);
	}
	
}
