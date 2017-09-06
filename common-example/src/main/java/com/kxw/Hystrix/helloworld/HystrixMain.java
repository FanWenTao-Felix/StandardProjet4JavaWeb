package com.kxw.Hystrix.helloworld;

import java.util.concurrent.ExecutionException;

/**
 * Created by kingson.wu on 2016/3/8.
 */
public class HystrixMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Hystrix命令可以用execute()方法同步（synchronously）执行。
        //String s = (String) new HelloWorldCommand().execute();

        //Hystrix命令也可以用queue()方法异步（asynchronously）执行。
        java.util.concurrent.Future future = new HelloWorldCommand().queue();
        String s = (String) future.get();
        System.out.println(s);
    }
}
