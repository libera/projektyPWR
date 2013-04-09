package com.project.data;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLES")
public class UserRoles {
	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************


	
	@Id
    @Column(name="USER_ROLE_ID")
    @GeneratedValue
    private Integer userRoleID;
    
 	@OneToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="USER_ID", nullable=false)
    private Prowadzacy userID;

    
    @Column(name="AUTHORITY")
    private String authority;


    //*************************************************************************************************
 	//***************************************Getters and Setters***************************************
 	//*************************************************************************************************

	public Integer getUserRoleID() {
		return userRoleID;
	}


	public void setUserRoleID(Integer userRoleID) {
		this.userRoleID = userRoleID;
	}


	public Prowadzacy getUserID() {
		return userID;
	}


	public void setUserID(Prowadzacy userID) {
		this.userID = userID;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}
    

}
