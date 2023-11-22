package org.project.backend.camping.place.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.project.backend.camping.place.dto.CampingPlaceImgDto;

@Mapper
public interface CampingPlaceImgDao {
	List<CampingPlaceImgDto> findByContentId(@Param("contentId") long contentId);
}
