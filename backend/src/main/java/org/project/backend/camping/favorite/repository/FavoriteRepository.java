package org.project.backend.camping.favorite.repository;

import org.project.backend.camping.favorite.domain.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FavoriteRepository extends MongoRepository<Favorite, String> {

    void deleteByUserIdAndContentId(long userId, long contentId);


    boolean existsByUserIdAndContentId(long userId, long contentId);

    int countByContentId(long contentId);

    List<Favorite> findAllByUserId(long userId);
}
