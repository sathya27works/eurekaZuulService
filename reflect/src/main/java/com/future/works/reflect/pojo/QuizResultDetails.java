package com.future.works.reflect.pojo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "reflect_quiz_result_details")
public class QuizResultDetails {

	@Id
	private String quizResultId;
	private String quizType;
	private List<Integer> score;
	private String resultMessage;
}
