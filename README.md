# Kafka producer and consumer 
This is a POC for kafka. There are two components one is producer which will push the message to the topic.
The other module is consumer module which will listen the messages pushed by producer.

There is a rest endpoint to push the message to the topic.

**Endpoint:** http://localhost:8444/order/

**Request Body**
```json
{
"id": 1,
"description": "ko",
"amount" : 23.34,
"noOfItems" : 4
}
```
