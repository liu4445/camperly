package org.project.backend.camping.favorite.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favorite")
@Getter

public class Favorite {
    @Id
    private String id;
    private long userId;
    private long contentId;

    public Favorite(long userId, long contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }
}
