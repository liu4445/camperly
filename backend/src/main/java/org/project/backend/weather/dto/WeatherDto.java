package org.project.backend.weather.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WeatherDto {

	private String cityName;
	private int month;
	private double avgTemperature;
	private double minTemperature;
	private double maxTemperature;
}
