package com.future.works.reflect.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.future.works.reflect.pojo.BlindQuizSave;
import com.future.works.reflect.pojo.QuizElements;
import com.future.works.reflect.pojo.QuizResultDetails;
import com.future.works.reflect.pojo.UserQuizDetails;
import com.future.works.reflect.repo.BlindQuizSaveRepository;
import com.future.works.reflect.repo.QuizDetailsRepository;
import com.future.works.reflect.repo.QuizResultDetailsRepository;
import com.future.works.reflect.repo.UserQuizDetailsRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired} )
public class QuizDaoImpl implements QuizDao{

	private final QuizDetailsRepository quizDetailsRepository;
	private final UserQuizDetailsRepository userQuizDetailsRepository;
	private final BlindQuizSaveRepository blindQuizSaveRepository;
	private final QuizResultDetailsRepository quizResultDetailsRepository;
	
	@Override
	public Flux<QuizElements> fetchQuizDetails(String quizType) {
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("quizType", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		QuizElements quizElements = new QuizElements();
		quizElements.setQuizType(quizType);
		Example<QuizElements> example = Example.of(quizElements, customExampleMatcher);
		
		return quizDetailsRepository.findAll(example);
		
	}
	
	@Override
	public void updateQuizEntries(List<UserQuizDetails> userQuizDetails) {
		userQuizDetailsRepository.saveAll(userQuizDetails);
	}
	
	@Override
	public List<QuizResultDetails> fetchResultMessage(String quizType) {
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("quizType", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		QuizResultDetails quizResultDetails = new QuizResultDetails();
		quizResultDetails.setQuizType(quizType);
		Example<QuizResultDetails> example = Example.of(quizResultDetails, customExampleMatcher);
		
		return quizResultDetailsRepository.findAll(example);
	}
	
	@Override
	public void saveBlindQuiz(BlindQuizSave blindQuizSave) {
		blindQuizSaveRepository.save(blindQuizSave);
		
	}
	
}
