package org.project.backend.weather.service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.project.backend.camping.place.dao.CampingPlaceDao;
import org.project.backend.camping.place.dto.CampingPlaceDto;
import org.project.backend.weather.dao.WeatherDao;
import org.project.backend.weather.dto.WeatherDto;
import org.project.backend.weather.dto.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherService {

	private final WeatherDao weatherDao;
	private final CampingPlaceDao campingPlaceDao;

	@Transactional(readOnly = true)
	public WeatherResponse getWeather(Long contentId) {
		CampingPlaceDto campingPlace = campingPlaceDao.findByContentId(contentId);

		// 서울시를 포함한 광역시
		if (Pattern.matches(".*시$", campingPlace.getDoName())) {
			String doName = campingPlace.getDoName().substring(0, campingPlace.getDoName().length() - 1);

			// 정확히 일치하는 도시
			List<WeatherDto> findWeather = weatherDao.findByCity(doName);
			// 정확히 일치하는 도시가 없다면 비슷한 지역
			if (findWeather == null || findWeather.isEmpty()) {
				findWeather = weatherDao.findByCityLike("%" + doName + "%");
			}

			// 없다면 비슷한 지역으로
			return new WeatherResponse(
					findWeather.stream()
							.collect(Collectors.toMap(WeatherDto::getMonth, weatherDto -> weatherDto))
			);
		} else {
			String sigunguName = campingPlace.getSigunguName().substring(0, campingPlace.getSigunguName().length() - 1);

			// 정확히 일치하는 도시
			List<WeatherDto> findWeather = weatherDao.findByCity(sigunguName);
			// 정확히 일치하는 도시가 없다면 비슷한 지역
			if (findWeather == null || findWeather.isEmpty()) {
				findWeather = weatherDao.findByCityLike("%" + sigunguName + "%");
			}

			// 없다면 비슷한 지역으로
			return new WeatherResponse(
					findWeather.stream()
							.collect(Collectors.toMap(WeatherDto::getMonth, weatherDto -> weatherDto))
			);
		}
	}
}
