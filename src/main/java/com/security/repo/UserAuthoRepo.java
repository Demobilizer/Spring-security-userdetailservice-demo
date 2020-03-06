package com.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.model.Authorities;
import com.security.model.UserBhai;
import com.security.model.UserBhaiAuthorities;

public interface UserAuthoRepo extends JpaRepository<UserBhaiAuthorities, Integer> {

	List<UserBhaiAuthorities> findAllAuthoritiesByUserBhai(UserBhai userBhai);

}
