package com.future.works.reflect.service;

import java.util.List;

import com.future.works.reflect.pojo.QuizElements;

import reactor.core.publisher.Flux;

public interface QuizService {

	Flux<QuizElements> fetchCuriosityDetails(String quizType);

	String validateCuriosityDetails(List<QuizElements> quizElements); 
	
	String saveBlindSpotQuiz(String uniqueId, String selectedList, String userId);

}
