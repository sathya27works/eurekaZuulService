package com.future.works.reflect.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "reflect_quiz_details")
public class QuizElements {

	@Id
	private String quizId;
	private String quizType;
	private int questionNo;
	private String question;
	private boolean answer;
	private boolean userAnswer;
	private int score;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
}
