package org.project.backend.camping.favorite.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.project.backend.camping.favorite.domain.Favorite;
import org.project.backend.camping.favorite.domain.FavoriteRequest;
import org.project.backend.camping.favorite.service.FavoriteService;
import org.project.backend.util.JwtUtil;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity insert(@RequestBody FavoriteRequest favoriteRequest, HttpServletRequest httpServletRequest) {
        long userId = jwtUtil.getId(jwtUtil.extractToken(httpServletRequest));
        favoriteService.insert(userId, favoriteRequest.getContentId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody FavoriteRequest favoriteRequest, HttpServletRequest httpServletRequest) {

        favoriteService.delete(favoriteRequest, httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{contentId}")
    public ResponseEntity find(@PathVariable("contentId") long contentId, HttpServletRequest httpServletRequest) {


        return ResponseEntity.ok(favoriteService.getLike(contentId,httpServletRequest));
    }

    @GetMapping("/count/{contentId}")
    public ResponseEntity count(@PathVariable("contentId") long contentId) {

        return ResponseEntity.ok(favoriteService.count(contentId));
    }
    @GetMapping("/list")
    public ResponseEntity getList(HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok(favoriteService.getList(httpServletRequest));
    }
}