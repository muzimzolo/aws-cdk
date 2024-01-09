package com.myorg;

import software.amazon.awscdk.services.lambda.IFunction;
/*
 * This construct can be attached to any Lambda function that’s used as an API Gateway backend, 
 * and it will count how many requests were issued to each URL path. It will store this in a DynamoDB table
 */
public interface HitCounterProps {
    // Public constructor for the props builder
    public static Builder builder() {
        return new Builder();
    }

    // The function for which we want to count url hits
    IFunction getDownstream();

    // The builder for the props interface
    public static class Builder {
        private IFunction downstream;

        public Builder downstream(final IFunction function) {
            this.downstream = function;
            return this;
        }

        public HitCounterProps build() {
            if(this.downstream == null) {
                throw new NullPointerException("The downstream property is required!");
            }

            return new HitCounterProps() {
                @Override
                public IFunction getDownstream() {
                    return downstream;
                }
            };
        }
    }
}