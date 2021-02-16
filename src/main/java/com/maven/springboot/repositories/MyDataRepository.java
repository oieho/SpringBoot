package com.maven.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maven.springboot.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long>{
	public MyData findById(long name);
	public List<MyData> findByNameLike(String name);
	public List<MyData> findByIdIsNotNullOrderByIdDesc();
	public List<MyData> findByAgeGreaterThan(Integer age);
	public List<MyData> findByAgeBetween(Integer age1, Integer age2);
}
