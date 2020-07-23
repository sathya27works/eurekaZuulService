package com.future.works.reflect.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "user_quiz_details")
public class UserQuizDetails {

	@Id
	private String userQuizId;
	private int questionNo;
	private String question;
	private boolean selectedAnswer;
	private int userScore;
	private String userName;
	
}
