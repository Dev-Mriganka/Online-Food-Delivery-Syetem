package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.CurrentUserSession;


public interface SessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	
	public  CurrentUserSession  findByUuid(String uuid);
}
