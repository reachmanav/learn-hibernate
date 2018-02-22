package org.manav.hibernate.dto;

import javax.persistence.Entity;

@Entity
public class FourWheeler extends VehicleSuper {

	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}


	
	
}
