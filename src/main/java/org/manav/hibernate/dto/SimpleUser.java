package org.manav.hibernate.dto;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="SIMPLE_USER")
@NamedQueries(value = { @NamedQuery(name = "SimpleUser.findById", query = "from SimpleUser where userId=:queryParamUserId"),
						@NamedQuery(name = "SimpleUser.findByName", query = "from SimpleUser where userName=:queryParamUserId")
					  })
//@NamedNativeQuery(name = "byName", query = "select * from SIMPLE_USER where username=:nativeUserName", resultClass=SimpleUser.class)
public class SimpleUser {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId; 
	private String userName;
	
	
	
	public SimpleUser(String userName) {
		super();
		this.userName = userName;
	}

	public SimpleUser() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
