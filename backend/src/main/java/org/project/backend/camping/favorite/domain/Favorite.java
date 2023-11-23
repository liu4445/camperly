package org.project.backend.camping.favorite.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favorite")
@Getter
@AllArgsConstructor
public class Favorite {
    @Id
    private String id;
    private String name;
    private Integer age;
}
