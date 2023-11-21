package org.project.backend.camping.place.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.project.backend.camping.place.dto.CampingPlaceDto;
import org.project.backend.constant.LocationType;
import org.project.backend.constant.MainFacilities;
import org.project.backend.constant.OperationType;
import org.project.backend.constant.SubFacilities;
import org.project.backend.constant.Theme;

@Mapper
public interface CampingPlaceDao {
	List<CampingPlaceDto> findAll();

	CampingPlaceDto findByContentId(@Param("contentId") Long contentId);

	boolean existsByCampingPlaceAndLocationType(
			@Param("campingPlace") CampingPlaceDto campingPlace,
			@Param("locationType") LocationType locationType
	);

	boolean existsByCampingPlaceAndOperationType(
			@Param("campingPlace") CampingPlaceDto campingPlace,
			@Param("operationType") OperationType operationType
	);

	boolean existsByCampingPlaceAndMainFacilities(
			@Param("campingPlace") CampingPlaceDto campingPlace,
			@Param("mainFacilities") MainFacilities mainFacilities
	);

	boolean existsByCampingPlaceAndTheme(
			@Param("campingPlace") CampingPlaceDto campingPlace,
			@Param("theme") Theme theme
	);

	boolean existsByCampingPlaceAndSubFacilities(
			@Param("campingPlace") CampingPlaceDto campingPlace,
			@Param("subFacilities") SubFacilities subFacilities
	);
}
