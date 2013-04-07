package com.tracker.crime.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tracker.crime.domain.Incident;

@Component
public class IncidentDAO {
	
public SessionFactory sessionFactory;
	
	
    private static final String QUERY_GET_TOP_10_INCIDENTS = "select * from incident order by reportingDate LIMIT 0, 10 ";

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addUpdate(Incident incident) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(incident);
		session.getTransaction().commit();
		session.close();
	}
	
	public Incident get(int incidentId){
		return null;
	}
	
	public List<Incident> getTopTenIncidents() {
		List<Incident> result = new ArrayList<Incident>();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		result = session.createSQLQuery(QUERY_GET_TOP_10_INCIDENTS).addEntity(Incident.class).list();
		
		session.getTransaction().commit();
		session.close();
		} catch(Exception E) {
			E.printStackTrace();
		}
		
		return result;
	}
	
	
}
