package org.project.backend.camping.place.controller;

import org.project.backend.camping.place.dto.SearchRequest;
import org.project.backend.camping.place.service.CampingPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/camping/place")
@RequiredArgsConstructor
@Slf4j
public class CampingPlaceController {

	private final CampingPlaceService campingPlaceService;

	@GetMapping
	public ResponseEntity search(SearchRequest searchRequest) {
		log.info("캠핑장 검색 요청: {}", searchRequest);
		return ResponseEntity.ok(campingPlaceService.search(searchRequest));
	}

	@GetMapping("/{contentId}")
	public ResponseEntity detail(@PathVariable("contentId") Long contentId) {
		log.info("캠핑장 상세 조회: {}", contentId);
		return ResponseEntity.ok(campingPlaceService.detail(contentId));
	}
}
