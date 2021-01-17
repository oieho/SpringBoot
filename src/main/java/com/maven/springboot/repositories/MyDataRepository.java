package com.maven.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maven.springboot.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long>{

}
