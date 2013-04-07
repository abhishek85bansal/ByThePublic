package com.tracker.crime.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {

	public String longitude;
	public String lattitude;
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	
	
}
