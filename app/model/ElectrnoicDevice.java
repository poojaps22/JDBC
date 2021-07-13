package com.fis.app.model;

import java.util.Objects;

public class ElectrnoicDevice implements Comparable<ElectrnoicDevice> 
{
	
	private String deviceType;
	private int deviceId;
	private String brandName;
	private int cost;
	private int power;
	private int starRatings;
	private String color;
	
	public ElectrnoicDevice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ElectrnoicDevice(String deviceType, int deviceId, String brandName, int cost, int power, int starRatings,
			String color) {
		super();
		this.deviceType = deviceType;
		this.deviceId = deviceId;
		this.brandName = brandName;
		this.cost = cost;
		this.power = power;
		this.starRatings = starRatings;
		this.color = color;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getStarRatings() {
		return starRatings;
	}

	public void setStarRatings(int starRatings) {
		this.starRatings = starRatings;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(brandName, color, cost, deviceId, deviceType, power, starRatings);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectrnoicDevice other = (ElectrnoicDevice) obj;
		return Objects.equals(brandName, other.brandName) && Objects.equals(color, other.color) && cost == other.cost
				&& deviceId == other.deviceId && Objects.equals(deviceType, other.deviceType) && power == other.power
				&& starRatings == other.starRatings;
	}

	@Override
	public int compareTo(ElectrnoicDevice o) {
		
		return this.getDeviceId() - o.getDeviceId();
	}

	@Override
	public String toString() {
		System.out.println("=> DeviceId - DeviceType - Cost - StarRatings - BrandName - Power - Color \n");
		return "=>    "+deviceId+"    - "+deviceType+"    - "+cost+" -    "+starRatings+"    -    "+brandName+"     -  "+power+"   -  "+color+"\n";
	}

}
