package com.fis.app.dao;

import java.nio.file.attribute.AclEntry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fis.app.exce.NoDeviceFoundException;
import com.fis.app.model.ElectrnoicDevice;

public class ElectronicDeviceDAOImpl implements IElectronicDeviceDAO {

	Connection con = null;

	String insertDeviceQuery = "insert into fisapp.electronicdevice values(?,?,?,?,?,?,?)";
	String selectAllDevices = "select * from fisapp.electronicdevice";
	String NewPrice = "update fisapp.electronicdevice set cost = ? where deviceid = ?";
	String NewRating = "update fisapp.electronicdevice set starratings = ? where deviceid = ?";
	String selectdeviceBasedOnBrandNameandType = "select * from fisapp.electronicdevice where brandname = ? and devicetype = ?";
	String deletedeviceBasedOnID = "delete from fisapp.electronicdevice where deviceid = ?";
	String countDevice = "select devicetype, count(devicetype) as Count from fisapp.electronicdevice group by devicetype";
	String getSumofPriceBasedOnType = "select devicetype, sum(cost) as TotalPrice from fisapp.electronicdevice group by devicetype";

	@Override
	public boolean addDevice(ElectrnoicDevice device) {
		con = DatabaseUtil.getConnection();
		boolean isInserted = false;
		if (con != null) {

			String deviceType = device.getDeviceType();
			int deviceId = device.getDeviceId();
			String brandName = device.getBrandName();
			int cost= device.getCost();
			int power= device.getPower();
			int starRatings= device.getStarRatings();
			String color= device.getColor();
			
			try {
				
				PreparedStatement ps = con.prepareStatement(insertDeviceQuery);
				
				ps.setString(1, deviceType);
				ps.setInt(2, deviceId);
				ps.setString(3, brandName);
				ps.setInt(4, cost);
				ps.setInt(5, power);
				ps.setInt(6, starRatings);
				ps.setString(7, color);
				
				int i = ps.executeUpdate(); 

				if (i > 0)
					isInserted = true;

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return isInserted;
	}

	@Override
	public List<ElectrnoicDevice> getAllDevices() {
		
		con = DatabaseUtil.getConnection();
		List<ElectrnoicDevice> deviceList = new ArrayList<>();
		if (con != null) {

			try 
			{
				
				PreparedStatement ps = con.prepareStatement(selectAllDevices);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					ElectrnoicDevice temp = new ElectrnoicDevice();

					temp.setDeviceType(rs.getString(1));
					temp.setDeviceId(rs.getInt(2));
					temp.setBrandName(rs.getString(3));
					temp.setCost(rs.getInt(4));
					temp.setPower(rs.getInt(5));
					temp.setStarRatings(rs.getInt(6));
					temp.setColor(rs.getString(7));

					deviceList.add(temp);
				}
				
			}
			catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return deviceList;
	}

	@Override
	public boolean changeDevicePrice(int deviceId, int newPrice) throws NoDeviceFoundException {
		
		con = DatabaseUtil.getConnection();
		boolean isUpdated = false;
		if (con != null) {

			try {
				
				PreparedStatement ps = con.prepareStatement(NewPrice);
				
				ps.setInt(2, deviceId);
				ps.setInt(1, newPrice);
				
				int i = ps.executeUpdate();

				if (i > 0)
					isUpdated = true;

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return isUpdated;
	}

	@Override
	public boolean changeDeviceRating(int deviceId, int newRating) throws NoDeviceFoundException {
		
		con = DatabaseUtil.getConnection();
		boolean isUpdated = false;
		if (con != null) {

			try {
				
				PreparedStatement ps = con.prepareStatement(NewRating);
				
				ps.setInt(2, deviceId);
				ps.setInt(1, newRating);
				
				int i = ps.executeUpdate();

				if (i > 0)
					isUpdated = true;

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return isUpdated;
	}

	@Override
	public boolean deleteDevice(int deviceId) throws NoDeviceFoundException {
		con = DatabaseUtil.getConnection();
		if (con != null) {
			
			try {
				
				PreparedStatement ps = con.prepareStatement(deletedeviceBasedOnID);
				ps.setInt(2, deviceId);
				
				int i = ps.executeUpdate(); 
				
				if (i!=0)
				{
					System.out.println("Deleted Device Successfully !!!!!");
					return true;
				}
				else
					throw new NoDeviceFoundException(deviceId);
				
			} catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return false;
}


	@Override
	public List<ElectrnoicDevice> getDevicesBasedOnBrandNameAndType(String brandName, String type) {
		con = DatabaseUtil.getConnection();
		List<ElectrnoicDevice> deviceList = new ArrayList<>();
		if (con != null) {

			PreparedStatement ps;
			try {
				
				ps = con.prepareStatement(selectdeviceBasedOnBrandNameandType);
				
				ps.setString(1, brandName);
				ps.setString(2, type);

				ResultSet rs = ps.executeQuery();
				boolean isFound = false;
				while (rs.next()) {
					isFound = true;
					ElectrnoicDevice temp = new ElectrnoicDevice();

					temp.setDeviceType(rs.getString(1));
					temp.setDeviceId(rs.getInt(2));
					temp.setBrandName(rs.getString(3));
					temp.setCost(rs.getInt(4));
					temp.setPower(rs.getInt(5));
					temp.setStarRatings(rs.getInt(6));
					temp.setColor(rs.getString(7));

					deviceList.add(temp);
				}

				if (!isFound)
					System.out.println("No device Found !!!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return deviceList;
	}

	@Override
	public int countDeviceType(String type) {
		
		//String countDevice = "select devicetype, count(devicetype) as Count from fisapp.electronicdevice group by devicetype";
		
		con = DatabaseUtil.getConnection();
		int count = 0;
		
		if(con!= null)
		{
			try {
				
				PreparedStatement ps = con.prepareStatement(countDevice);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					
					count=rs.getInt("Count");
					String dtype = rs.getString("devicetype");
					System.out.println(dtype+" - "+count);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return count;
	}

	@Override
	public int getSumofPriceBasedOnType(String type) {
		//String getSumofPriceBasedOnType = "select devicetype, sum(cost) as TotalPrice from fisapp.electronicdevice group by devicetype";
		con = DatabaseUtil.getConnection();
		int sum = 0;
		if (con != null) {

			PreparedStatement ps;
			try {
				
				ps = con.prepareStatement(getSumofPriceBasedOnType);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					
					sum=rs.getInt("TotalPrice");
					String dtype = rs.getString("devicetype");
					System.out.println(dtype+" - "+sum);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sum;
	}

	@Override
	public List<ElectrnoicDevice> getDevicesBasedOnPriceRangeAndType(int range1, int range2, String type) {
		
		List<ElectrnoicDevice> ls =new ArrayList<>();
		ls = ls.stream().filter(ed1->{
			return ed1.getCost()>=range1 && ed1.getCost()<=range2 && ed1.getDeviceType().equals(type);
		}).collect(Collectors.toList());
		return ls;
	}

}
