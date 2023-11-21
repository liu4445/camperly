package org.project.backend.weather.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.project.backend.weather.dto.WeatherDto;

@Mapper
public interface WeatherDao {

	List<WeatherDto> findByCity(@Param("cityName") String cityName);

	List<WeatherDto> findByCityLike(@Param("cityName") String cityName);
}
