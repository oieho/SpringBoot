package com.maven.springboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maven.springboot.MyDataMongo;

public interface MyDataMongoRepository extends MongoRepository<MyDataMongo, String> {
	public List<MyDataMongo> findByName(String s);
}
