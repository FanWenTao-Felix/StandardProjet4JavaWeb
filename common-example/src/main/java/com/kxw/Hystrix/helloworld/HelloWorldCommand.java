package com.kxw.Hystrix.helloworld;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * <a href='http://www.infoq.com/cn/news/2013/01/netflix-hystrix-fault-tolerance'>@link</a>
 */
public class HelloWorldCommand extends HystrixCommand {
    public HelloWorldCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
    }

    @Override
    protected String run() {
        return "Hello World";
    }

    @Override
    protected String getFallback() {
        return "Hello Fallback";
    }
}