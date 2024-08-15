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
@Table(name = "Shares", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "videoId", "userId" }) 
		})
public class Share {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne @JoinColumn(name = "userId")
	private User user;
	@ManyToOne @JoinColumn(name = "videoId")
	private Video video;
	@Column
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(name = "shareDate")
	private Date shareDate = new Date();
	
	public Share() {
	}
	public Share(Long id, User user, Video video, String email, Date shareDate) {
		super();
		this.id = id;
		this.user = user;
		this.video = video;
		this.email = email;
		this.shareDate = shareDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getShareDate() {
		return shareDate;
	}
	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}
	
	
}
