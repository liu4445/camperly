package org.project.backend.camping.favorite.controller;

import lombok.RequiredArgsConstructor;
import org.project.backend.camping.favorite.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/test")
    public ResponseEntity test() {
        favoriteService.test();
        return ResponseEntity.ok().build();
    }
}
