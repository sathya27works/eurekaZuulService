package com.future.works.reflect.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.future.works.reflect.pojo.UserQuizDetails;
public interface UserQuizDetailsRepository extends ReactiveMongoRepository<UserQuizDetails, String> {

}
