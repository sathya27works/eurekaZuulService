package com.future.works.reflect.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.future.works.reflect.pojo.BlindQuizSave;
public interface BlindQuizSaveRepository extends ReactiveMongoRepository<BlindQuizSave, String> {

}
