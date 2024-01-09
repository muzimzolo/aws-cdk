/* This is an AWS Lambda function written in Node.js that serves as an API Gateway proxy. 
   It's designed to count hits to a particular path, update a DynamoDB table with the hit count, 
   invoke another Lambda function (referred to as the "downstream" function) with the incoming event, 
   and then return the response from the downstream function. */

// Import AWS SDK Modules (DynamoDB & Lambda)
const {DynamoDB, Lambda} = require('aws-sdk');

/* This line exports an asynchronous function named handler as the entry point for the Lambda function. 
  The function takes an event parameter, which represents the input to the Lambda function. */
exports.handler = async function(event) {
	console.log("request:" , JSON.stringify)

/* Create AWS SDK clients
   These lines create instances of the DynamoDB and Lambda clients using the AWS SDK. 
   These clients will be used to interact with DynamoDB and invoke the downstream Lambda function */
	const dynamo = new DynamoDB();
	const lambda = new Lambda();

/* Update dynamo entry for "path" with hits++
   This block updates an entry in the DynamoDB table specified by the environment variable HITS_TABLE_NAME. 
   It increments the hits attribute for the specified path (taken from the event.path property). */
	await dynamo.updateItem({
		TableName: process.env.HITS_TABLE_NAME,
		Key: {path: {S: event.path}},
		UpdateExpression: 'Add hits: incr',
		ExpressionAttributeValues: {':incr': {N: '1'}}
	}).promise();

	// call downstream function and capture response
	const resp = await lambda.invoke({
		FunctionName: process.env.DOWNSTREAM_FUNCTION_NAME,
		Payload: JSON.stringify(event)
	}).promise();

	console.log('downstream response:', JSON.stringify(resp, undefined, 2));

	// return response back to upstream caller
	return JSON.parse(resp, Payload);
	
};