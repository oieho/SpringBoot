package com.maven.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maven.springboot.MsgData;

public interface MsgDataRepository extends JpaRepository<MsgData, Long> {

}
