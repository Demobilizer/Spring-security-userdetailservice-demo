package com.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Authorities {

	@Id
	@GeneratedValue
	private int authorityId;
	private String authorityName;
	
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	@Override
	public String toString() {
		return "Authorities [authorityId=" + authorityId + ", authorityName=" + authorityName + "]";
	}
	
	
}
