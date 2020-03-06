package com.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserBhaiAuthorities {

	@Id
	@GeneratedValue
	private int uaId;
	
	@ManyToOne(targetEntity = UserBhai.class)
	@JoinColumn(name = "userId")
	private UserBhai userBhai;
	
	@ManyToOne(targetEntity = Authorities.class)
	@JoinColumn(name = "authorityId")
	private Authorities authorities;

	public int getUaId() {
		return uaId;
	}

	public void setUaId(int uaId) {
		this.uaId = uaId;
	}

	public UserBhai getUserBhai() {
		return userBhai;
	}

	public void setUserBhai(UserBhai userBhai) {
		this.userBhai = userBhai;
	}

	public Authorities getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Authorities authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserBhaiAuthorities [uaId=" + uaId + ", userBhai=" + userBhai + ", authorities=" + authorities + "]";
	}
	
}
