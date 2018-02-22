package org.manav.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.manav.hibernate.dto.SimpleUser;

public class UserCrudDemo {

	public static void main(String[] args) {

		
		SessionFactory buildSessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = buildSessionFactory.openSession();
		
		session.beginTransaction();
		
		for (int i=0;i<10;i++)  {
			SimpleUser user = new SimpleUser("User " +i);
			session.save(user);
		}
		
		session.getTransaction().commit();		
		SimpleUser user = session.get(SimpleUser.class, 10);
		System.out.println("The userName fetched is: "+user.getUserName());
		
		session.close();
		
				

		System.out.println("----------------------------------------------");
		System.out.println("Deleting User with ID 10");
		System.out.println("----------------------------------------------");
		
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
		
		//Get User with ID 9 and update using get()
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		SimpleUser usertmp = session.get(SimpleUser.class,9);
		System.out.println("User ID 9 is:"+usertmp.getUserName());
		
		usertmp.setUserName("Updated User 8");
		session.getTransaction().commit();
		session.close();		
		
		//Using HQL
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		Query createQuery = session.createQuery("from SimpleUser");
		createQuery.setFirstResult(5); //Start from 5th result
		createQuery.setMaxResults(4); // Show only 4 results
		List<SimpleUser> userObjList = (List<SimpleUser>) createQuery.list();	
		//Using Lambda below
		
		userObjList.stream().forEach(u->System.out.println(u.getUserName()));
		System.out.println("The userlist size is:" +userObjList.size());		
		session.getTransaction().commit();
		session.close();
		
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		Query createQuery2 = session.createQuery("select userName from SimpleUser");
		List<String> userNameList = (List<String>) createQuery2.list();	
		//Using Lambda below
		userNameList.stream().forEach(u->System.out.println(u.toString()));	
		session.getTransaction().commit();
		session.close();
		
		// Using Named queries
		
		System.out.println("----------------------------------------------");
		System.out.println("Using named query");
		System.out.println("----------------------------------------------");
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		Query createQuery3 = session.getNamedQuery("SimpleUser.findById");
		createQuery3.setParameter("queryParamUserId", 5);
		List<SimpleUser> userNameList2 = (List<SimpleUser>) createQuery3.list();	
		//Using Lambda below
		userNameList2.stream().forEach(u->System.out.println(u.getUserName()));	
		session.getTransaction().commit();
		session.close();
		
		
		/*
		System.out.println("----------------------------------------------");
		System.out.println("Using named Native query");
		System.out.println("----------------------------------------------");
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		Query createQuery4 = session.getNamedQuery("byName");
		createQuery4.setParameter("nativeUserName", "User 2");
		List<SimpleUser> userNameList3 = (List<SimpleUser>) createQuery4.list();	
		//Using Lambda below
		userNameList3.stream().forEach(u->System.out.println(u.getUserName()));	
		session.getTransaction().commit();
		session.close();
		*/
		
		System.out.println("----------------------------------------------");
		System.out.println("Using CRITERIA QUERY query");
		System.out.println("----------------------------------------------");
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		Root<SimpleUser> root = criteriaQuery.from(SimpleUser.class);
		//criteriaQuery.select(criteriaBuilder.max(root.get("userId")));
		criteriaQuery.multiselect(root.get("userId"), builder.count(root.get("userId")));
		criteriaQuery.groupBy(root.get("userId"));
		criteriaQuery.orderBy(builder.asc(root.get("userId")));
		//criteriaQuery.where(criteriaBuilder.greaterThan(root.get("userId"), 5));
		//criteriaQuery.where(criteriaBuilder.greaterThan(root.get("userId"), 7));
		
		//criteriaQuery.select(criteriaBuilder.max(arg0))
								  
		
		Query<Object[]> q = session.createQuery(criteriaQuery);
		
		
		List<Object[]> userList = (List<Object[]>) q.getResultList();
		//Using Lambda below
		//userList.stream().forEach(u->System.out.println(u.intValue()));	
		
		for (Object[] obj: userList)   {
            int uId = (int) obj[0];
			long userCount = (long) obj[1];
			System.out.println("[uID="+uId + ";userCount="+userCount+"]");
		}
		
		session.getTransaction().commit();
		session.close();
	
		
		
		

		System.out.println("----------------------------------------------");
		System.out.println("Learning Hibernate Cache");
		System.out.println("----------------------------------------------");
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		SimpleUser u1 = session.get(SimpleUser.class, 7);
		u1.setUserName("Updated " + u1.getUserName());		
		session.getTransaction().commit();
		session.close();
		
		//Fetch the same object again
		session = buildSessionFactory.openSession();
		session.beginTransaction();
		SimpleUser u2  = session.get(SimpleUser.class, 7);
		session.getTransaction().commit();
		session.close();
		
		
		buildSessionFactory.close();

	}

}
