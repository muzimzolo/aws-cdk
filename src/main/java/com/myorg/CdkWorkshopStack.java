package com.myorg;

import software.constructs.Construct;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
//import software.amazon.awscdk.Duration;
//import software.amazon.awscdk.services.sns.Topic;
//import software.amazon.awscdk.services.sns.subscriptions.SqsSubscription;
//import software.amazon.awscdk.services.sqs.Queue;


public class CdkWorkshopStack extends Stack {
    public CdkWorkshopStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkWorkshopStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);
        
        final Function hello = Function.Builder.create(this, "HelloHandler")
        		.runtime(Runtime.NODEJS_20_X)    // execution environment
                .code(Code.fromAsset("lambda"))  // code loaded from the "lambda" directory
                .handler("hello.handler")        // file is "hello", function is "handler"
                .build();
        // The name of the handler function is hello.handler 
        // (“hello” is the name of the file and “handler” is the exported function name
        
        
     // Defines an API Gateway REST API resource backed by our "hello" function
        LambdaRestApi.Builder.create(this, "Endpoint")
        .handler(hello).build();
    }
   
}
