#Event handler
Stores random events in MongoDB and if the number of events > 10 in a 5 minute rolling window, logs it into an external file (also console) and does not store events for 2 minutes.

## Ashay Argal
(917)497-5413
the.ashay.argal@gmail.com

### extra dependencies in pom.xml
1. org.mongodb
2. log4j

### features
All variables are stored in application.properties file and thus can be modified easily.
Logs into external file in the program directory.

### approach
I used timestamps(ms) to compare two events and also to check if the handler is storing events or not.
For every event I check the previous 10th event, 
if its timestamp is closer to current timestamp than the specified time window, the handler becomes inactive for 2 mins and logs the error.

one other approach could be to maintain a queue in the server and check with the previous 10th event.
This would make the program easier and faster but might make it prone to failure.

### testing
testing the storing of events can be turned on/off by changing the "testTimeout" propert in Application.properties
```
testTimeout = false
```