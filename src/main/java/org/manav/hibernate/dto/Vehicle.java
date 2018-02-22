package org.manav.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="USER_VEHICLE")
public class Vehicle {

	@Column(name="VEHICLE_ID")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int vehicleId;
	
	public String vehicleName;
	
	@ManyToOne	
	@JoinColumn(name="USER_OF_VEHICLE")
	@NotFound(action=NotFoundAction.IGNORE)
	private UserDetails userOfVehicle; 
	
	public Vehicle() {
		
	}
	
	
	
	public Vehicle(String vehicleName) {
		this.vehicleName = vehicleName;
	}



	
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}



	public UserDetails getUserOfVehicle() {
		return userOfVehicle;
	}



	public void setUserOfVehicle(UserDetails userOfVehicle) {
		this.userOfVehicle = userOfVehicle;
	}
	
	
	
}
