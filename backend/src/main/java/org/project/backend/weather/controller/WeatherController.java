package org.project.backend.weather.controller;

import org.project.backend.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

	private final WeatherService weatherService;

	@GetMapping
	public ResponseEntity getWeather(@RequestParam Long contentId) {
		log.info("날씨 검색 요청: {}", contentId);
		return ResponseEntity.ok(weatherService.getWeather(contentId));
	}
}
