package org.manav.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.manav.hibernate.dto.FourWheeler;
import org.manav.hibernate.dto.TwoWheeler;
import org.manav.hibernate.dto.VehicleSuper;

public class VehicleDemo {

	public static void main(String[] args) {


		VehicleSuper vehicleSuper = new VehicleSuper("Car");
		
		TwoWheeler bike = new TwoWheeler();
		bike.setVehicleName("Motorbike");
		bike.setSteeringHandle("Bike-Handle");

		FourWheeler truck = new FourWheeler();
		truck.setSteeringWheel("Car-Steering-Wheel");
		truck.setVehicleName("Truck");
		
		
		SessionFactory buildSessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = buildSessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicleSuper);
		session.save(bike);
		session.save(truck);
		session.getTransaction().commit();
		
		session.close();		
		buildSessionFactory.close();
		
	}
	

}
