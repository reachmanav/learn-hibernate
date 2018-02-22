package org.manav.hibernate.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="USER_DETAILS")
public class UserDetails {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	//@OneToOne
	//@JoinColumn(name="VEHICLE_JOIN_ID")
	@OneToMany(cascade=CascadeType.PERSIST)
/*	@JoinTable(name="USER_VEHICLE_MAP",
			   joinColumns= {@JoinColumn(name="USER_ID")},
			   inverseJoinColumns= {@JoinColumn(name="VEHICLE_ID")})*/
	private Collection<Vehicle> vehicle = new ArrayList<>();
	
	
	public Collection<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Collection<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}


	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="HOME_STREET")),
		@AttributeOverride(name="city", column=@Column(name="HOME_CITY")),
		@AttributeOverride(name="state", column=@Column(name="HOME_STATE")),
		@AttributeOverride(name="pincode", column=@Column(name="HOME_PINCODE"))
	})
	private Address homeAddress;
	
	@Embedded
	private Address officeAddress;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@JoinTable(name="COMPANY_ADDRESS",
			   joinColumns= {@JoinColumn(name="USER_ID")}
			)
	@GenericGenerator(name = "sequence-gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name="ADDRESS_SEQ_NUM") }, generator = "sequence-gen", type = @Type(type="long"))
	private Collection<Address> listOfCompanyAddress = new ArrayList<>();
	
	
	


	public Collection<Address> getListOfCompanyAddress() {
		return listOfCompanyAddress;
	}

	public void setListOfCompanyAddress(Collection<Address> listOfCompanyAddress) {
		this.listOfCompanyAddress = listOfCompanyAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}


	@Temporal(TemporalType.DATE)
	private Date joinedDate; // Temporal will only save the date component, not time
	
	
	private String description; // Lob means hibernate will choose whether CLOB/BLOB
	
	@Transient
	private String whatever; // This will not be stored
	

	public String getWhatever() {
		return whatever;
	}
	public void setWhatever(String whatever) {
		this.whatever = whatever;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	@Lob
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public String getUserName() {
		return userName + " from getter";
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
