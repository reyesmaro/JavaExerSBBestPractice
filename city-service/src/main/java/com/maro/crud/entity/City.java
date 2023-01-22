package com.maro.crud.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "city")
public class City {
	@Id
	@Column(name = "ID")
	@Min(value = 0)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "City Id shouldn't be NULL OR EMPTY and more than ZERO")
	private int id;
	
	@Column(name = "Name")	
    @NotBlank(message = "City Name shouldn't be NULL OR EMPTY")
	private String name;
	
	@Column(name = "CountryCode")
    @NotBlank(message = "Country code shouldn't be NULL OR EMPTY")
	private String countryCode;
	
	@Column(name = "District")
    @NotBlank(message = "District code shouldn't be NULL OR EMPTY")
	private String district;
	
	@Column(name = "Population")
	private int population;
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name ="Code" , referencedColumnName = "code")
	private Country country;
}
