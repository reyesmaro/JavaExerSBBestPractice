package com.maro.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "country")
public class Country {
	@Id
	@Column(name = "Code")
	private String code;
	@Column(name = "Name")
	private String name;
	@Column(name = "Continent")
	private String continent;
	@Column(name = "Region")
	private String region;
	@Column(name = "SurfaceArea")
	private Double surfaceArea;
	@Column(name = "IndepYear")
	private Integer indepYear;
	@Column(name = "Population")
	private Integer population;
	@Column(name = "LifeExpectancy")
	private Double lifeExpectancy;
	@Column(name = "GNP")
	private Double gnp;
	@Column(name = "GNPOld")
	private Double gnpOld;
	@Column(name = "LocalName")
	private String localName;
	@Column(name = "GovernmentForm")
	private String governmentForm;
	@Column(name = "HeadOfState")
	private String headOfState;
	@Column(name = "Capital")
	private String capital;
	@Column(name = "Code2")
	private String code2;
//	@OneToMany(cascade = CascadeType.ALL , targetEntity=City.class, mappedBy="country")
//	private List<City> city;
}
