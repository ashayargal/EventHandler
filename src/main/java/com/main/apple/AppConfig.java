package com.main.apple;

import java.util.Properties;

/**
 * @author ashay
 *
 */
public class AppConfig {

	Properties configFile;

	public AppConfig() {
		configFile = new java.util.Properties();
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("Application.properties"));
		} catch (Exception eta) {
			eta.printStackTrace();
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}

}
