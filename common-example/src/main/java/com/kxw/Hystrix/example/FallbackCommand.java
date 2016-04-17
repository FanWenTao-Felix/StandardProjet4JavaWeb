package com.kxw.Hystrix.example;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

//重载HystrixCommand 的getFallback方法实现逻辑
public class FallbackCommand extends HystrixCommand<String> {
    private final String name;

    public FallbackCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置依赖超时时间,500毫秒*/
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return "exeucute Falled";
    }

    @Override
    protected String run() throws Exception {
        //sleep 1 秒,调用会超时
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception {
        FallbackCommand command = new FallbackCommand("test-Fallback");
        String result = command.execute();
    }
}
/* 运行结果:getFallback() 调用运行
getFallback executed
*/