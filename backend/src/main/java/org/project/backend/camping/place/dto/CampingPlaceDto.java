package org.project.backend.camping.place.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CampingPlaceDto {

	private Long contentId;
	private String doName;
	private String sigunguName;
	private int sinkCnt;
	private String brazier;
	private String canCaravan;
	private int toiletCnt;
	private int showerRoomCnt;
	private String feature;
	private String induty;
	private int zipcode;
	private String address;
	private double mapX;
	private double mapY;
	private String tel;
	private String homepage;
	private String reserveUrl;
	private String reserveType;
	private int manageType;
	private String oeprSeason;
	private String operDate;
	private String canTrailer;
	private String intro;
	private int area;
	private String manageStatus;
	private String equipLend;
	private String animal;
	private String firstImageUrl;
	private String campsiteName;
	private String lineIntro;
	private String facltDivName;
	private String locationType;
	private int siteTypeGrass;
	private int siteTypeCrushed;
	private int siteTypeTech;
	private int siteTypePebble;
	private int siteTypeSoil;
	private int generalSiteCnt;
	private int autoSiteCnt;
	private int glampSiteCnt;
	private int caravanSiteCnt;
	private int userCaravanSiteCnt;
	private String etcFacilities;
	private String etcCaravanFacilities;
	private String etcGlampingFacilities;
	private String experience;
}
