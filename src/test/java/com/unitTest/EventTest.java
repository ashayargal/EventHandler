package com.unitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.main.apple.AppConfig;
import com.main.apple.Event;

class EventTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws NumberFormatException, InterruptedException {
		AppConfig config = new AppConfig();
		Event event = new Event();
		// handler must be active
		assertTrue(event.getHandlerStatus());
		
		// storing allowed number of events
		for(int i =0; i<Integer.parseInt(config.getProperty("maxEvents")); i++) {
			event.registerEvent((int) (Math.random()*10));
		}
		assertTrue(event.getHandlerStatus());
		
		// storing one event more than the allowed number
		event.registerEvent((int) (Math.random()*10));
		// handler should be inactive
		assertFalse(event.getHandlerStatus());
		if(Boolean.parseBoolean(config.getProperty("testTimeout"))) {
			TimeUnit.MILLISECONDS.sleep(Long.parseLong(config.getProperty("timeWindow")));
			event.registerEvent((int) (Math.random()*10));
			assertTrue(event.getHandlerStatus());
		}
	}

}
