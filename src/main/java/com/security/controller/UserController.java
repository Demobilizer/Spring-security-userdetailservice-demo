package com.security.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	
	@GetMapping("/")
	public String indexPage(/* Authentication auth */) {
		/*
		 * Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		 * if(authorities.contains("ROLE_ADMIN")) { return "admin-view"; } else
		 * if(authorities.contains("ROLE_USER")) { return "user-view"; } else { return
		 * "index"; }
		 */
		return "index";
	}
	
	/*
	 * @GetMapping("/") public ModelAndView indexPage(ModelAndView mv) {
	 * mv.setViewName("index"); return mv; }
	 */
	 
	@GetMapping("/user")
	public String usersPage(Principal principal, Model m) {
		m.addAttribute("name", principal.getName());
		return "user-view";
	}
	
	@GetMapping("/admin")
	public String adminsPage(Principal principal, Model m) {
		m.addAttribute("name", principal.getName());
		return "admin-view";
	}
	
	@GetMapping("/accessDenied")
	public String accessDeniedPage() {
		return "access-denied-view";
	}
	
	@GetMapping("/login")
	public String customLoginPage() {
		return "custom-login-page";
	}
}
