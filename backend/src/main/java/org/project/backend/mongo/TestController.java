package org.project.backend.mongo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {

	private final TestRepository testRepository;

	@GetMapping("/test")
	public ResponseEntity test() {
		Test test = testRepository.findById("1")
				.orElseThrow(() -> new IllegalArgumentException());
		return ResponseEntity.ok(test);
	}
}
