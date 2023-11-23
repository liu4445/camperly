package org.project.backend.camping.favorite.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.project.backend.camping.favorite.domain.Favorite;
import org.project.backend.camping.favorite.repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Transactional
    public void test() {

    }

}
