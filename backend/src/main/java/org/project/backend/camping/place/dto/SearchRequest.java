package org.project.backend.camping.place.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.project.backend.constant.LocationType;
import org.project.backend.constant.MainFacilities;
import org.project.backend.constant.OperationType;
import org.project.backend.constant.SubFacilities;
import org.project.backend.constant.Theme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class SearchRequest {
	private final List<LocationType> locationTypes;
	private final OperationType operationType;
	private final List<MainFacilities> mainFacilities;
	private final List<Theme> themes;
	private final List<SubFacilities> subFacilities;

	@NotNull
	private final boolean isLike;

	@NotNull
	private final int page;
}
