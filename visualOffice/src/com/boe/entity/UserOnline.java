package com.boe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="USER_ONLINE")
public class UserOnline implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "generate")
	@GenericGenerator(name = "generate", strategy = "native")   
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 20)
	private String userId;
	
	@Column(length = 50)
	private String userName;
	
	@Column(length = 50)
	private String userIp;
	
	@Column(length = 20)
	private String userStatus;
	
	private Date userLogtime;
	
	private Date userUpdatetime;
	
	@Column(length = 100)
	private String sessionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Date getUserLogtime() {
		return userLogtime;
	}

	public void setUserLogtime(Date userLogtime) {
		this.userLogtime = userLogtime;
	}

	public Date getUserUpdatetime() {
		return userUpdatetime;
	}

	public void setUserUpdatetime(Date userUpdatetime) {
		this.userUpdatetime = userUpdatetime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
