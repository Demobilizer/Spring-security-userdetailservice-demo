package com.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.model.Authorities;
import com.security.model.UserBhai;
import com.security.model.UserBhaiAuthorities;
import com.security.repo.AuthoritiesRepo;
import com.security.repo.UserAuthoRepo;
import com.security.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAuthoRepo userAuthoRepo;
	
	@Autowired
	private AuthoritiesRepo authoritiesRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("coming to custom service class with userName....."+userName);
		UserBhai userBhai = userRepository.findByUserName(userName);
		
		List<GrantedAuthority> grantAuthoritiesList = getAuthoritiesByUserName(userName);
		
		User user = new User(userBhai.getUserName(),
				userBhai.getPassword(),
				(Collection<? extends GrantedAuthority>) grantAuthoritiesList);
		return user;
	}

	private List<GrantedAuthority> getAuthoritiesByUserName(String username) {
		List<String> authoritiesList = new ArrayList<>();
		
		// get userid by username
		
		UserBhai userBhai = userRepository.findByUserName(username);
		
		// get authority id associated with that user
		
		List<UserBhaiAuthorities> authoritiesList1 = userAuthoRepo.findAllAuthoritiesByUserBhai(userBhai);
		
		// get autho names and put them into the list
		
		List<Authorities> authoList = new ArrayList<>();
		
		for (UserBhaiAuthorities authorities : authoritiesList1) {
			authoList.add(authoritiesRepo.findById(authorities.getAuthorities().getAuthorityId()).orElse(null));
		}
		
			for (Authorities authority : authoList) {
				authoritiesList.add(authority.getAuthorityName());
			}
		
			 List<GrantedAuthority> authorities = new ArrayList<>();
		        for (String authority : authoritiesList) {
		            authorities.add(new SimpleGrantedAuthority(authority));
		        }
			
		
		return authorities;
	}

}
