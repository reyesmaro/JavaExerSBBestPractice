package com.maro.crud;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.asm.TypeReference;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.maro.crud.dto.APIResponse;
import com.maro.crud.entity.City;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

class CityServiceApplicationTests {
	
	RestTemplateBuilder builder = new RestTemplateBuilder();
	RestTemplate restTemplate = builder.basicAuthentication("user", "userpass").build();

	@Test
	void getCityById() {
		ResponseEntity<APIResponse<City>> resp = restTemplate.exchange("http://localhost:9091/getCityById/65", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<APIResponse<City>>() {});
		assertEquals(resp.getBody().getResults().getId(), 65);
		assertEquals(resp.getBody().getResults().getCountryCode(), "ARE");
		assertEquals(resp.getBody().getResults().getDistrict(), "Abu Dhabi");
		assertEquals(resp.getBody().getResults().getPopulation(), 398695);
		assertEquals(resp.getBody().getResults().getName(), "Abu Dhabi");
	}

	@Test
	void getAllCities() {
		ResponseEntity<APIResponse<List<City>>> resp = restTemplate.exchange("http://localhost:9091/getAllCities", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<APIResponse<List<City>>>() {});
		assertTrue(resp.getBody().getResults().size() >= 4080);
	}
	@Test
	void getCityByName() {
		ResponseEntity<APIResponse<City>> resp = restTemplate.exchange("http://localhost:9091//getCityByName/Manila", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<APIResponse<City>>() {});
		assertEquals(resp.getBody().getResults().getId(), 766);
		assertEquals(resp.getBody().getResults().getName(), "Manila");
		assertEquals(resp.getBody().getResults().getCountryCode(), "PHL");
		assertEquals(resp.getBody().getResults().getDistrict(), "National Capital Reg");
		assertEquals(resp.getBody().getResults().getPopulation(), 1581082);
	}
	@Test
	void updateCity() {
		restTemplate = builder.basicAuthentication("admin", "adminpass").build();
		ResponseEntity<APIResponse<City>> resp = restTemplate.exchange("http://localhost:9091/updateCity", HttpMethod.PUT, new HttpEntity<City>(City.builder().id(4080).name("TestUpdate2").countryCode("PHL").district("National Capital Reg").population(99).build()), new ParameterizedTypeReference<APIResponse<City>>() {});
		assertEquals(resp.getStatusCode(), HttpStatus.CREATED);
	}
	@Test
	void addCity() {
		restTemplate = builder.basicAuthentication("admin", "adminpass").build();
		ResponseEntity<APIResponse<City>> resp = restTemplate.exchange("http://localhost:9091/addCity", HttpMethod.POST , new HttpEntity<City>(City.builder().id(0).name("New added City").countryCode("PHL").district("National Capital Reg").population(101).build()) , new ParameterizedTypeReference<APIResponse<City>>() {});
		assertEquals(resp.getStatusCode(), HttpStatus.CREATED);		
	}
	@Test
	void testRepoCity() {
		
	}
}
