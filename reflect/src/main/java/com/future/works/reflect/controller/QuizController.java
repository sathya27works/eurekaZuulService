package com.future.works.reflect.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.future.works.reflect.pojo.QuizElements;
import com.future.works.reflect.pojo.UserDetails;
import com.future.works.reflect.service.QuizServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value="/quizManager")
public class QuizController {

	@Autowired
	private QuizServiceImpl quizServiceImpl;
	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public QuizController(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper=dynamoDBMapper;
	}

	@GetMapping("/quiz/{quizType}")
	@ResponseBody
	@HystrixCommand(fallbackMethod = "fetchCuriosityDetailsFallback")
	public Flux<QuizElements> fetchCuriosityDetails(@PathVariable String quizType) {
		return quizServiceImpl.fetchCuriosityDetails(quizType);
	}

	@SuppressWarnings("unused")
	private Flux<QuizElements> fetchCuriosityDetailsFallback(@PathVariable String quizType) {
		return null;
	}
	
	@GetMapping("/userDetails")
	public UserDetails getUserDetails() {
		return dynamoDBMapper.load(UserDetails.class, "1");
	}
	
	@PostMapping("/submitQuiz")
	@ResponseBody
	public String validateQuiz(@RequestBody List<QuizElements> quizElements) {
		return quizServiceImpl.validateCuriosityDetails(quizElements);
	}
	
	@GetMapping("/submitBlindSpot/{uniqueId}/{selectedList}/{userId}")
	public String submitBlindSpot(@PathVariable("uniqueId") String uniqueId, @PathVariable("selectedList") String selectedList, @PathVariable("userId") String userId) {
		return quizServiceImpl.saveBlindSpotQuiz(uniqueId, selectedList, userId);
	}	 
	
	@ExceptionHandler
	public void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());

	}

}
