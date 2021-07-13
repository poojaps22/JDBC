package com.fis.app.dao;

import java.util.List;

import com.fis.app.exce.NoDeviceFoundException;
import com.fis.app.model.ElectrnoicDevice;

public interface IElectronicDeviceDAO {

	
	public boolean addDevice(ElectrnoicDevice device);
	public List<ElectrnoicDevice> getAllDevices();
	
	public boolean changeDevicePrice(int deviceId,int newPrice)throws NoDeviceFoundException;
	public boolean changeDeviceRating(int deviceId,int newRating)throws NoDeviceFoundException;
	
	public boolean deleteDevice(int deviceId)throws NoDeviceFoundException;
	
	public List<ElectrnoicDevice> getDevicesBasedOnBrandNameAndType(String brandName,String type);
	public int countDeviceType(String type);
	public int getSumofPriceBasedOnType(String type);
	
	public List<ElectrnoicDevice> getDevicesBasedOnPriceRangeAndType(int range1, int range2, String type);
	
}
