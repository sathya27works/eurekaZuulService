package com.future.works.reflect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.future.works.reflect.dao.QuizDaoImpl;
import com.future.works.reflect.pojo.BlindQuizSave;
import com.future.works.reflect.pojo.QuizElements;
import com.future.works.reflect.pojo.QuizResultDetails;
import com.future.works.reflect.pojo.UserQuizDetails;

import reactor.core.publisher.Flux;

@Component
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizDaoImpl quizDaoImpl;

	@Override
	public Flux<QuizElements> fetchCuriosityDetails(String quizType) {
		return quizDaoImpl.fetchQuizDetails(quizType);
	}

	@Override
	public String validateCuriosityDetails(List<QuizElements> quizElements) {
	  /** logic to arrive score for each entries and save to user quiz details table */
		List<UserQuizDetails> userQuizDetails = new ArrayList<>();
		Integer sumScore = 0;
		String quizType = "";
		for(QuizElements quizElement: quizElements) {
			UserQuizDetails userQuizDetail = new UserQuizDetails();
			userQuizDetail.setQuestion(quizElement.getQuestion());
			userQuizDetail.setSelectedAnswer(quizElement.isAnswer());
			Integer userScore = 0;
			if(quizElement.isAnswer()==quizElement.isUserAnswer()) {
				userScore = quizElement.getScore();
				sumScore += userScore;
			}
			userQuizDetail.setUserScore(userScore);
			userQuizDetails.add(userQuizDetail);
			quizType = quizElement.getQuizType();
		}
		final Integer sumScoreFinal = sumScore;
		quizDaoImpl.updateQuizEntries(userQuizDetails);
		List<QuizResultDetails> fluxQuizResultDetails = quizDaoImpl.fetchResultMessage(quizType);
		StringBuilder resultDisplayMessage = new StringBuilder();
		resultDisplayMessage.append("\"");
		fluxQuizResultDetails.stream().filter(resultMessage ->resultMessage.getScore().contains(sumScoreFinal)).forEach(resultMessage-> 
            		 resultDisplayMessage.append(resultMessage.getResultMessage())
		);
		
		resultDisplayMessage.append("\"");
		return resultDisplayMessage.toString();
	}
	
	@Override
	public String saveBlindSpotQuiz(String uniqueId, String selectedList, String userId) {
		BlindQuizSave save = new BlindQuizSave();
		save.setSelectedList(selectedList);
		save.setUniqueId(uniqueId);
		save.setUserId(userId);
		quizDaoImpl.saveBlindQuiz(save);
		return "SUCCESS";
	}
	
}
