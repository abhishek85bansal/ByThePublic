package com.photohack.bythepeople;


public class IncidentData {

	private double latitude;
	private double longitude;
  // local path.
	private String videoFile;
  // remote url.
  private String videoUri;
	private String note;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getVideoFile() {
		return videoFile;
	}
	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
  public void setVideoUri(String videoUri) {
    this.videoUri = videoUri;
  }
  public String getVideoUri() {
    return videoUri;
  }
}
