package com.future.works.reflect.pojo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "reflect_user_details")
@DynamoDBTable(tableName = "reflect_user_details")
public class UserDetails {
	
	//@Id
	@DynamoDBHashKey(attributeName = "id")
	private String id;
	@DynamoDBAttribute(attributeName = "userName")
	private String userName;
	@DynamoDBAttribute(attributeName = "password")
	private String password;
	@DynamoDBAttribute(attributeName = "role")
	private String role;
	
}
