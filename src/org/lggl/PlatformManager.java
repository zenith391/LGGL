package org.lggl;

public class PlatformManager {

	private static Platform platform;
	
	public static Platform getPlatform() {
		if (platform == null) {
			initPlatform();
		}
		return platform;
	}
	
	private static void initPlatform() {
		String name = System.getProperty("os.name").toLowerCase();
		if (name.contains("windows")) {
			platform = Platform.WINDOWS;
		}
		
		if (name.contains("linux")) {
			platform = Platform.LINUX;
		}
		
		if (name.contains("osx")) {
			platform = Platform.OSX;
		}
		
		if (name.contains("android")) { // NOT SURE
			platform = Platform.ANDROID;
		}
	}

}