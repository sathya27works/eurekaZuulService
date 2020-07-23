package com.future.works.reflect.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.future.works.reflect.pojo.QuizResultDetails;
public interface QuizResultDetailsRepository extends MongoRepository<QuizResultDetails, String> {

}
