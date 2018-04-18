package com.main.apple;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * @author ashay
 *
 */
public class Event {

	boolean active;
	MongoDBConn conn;
	int eventNumber;
	AppConfig config = new AppConfig();
	int maxEvents = Integer.parseInt(config.getProperty("maxEvents"));
	long timeWindow = Long.parseLong(config.getProperty("timeWindow"));
	long timeout = Long.parseLong(config.getProperty("timeout"));
	final static Logger logger = Logger.getLogger(Event.class);
	long startTimeout;

	public Event() {
		// TODO Auto-generated constructor stub
		this.active = true;
		this.conn = new MongoDBConn();
		this.eventNumber = 0;
	}

	public void registerEvent(int eventValue) {
		MongoCollection<Document> collection = conn.getCollection();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (!this.active) {
			if (timestamp.getTime() - this.startTimeout <= this.timeout) {
				logger.info("Event handler in timeout state");
				return;
			} else {
				this.active = true;
			}
		}

		if (this.eventNumber >= this.maxEvents) {
			try {
				Document prevEvent = collection.find(Filters.eq("eventNumber", (this.eventNumber + 1) - this.maxEvents))
						.first();
				Long prev = prevEvent.getLong("timeStamp");
				Long diff = timestamp.getTime() - prev;
				if (diff > this.timeWindow) {
					Document document = new Document("eventNumber", ++this.eventNumber)
							.append("eventValue", eventValue)
							.append("timeStamp", timestamp.getTime());
					collection.insertOne(document);
					logger.debug("Event stored successfully");
				} else {
					logger.error("Max event limit reached.");
					sleepHandler();
				}
			} catch(Exception e) {
				logger.fatal(e);
			}
			
		} else {
			Document document = new Document("eventNumber", ++this.eventNumber).append("eventValue", eventValue)
					.append("timeStamp", timestamp.getTime());
			collection.insertOne(document);

			logger.debug("Document inserted successfully");
		}

	}

	/**
	 * Handle timeout
	 */
	private void sleepHandler() {
		// TODO Auto-generated method stub
		logger.error("Not storing events for next " + this.timeout + " ms.");
		this.active = false;
		Timestamp timeoutTimestamp = new Timestamp(System.currentTimeMillis());
		this.startTimeout = timeoutTimestamp.getTime();

	}
	
	/**
	 * @return
	 */
	public boolean getHandlerStatus() {
		return this.active;
	}
}
