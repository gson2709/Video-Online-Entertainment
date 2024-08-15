package com.poly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "getUsersLikeByVideo",
			procedureName = "sp_getUsersLikeByVideo",
			resultClasses = {LikeByUsers.class},
			parameters = @StoredProcedureParameter(name = "VideoId", type = String.class)
	)
})

@Entity
public class LikeByUsers {
	@Id
	private String id;
	private String fullname;
	private String email;
	private Date likeDate;
	
	public LikeByUsers() {
	}
	public LikeByUsers(String id, String fullname, String email, Date likeDate) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.likeDate = likeDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	
}
