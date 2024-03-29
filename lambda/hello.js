/*
 * This is a simple Lambda function which returns the text “Hello, CDK! You’ve hit [url path]”. 
 * The function’s output also includes the HTTP status code and HTTP headers. 
 * These are used by API Gateway to formulate the HTTP response to the user
 */

exports.handler = async function(event) {
  console.log("request:", JSON.stringify(event, undefined, 2));
  return {
    statusCode: 200,
    headers: { "Content-Type": "text/plain" },
    body: `Hello, CDK! You've hit ${event.path}\n`
  };
};
