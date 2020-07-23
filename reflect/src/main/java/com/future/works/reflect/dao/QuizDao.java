package com.future.works.reflect.dao;

import java.util.List;

import com.future.works.reflect.pojo.BlindQuizSave;
import com.future.works.reflect.pojo.QuizElements;
import com.future.works.reflect.pojo.QuizResultDetails;
import com.future.works.reflect.pojo.UserQuizDetails;

import reactor.core.publisher.Flux;

public interface QuizDao {

	Flux<QuizElements> fetchQuizDetails(String quizType); 
	
	void updateQuizEntries(List<UserQuizDetails> userQuizDetails);

	List<QuizResultDetails> fetchResultMessage(String quizType);
	void saveBlindQuiz(BlindQuizSave blindQuizSave);

}
