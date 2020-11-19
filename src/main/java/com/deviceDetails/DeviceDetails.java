package com.deviceDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import com.utility.LoggingUtils;

public class DeviceDetails {

	public static String outputText;
	public static String outputText1;
	public static String DeviceModel;
	static String runs = "";
	static String cmd = "";
	static String cmd2 = "";
	static String AppDetails = "";
	static ArrayList<String> devices = new ArrayList<String>();
	static ArrayList<String> deviceManufacturer = new ArrayList<String>();
	static HashSet<String> hs = new HashSet<String>();
	static ArrayList<String> deviceOS = new ArrayList<String>();
	static HashSet<String> hsOS = new HashSet<String>();
	public static String OEM;

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	public static String getAppDetails(String str) {
		try {
			getListOfDevicesConnected();
			String cmd = "";
			if (AppDetails.isEmpty()) {
				cmd = ("/usr/local/bin/adb -s " + devices.get(0) + " shell \"dumpsys package " + str + " | grep versionName\"");
				AppDetails = str;
			} else if (!AppDetails.isEmpty()) {
				cmd = ("/usr/local/bin/adb -s " + devices.get(1) + " shell \"dumpsys package " + str + " | grep versionName\"");
			}
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((DeviceModel = br.readLine()) != null) {
				logger.info("App Details :: " + DeviceModel.trim());
				break;
			}
		} catch (Exception e) {
		}

		return DeviceModel;
	}
	
	public static String getAppVersion(String packageName) {

		try {
			cmd = ("/usr/local/bin/adb shell \"dumpsys package " + packageName + " | grep versionName\"");
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((DeviceModel = br.readLine()) != null) {
				return DeviceModel.trim();
			}
		} catch (Exception e) {
			
		}
		return "";
	}

	public static void getTheDeviceManufacturer() {
		devices.removeAll(devices);
		deviceManufacturer.removeAll(deviceManufacturer);
		getListOfDevicesConnected();
		try {
			for (int i = 0; i <= getListOfDevicesConnected().size() - 1; i++) {
				String cmd3 = ("/usr/local/bin/adb  -s " + devices.get(i) + " shell getprop ro.product.manufacturer");
				Process process = Runtime.getRuntime().exec(cmd3);
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				deviceManufacturer.add(br.readLine());
				OEM = deviceManufacturer.get(0);
			}
			hs.addAll(deviceManufacturer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTheOSVersion() {
		try {
			String cmd1 = ("/usr/local/bin/adb shell getprop ro.build.version.release");
			Process p1 = Runtime.getRuntime().exec(cmd1);
			BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			while ((outputText1 = br.readLine()) != null) {
				logger.info("Version :: " + outputText1.toString());
				Thread.sleep(3000);
				break;
			}

		} catch (Exception e) {
		}
		return outputText1;
	}

	public static void removePermisson(String packagename) {
		String cmd2 = ("/usr/local/bin/adb shell pm clear " + packagename);
		try {
			Runtime.getRuntime().exec(cmd2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getListOfDevicesConnected() {
		try {
			String cmd2 = ("/usr/local/bin/adb devices");
			Process p1 = Runtime.getRuntime().exec(cmd2);
			BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			String s = "";
			Thread.sleep(1000);
			devices.removeAll(devices);
			while (!(s = br.readLine()).isEmpty()) {
				if (!s.equals("List of devices attached")) {
					devices.add(s.replaceAll("device", "").trim());
				}
			}

			return devices;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return devices;
	}

	public static String deviceNames(String extent) {
		ArrayList<String> a = new ArrayList<String>();
		a.addAll(hs);
		return a.get(0);
	}

	public static String deviceOS(String extent) {
		ArrayList<String> a = new ArrayList<String>();
		a.addAll(hsOS);
			return a.get(0);
	}

	public static void getTheDeviceOSVersion() {

		devices.removeAll(devices);
		deviceOS.removeAll(deviceOS);
		getListOfDevicesConnected();

		try {
			for (int i = 0; i <= getListOfDevicesConnected().size() - 1; i++) {
				String cmd3 = ("/usr/local/bin/adb -s " + devices.get(i) + " shell getprop ro.build.version.release");
				Process process = Runtime.getRuntime().exec(cmd3);
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				deviceOS.add(br.readLine());
			}
			hsOS.addAll(deviceOS);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String DeviceInfo(String testName) {

		String DeviceInfo;
		String getDeviceName = deviceNames(testName);
		String getOSName = deviceOS(testName);
		DeviceInfo = "Device Name - " + getDeviceName + " Version - " + getOSName;
		return DeviceInfo;
	}

}
