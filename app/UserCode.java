package com.fis.app;

import java.util.List;

import com.fis.app.dao.ElectronicDeviceDAOImpl;
import com.fis.app.dao.IElectronicDeviceDAO;
import com.fis.app.model.ElectrnoicDevice;

public class UserCode {

	public static void main(String[] args) {
		
		try {
			
			IElectronicDeviceDAO dao = new ElectronicDeviceDAOImpl();
			
			
			/*
			 * ElectrnoicDevice mockED = new ElectrnoicDevice("Laptop", 101, "Dell", 26000,
			 * 230, 8, "Grey"); boolean a = dao.addDevice(mockED); System.out.println(a);
			 */
			
			/*
			 * List<ElectrnoicDevice> list = dao.getAllDevices();
			 * list.stream().forEach((dev)->System.out.println(dev));
			 */
			
			/*
			 * boolean a = dao.changeDevicePrice(101,30000); System.out.println(a);
			 */
			
			/*
			 * boolean a = dao.changeDeviceRating(101,9); System.out.println(a);
			 */
			
			
			/*
			 * boolean a = dao.deleteDevice(101); System.out.println(a);
			 */
			
			
			/*
			 * List<ElectrnoicDevice> list =
			 * dao.getDevicesBasedOnBrandNameAndType("Dell","Laptop");
			 * list.stream().forEach((dev)->System.out.println(dev));
			 */
			
			
			/*
			 * int a = dao.countDeviceType("Laptop"); System.out.println(a);
			 */
			
			/*
			 * int a = dao.getSumofPriceBasedOnType("Laptop"); System.out.println();
			 */
			
			List<ElectrnoicDevice> list =dao.getDevicesBasedOnPriceRangeAndType(30000,36000,"Laptop");
			list.stream().forEach((dev)->System.out.println(dev));
			
			
			
		} catch (Exception e) {
			System.out.println("Problem "+e);
		}
		
	}
	
}
