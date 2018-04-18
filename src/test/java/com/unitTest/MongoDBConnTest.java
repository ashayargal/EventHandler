package com.unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import com.main.apple.MongoDBConn;
import com.mongodb.client.MongoCollection;

class MongoDBConnTest {

	
	@Test
	void test() {
		MongoDBConn conn = new MongoDBConn();
		assertFalse(conn.activeConn);
		MongoCollection<Document> collection = conn.getCollection();
		assertTrue(conn.activeConn);
		assertNotNull(collection);
		assertTrue(collection.count() == 0);
	}

}
