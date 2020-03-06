package com.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.security.model.UserBhai;

public interface UserRepository extends JpaRepository<UserBhai, Integer>{

	//UserBhai loadUserByUsername(String username);

	UserBhai findByUserName(String userName);

}
