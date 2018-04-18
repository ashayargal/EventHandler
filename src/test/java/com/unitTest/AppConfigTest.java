package com.unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.main.apple.AppConfig;

class AppConfigTest {

	@Test
	void testGetProperty() {
		AppConfig config = new AppConfig();
		assertNotNull(config);
		assertNull(config.getProperty("abcdefghijkl"), "No such property");
	}

}
