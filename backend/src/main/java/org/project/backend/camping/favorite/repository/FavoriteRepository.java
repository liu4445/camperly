package org.project.backend.camping.favorite.repository;

import org.project.backend.camping.favorite.domain.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FavoriteRepository extends MongoRepository<Favorite, String> {

    List<Favorite> findByName(String name);
}
