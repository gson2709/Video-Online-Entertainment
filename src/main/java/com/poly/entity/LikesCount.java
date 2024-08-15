package com.poly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "getFavoriteCount",
			procedureName = "sp_getVideoFavoriteCount",
			resultClasses = {LikesCount.class}
	)
})

@Entity
public class LikesCount implements Serializable{
	@Id
	private String title;
	private Long likes;
	private Date newest;
	private Date oldest;
	
	public LikesCount() {
		
	}
	public LikesCount(String title, Long likes, Date newest, Date oldest) {
		this.title = title;
		this.likes = likes;
		this.newest = newest;
		this.oldest = oldest;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	public Date getNewest() {
		return newest;
	}
	public void setNewest(Date newest) {
		this.newest = newest;
	}
	public Date getOldest() {
		return oldest;
	}
	public void setOldest(Date oldest) {
		this.oldest = oldest;
	}
	
	
}
