package org.project.backend.weather.dto;

import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class WeatherResponse {

	private final Map<Integer, WeatherDto> weather;
}
