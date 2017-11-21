package com.lechenggu.bkeasygo.login.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class AdminBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7794226264978995083L;
	@Id
	@GeneratedValue(generator="")
	@SequenceGenerator(name = "",sequenceName="")
	private int id ;
	
	@Column(name="",unique=false,nullable=false,length=50)
	private String name;
	
	@Column(name="",unique=false,nullable=false,length=50)
	private String password;
	
	@Column(name="",unique=false,nullable=false,length=50)
	private String realname;
	
	private String jzmm;   
	
	public String getJzmm() {
		return jzmm;
	}
	public void setJzmm(String jzmm) {
		this.jzmm = jzmm;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
