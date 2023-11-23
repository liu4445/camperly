package org.project.backend.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Document(collation = "test")
@AllArgsConstructor
public class Test {
	@Id
	private String id;
	private String name;
	private int age;
}
