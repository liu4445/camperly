package org.project.backend.camping.favorite.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@ToString
@RequiredArgsConstructor
public class FavoriteRequest {
    private long contentId;

}
