package com.poly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Favorites", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "videoId", "userId" }) 
		})
public class Favorite {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne @JoinColumn(name = "userId")
	private User user;
	@ManyToOne @JoinColumn(name = "videoId")
	private Video video;
	@Temporal(TemporalType.DATE)
	@Column(name = "likeDate")
	private Date likeDate = new Date();
	
	public Favorite() {
		
	}
	public Favorite(long id, User user, Video video, Date likeDate) {
		super();
		this.id = id;
		this.user = user;
		this.video = video;
		this.likeDate = likeDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User userId) {
		this.user = userId;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video videoId) {
		this.video = videoId;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	
}
