package org.manav.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.manav.hibernate.dto.Address;
import org.manav.hibernate.dto.UserDetails;
import org.manav.hibernate.dto.Vehicle;

public class HibernateTest {

	public static void main (String[] args)  {
		
		UserDetails userDetails = new UserDetails ();
		Vehicle vehicle1 = new Vehicle("Car");
		vehicle1.setUserOfVehicle(userDetails);
		Vehicle vehicle2 = new Vehicle("Truck");
		vehicle2.setUserOfVehicle(userDetails);
		
		//Commented because GeneratedValue will auto-assign
		//userDetails.setUserId(1);
		
		userDetails.setUserName("Manav");
		userDetails.setHomeAddress(new Address("Frankford Rd","Dallas","Texas","75252"));
		userDetails.setOfficeAddress(new Address("Campbell Rd","Dallas","Richardson","75081"));

		userDetails.getListOfCompanyAddress().add(new Address("Frankford Rd","Dallas","Texas","75252"));
		userDetails.getListOfCompanyAddress().add(new Address("Campbell Rd","Dallas","Richardson","75081"));
		
		userDetails.setDescription("My description");
		userDetails.setJoinedDate(new Date());
		
		userDetails.getVehicle().add(vehicle1);
		userDetails.getVehicle().add(vehicle2);
		
		SessionFactory buildSessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = buildSessionFactory.openSession();
		session.beginTransaction();
		//session.save(userDetails);
		//session.save(vehicle1);	
		//session.save(vehicle2);	
		
		session.persist(userDetails);
		
		session.getTransaction().commit();
		session.close();
		
		userDetails = null;
		
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		userDetails =  (UserDetails) session.get(UserDetails.class, 1);
		//System.out.println(userDetails.getVehicle().getVehicleName());
		session.close();
		
		
		/*// Reading the Vehicle object
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		//Vehicle vehicle = new Vehicle();
		Vehicle vehicle3 = session.get(Vehicle.class, 2);
		
		System.out.println("For Vehicle with ID " + vehicle3.getVehicleId() + 
				" and name as " + vehicle3.getVehicleName() + " is " +
								 vehicle3.getUserOfVehicle());*/
		
		buildSessionFactory.close();
		
	}

}
