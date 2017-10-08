package com.mathi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userrole")
public class UserRole implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 8432414340180447723L;

  @Id
  @GeneratedValue
  private Integer id;
  
  @Column(name="rolename")
  private String roleName;
  
  @Column
  private String email;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}  
}