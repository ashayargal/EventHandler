package com.main.apple;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;

/**
 * @author ashay
 *
 */
public class MongoDBConn {

	MongoCollection<Document> collection = null;
	final static Logger logger = Logger.getLogger(MongoDBConn.class);

	public void createConnection() {

		AppConfig config = new AppConfig();
		
		try {
			// Creating a Mongo client
			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient(config.getProperty("dbURL"),
					Integer.parseInt(config.getProperty("dbPort")));

			logger.debug("Connected to the database successfully");

			// Accessing the database
			MongoDatabase database = mongo.getDatabase(config.getProperty("database"));

			// Retrieving a collection
			collection = database.getCollection(config.getProperty("collection"));
			collection.deleteMany(new Document());
			logger.debug("Collection Events selected successfully");
		} catch(Exception e) {
			logger.fatal(e);
		}

	}

	/**
	 * @return
	 */
	public MongoCollection<Document> getCollection() {
		if (this.collection == null) {
			createConnection();
		}
		return this.collection;
	}

}
