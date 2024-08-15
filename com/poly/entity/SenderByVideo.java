package com.poly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "getSenderByVideo",
			procedureName = "sp_getSenderByVideo",
			resultClasses = {SenderByVideo.class},
			parameters = @StoredProcedureParameter(name = "VideoId", type = String.class)
	)
})

@Entity
public class SenderByVideo {
	@Id
	private String senderName;
	private String senderEmail;
	private String receiverEmail;
	private Date sentDate;
	
	public SenderByVideo() {
	}
	public SenderByVideo(String senderName, String senderEmail, String receiverEmail, Date sentDate) {
		super();
		this.senderName = senderName;
		this.senderEmail = senderEmail;
		this.receiverEmail = receiverEmail;
		this.sentDate = sentDate;
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	
}
