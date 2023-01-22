package com.maro.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maro.crud.entity.City;
@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	City findByName(String name);
	List<City> findAllByCountryCode(String countryCode);
	@Query(value="select city.ID,  city.Code,  city.CountryCode,  city.District,  city.Name,  "
			+ "city.Population,  country.Code,  country.Capital,  country.Code2,  country.Continent,  "
			+ "country.GNP,  country.GNPOld,  country.GovernmentForm,  country.HeadOfState,  "
			+ "country.IndepYear,  country.LifeExpectancy,  country.LocalName,  country.Name,  "
			+ "country.Population,  country.Region,  country.SurfaceArea  "
			+ "from city city  left outer join country country  on city.CountryCode=country.Code "
			+ "where city.ID = :cityId "
			, nativeQuery=true)
	Optional<City> getCityAndCountry(@Param("cityId") int id);
	@Query(value="select city.ID,  city.CountryCode,  city.District,  city.Name,  "
			+ "city.Population,  country.Code,  country.Capital,  country.Code2,  country.Continent,  "
			+ "country.GNP,  country.GNPOld,  country.GovernmentForm,  country.HeadOfState,  "
			+ "country.IndepYear,  country.LifeExpectancy,  country.LocalName,  country.Name,  "
			+ "country.Population,  country.Region,  country.SurfaceArea  "
			+ "from city city  left outer join country country  on city.CountryCode=country.Code "
			+ "where city.ID = :cityId "
			, nativeQuery=true)
	Optional<City> getCityWithCountry(@Param("cityId") int id);
}
