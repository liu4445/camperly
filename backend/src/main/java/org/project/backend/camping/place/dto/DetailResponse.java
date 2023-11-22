package org.project.backend.camping.place.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class DetailResponse {

	private final CampingPlaceDto campingPlaceDto;
	private final List<CampingPlaceImgDto> imageUrls;
}
