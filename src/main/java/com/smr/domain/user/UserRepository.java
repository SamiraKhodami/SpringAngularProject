package com.smr.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);
	List<User> findByFirstNameStartingWith(String firstName);
	List<User> findByUserNameAndFirstNameStartingWith(String userName,String firstName);
}