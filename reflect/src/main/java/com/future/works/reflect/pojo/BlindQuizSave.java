package com.future.works.reflect.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "blind_spot_quiz_save")
public class BlindQuizSave {

	
	private String userId;
	private String selectedList;
	private String uniqueId;
	public String getUserId() {
		return userId;
	}
	
}
