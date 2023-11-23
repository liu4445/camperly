package org.project.backend.camping.place.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.project.backend.camping.favorite.repository.FavoriteRepository;
import org.project.backend.camping.place.dao.CampingPlaceDao;
import org.project.backend.camping.place.dao.CampingPlaceImgDao;
import org.project.backend.camping.place.dto.CampingPlaceDto;
import org.project.backend.camping.place.dto.CampingPlaceImgDto;
import org.project.backend.camping.place.dto.DetailResponse;
import org.project.backend.camping.place.dto.SearchRequest;
import org.project.backend.camping.place.dto.SearchResponse;
import org.project.backend.constant.LocationType;
import org.project.backend.constant.MainFacilities;
import org.project.backend.constant.OperationType;
import org.project.backend.constant.SubFacilities;
import org.project.backend.constant.Theme;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampingPlaceService {

	private final static int pageSize = 20;

	private final CampingPlaceDao campingPlaceDao;
	private final CampingPlaceImgDao campingPlaceImgDao;
	private final FavoriteRepository favoriteRepository;

	@Transactional(readOnly = true)
	public SearchResponse search(SearchRequest searchRequest) {
		List<CampingPlaceDto> campingPlaces = campingPlaceDao.findAll();

		if (!searchRequest.getKeyword().isEmpty()) {
			if (searchRequest.isNameFlag() && searchRequest.isLocationFlag()) {
				campingPlaces = campingPlaces.stream()
						.filter(campingPlace -> campingPlace.getCampsiteName().contains(searchRequest.getKeyword()))
						.filter(campingPlace -> campingPlace.getAddress().contains(searchRequest.getKeyword()))
						.collect(Collectors.toList());
			} else if (!searchRequest.isNameFlag() && !searchRequest.isLocationFlag()) {
				campingPlaces = campingPlaces.stream()
						.filter(campingPlace -> campingPlace.getCampsiteName().contains(searchRequest.getKeyword()) ||
								campingPlace.getCampsiteName().contains(searchRequest.getKeyword())
						).collect(Collectors.toList());
			} else if (searchRequest.isNameFlag()) {
				campingPlaces = campingPlaces.stream()
						.filter(campingPlace -> campingPlace.getCampsiteName().contains(searchRequest.getKeyword()))
						.collect(Collectors.toList());
			} else {
				campingPlaces = campingPlaces.stream()
						.filter(campingPlace -> campingPlace.getAddress().contains(searchRequest.getKeyword()))
						.collect(Collectors.toList());
			}
		}
		if (searchRequest.getOperationType() != null) {
			campingPlaces = filterByOperationType(campingPlaces, searchRequest.getOperationType());
		}
		if (searchRequest.getLocationTypes() != null && !searchRequest.getLocationTypes().isEmpty()) {
			campingPlaces = filterByLocationType(campingPlaces, searchRequest.getLocationTypes());
		}
		if (searchRequest.getMainFacilities() != null && !searchRequest.getMainFacilities().isEmpty()) {
			campingPlaces = filterByMainFacilities(campingPlaces, searchRequest.getMainFacilities());
		}
		if (searchRequest.getThemes() != null && !searchRequest.getThemes().isEmpty()) {
			campingPlaces = filterByTheme(campingPlaces, searchRequest.getThemes());
		}
		if (searchRequest.getSubFacilities() != null && !searchRequest.getSubFacilities().isEmpty()) {
			campingPlaces = filterBySubFacilities(campingPlaces, searchRequest.getSubFacilities());
		}

		campingPlaces.sort(Comparator.comparing((CampingPlaceDto campingPlace) -> favoriteRepository.countByContentId(campingPlace.getContentId())).reversed());
		List<CampingPlaceDto> paging = paging(searchRequest.getPage(), campingPlaces);
		return new SearchResponse(paging);
	}

	private List<CampingPlaceDto> paging(int page, List<CampingPlaceDto> campingPlaces) {
		if (campingPlaces.size() < (page - 1) * pageSize) {
			return Collections.emptyList();
		}
		return campingPlaces.subList((page - 1) * pageSize, Math.min(page * pageSize, campingPlaces.size()));
	}

	private List<CampingPlaceDto> filterByLocationType(
			List<CampingPlaceDto> campingPlaces,
			List<LocationType> locationTypes
	) {
		List<CampingPlaceDto> filteredCampingPlace = new ArrayList<>();
		for (CampingPlaceDto campingPlace : campingPlaces) {
			boolean isFiltered = false;
			for (LocationType locationType : locationTypes) {
				if (!campingPlaceDao.existsByCampingPlaceAndLocationType(campingPlace, locationType)) {
					isFiltered = true;
					break;
				}
			}
			if (!isFiltered) {
				filteredCampingPlace.add(campingPlace);
			}
		}
		return filteredCampingPlace;
	}

	private List<CampingPlaceDto> filterByOperationType(
			List<CampingPlaceDto> campingPlaces,
			OperationType operationType
	) {
		return campingPlaces.stream()
				.filter(campingPlace ->
						campingPlaceDao.existsByCampingPlaceAndOperationType(campingPlace, operationType))
				.collect(Collectors.toList());
	}

	private List<CampingPlaceDto> filterByMainFacilities(
			List<CampingPlaceDto> campingPlaces,
			List<MainFacilities> mainFacilities
	) {
		List<CampingPlaceDto> filteredCampingPlace = new ArrayList<>();
		for (CampingPlaceDto campingPlace : campingPlaces) {
			boolean isFiltered = false;
			for (MainFacilities facilities : mainFacilities) {
				if (!campingPlaceDao.existsByCampingPlaceAndMainFacilities(campingPlace, facilities)) {
					isFiltered = true;
					break;
				}
			}
			if (!isFiltered) {
				filteredCampingPlace.add(campingPlace);
			}
		}
		return filteredCampingPlace;
	}

	private List<CampingPlaceDto> filterByTheme(
			List<CampingPlaceDto> campingPlaces,
			List<Theme> themes
	) {
		List<CampingPlaceDto> filteredCampingPlace = new ArrayList<>();
		for (CampingPlaceDto campingPlace : campingPlaces) {
			boolean isFiltered = false;
			for (Theme theme : themes) {
				if (!campingPlaceDao.existsByCampingPlaceAndTheme(campingPlace, theme)) {
					isFiltered = true;
					break;
				}
			}
			if (!isFiltered) {
				filteredCampingPlace.add(campingPlace);
			}
		}
		return filteredCampingPlace;
	}

	private List<CampingPlaceDto> filterBySubFacilities(
			List<CampingPlaceDto> campingPlaces,
			List<SubFacilities> subFacilities
	) {
		List<CampingPlaceDto> filteredCampingPlace = new ArrayList<>();
		for (CampingPlaceDto campingPlace : campingPlaces) {
			boolean isFiltered = false;
			for (SubFacilities facilities : subFacilities) {
				if (!campingPlaceDao.existsByCampingPlaceAndSubFacilities(campingPlace, facilities)) {
					isFiltered = true;
					break;
				}
			}
			if (!isFiltered) {
				filteredCampingPlace.add(campingPlace);
			}
		}
		return filteredCampingPlace;
	}

	@Transactional(readOnly = true)
	public DetailResponse detail(long contentId) {
		CampingPlaceDto campingPlace = campingPlaceDao.findByContentId(contentId);
		List<CampingPlaceImgDto> imgUrls = campingPlaceImgDao.findByContentId(campingPlace.getContentId());
		campingPlace.setLocationTypes(campingPlaceDao.findLocationTypeByContentId(campingPlace.getContentId()));
		campingPlace.setMainFacilities(campingPlaceDao.findMainFacilitiesByContentId(campingPlace.getContentId()));
		campingPlace.setOperationTypes(campingPlaceDao.findOperationTypeByContentId(campingPlace.getContentId()));
		campingPlace.setSubFacilities(campingPlaceDao.findSubFacilitiesByContentId(campingPlace.getContentId()));
		campingPlace.setThemes(campingPlaceDao.findThemeByContentId(campingPlace.getContentId()));
		return new DetailResponse(campingPlace, imgUrls);
	}
}
