package org.project.backend.camping.place.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class SearchResponse {

	private final List<CampingPlaceDto> campingPlaceDtos;
}
