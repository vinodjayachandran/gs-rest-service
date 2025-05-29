package com.example.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Question;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	// List<Question> questions = new ArrayList<>();
	Map<String, Question> questionsById = new HashMap<>();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	/*
	 * Query parameter example
	 * http://localhost:8080/greet?name=John
	 */
	@GetMapping("/greet")
	public String greetUser(@RequestParam(required = false, defaultValue = "Guest") String name) {
    	return "Hello, " + name;
	}

	/*
	 * Path variable example
	 * http://localhost:8080/user/123
	 */
	@GetMapping("/user/{id}")
	public String getUser(@PathVariable int id) {
    	return "User ID: " + id;
	}

	@PutMapping("/question/{id}")
	public String upVote(@PathVariable String id) {
		//TODO: process PUT request
		Question question = questionsById.get(id);
		if (question == null) {
			return "Question not found with ID: " + id;
		}
		else {
			question.upVote();
			questionsById.put(id, question); // Update the question in the map
			return "Question with ID: " + id + " has been upvoted.";
		}
		
	}

	/*
	 * POST request example
	 */
	@PostMapping("/questions")
	public String createUser(@RequestBody Question question) {
		questionsById.put(question.getId(), question);
		return "Question created successfully with ID: " + question.getId();
	}

	@GetMapping("/questions")
	public String getQuestions() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Question> entry : questionsById.entrySet()) {
           
			sb.append(entry.getValue().toString()).append("\n");
        }
		
		return sb.toString();
	}



	@GetMapping("/info")
	public ResponseEntity<String> getInfo(@RequestHeader("User-Agent") String userAgent) {
    	return ResponseEntity.ok("Your agent: " + userAgent);
	}




}
