package org.project.backend.camping.favorite.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.project.backend.camping.favorite.domain.Favorite;
import org.project.backend.camping.favorite.domain.FavoriteRequest;
import org.project.backend.camping.favorite.repository.FavoriteRepository;
import org.project.backend.camping.place.dao.CampingPlaceDao;
import org.project.backend.camping.place.dto.CampingPlaceDto;
import org.project.backend.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final CampingPlaceDao campingPlaceDao;
    private final JwtUtil jwtUtil;

    public void insert(long userId, long contentId) {
        favoriteRepository.save(new Favorite(userId, contentId));
    }

    public void delete(FavoriteRequest favoriteRequest, HttpServletRequest httpServletRequest) {
        long contentId = favoriteRequest.getContentId();
        long userId = jwtUtil.getId(jwtUtil.extractToken(httpServletRequest));
        favoriteRepository.deleteByUserIdAndContentId(userId, contentId);
    }

    public Boolean getLike (long contentId, HttpServletRequest httpServletRequest){

        long userId = jwtUtil.getId(jwtUtil.extractToken(httpServletRequest));
           return favoriteRepository.existsByUserIdAndContentId(userId, contentId);
    }

    public int count (long contentId) {
        return favoriteRepository.countByContentId(contentId);
    }

    public List<CampingPlaceDto> getList (HttpServletRequest httpServletRequest) {
        long userId = jwtUtil.getId(jwtUtil.extractToken(httpServletRequest));
        List<Favorite> findFavorites = favoriteRepository.findAllByUserId(userId);
        return findFavorites.stream()
                .map(favorite -> campingPlaceDao.findByContentId(favorite.getContentId()))
                .sorted(Comparator.comparing((CampingPlaceDto campingPlace) -> favoriteRepository.countByContentId(campingPlace.getContentId())).reversed())
                .collect(Collectors.toList());
    }
}
