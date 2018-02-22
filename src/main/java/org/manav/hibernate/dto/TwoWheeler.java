package org.manav.hibernate.dto;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("2W")
public class TwoWheeler extends VehicleSuper {

	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
	
	
}
