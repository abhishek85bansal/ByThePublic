package com.tracker.crime.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.tracker.crime.dao.IncidentDAO;
import com.tracker.crime.domain.Incident;
import com.tracker.crime.domain.Location;
import com.tracker.crime.dto.IncidentReport;

@Component
public class IncidentManager {
	
	public SessionFactory sessionFactory;
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}





	public IncidentDAO incidentDao;
	
	@Autowired
	public void setIncidentDao(IncidentDAO incidentDao) {
		this.incidentDao = incidentDao;
	}
	
	
	public void addUpdateIncident(Incident inc){
		incidentDao.addUpdate(inc);
	}
	


	public List<IncidentReport> getTopTenIncidents() {
		
		List<Incident> incidentEntityList = incidentDao.getTopTenIncidents();
		List<IncidentReport> response = new ArrayList<IncidentReport>();
		
		for (Incident incident: incidentEntityList) {
			IncidentReport dto = new IncidentReport();
			dto.setDescription(incident.getDescription());
			dto.setIncidentId(incident.getIncidentId());
			dto.setLatitude(incident.getLocation().getLattitude());
			dto.setLongitude(incident.getLocation().getLongitude());
			dto.setReportedDate(incident.getReportingDate());
			dto.setSpamCount(incident.getSpamCount());
			dto.setUrl(incident.getUrl());
			
			response.add(dto);
			
		}
		
		return response;
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Incident incident = new Incident();
		incident.setActive(true);
		incident.setDescription("Sample Incident");
		Location loc = new Location();
		loc.setLattitude("12345");
		loc.setLongitude("23456");
		incident.setLocation(loc);
		incident.setReportingDate(new Date());
		incident.setSpamCount(0);
		incident.setUrl(null);
		
		
//		
//		SessionFactory factory = new Configuration().configure().buildSessionFactory();
//		Session session = factory.openSession();
//		session.beginTransaction();
//		
//		session.save(incident);
//		
//		session.getTransaction().commit();
//		session.close();
		
		 ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		 IncidentManager  manager = (IncidentManager ) appContext.getBean("incidentManager");
	     manager.addUpdateIncident(incident);
		
		

	}

}
