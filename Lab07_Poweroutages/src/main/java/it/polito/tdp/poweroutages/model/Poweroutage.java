package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class Poweroutage {

	private int id;
	private int nerc;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private int customersAffected;
	

	
	
	public Poweroutage(int id, int nerc, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished,
			int customersAffected) {
		
		this.id = id;
		this.nerc = nerc;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.customersAffected = customersAffected;
	}
	public int getId() {
		return id;
	}
	public int getNerc() {
		return nerc;
	}
	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}
	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}
	public int getCustomersAffected() {
		return customersAffected;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poweroutage other = (Poweroutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Poweroutage [id=" + id + ", nerc=" + nerc + ", dateEventBegan=" + dateEventBegan
				+ ", dateEventFinished=" + dateEventFinished + ", customersAffected=" + customersAffected + "]";
	}
	
	
	
	
	
	
}
