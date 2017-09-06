package com.kxw.Hystrix.github;


import com.kxw.Hystrix.github.helloworld.CommandHelloWorld;

/**
 * Created by kingson.wu on 2016/3/8.
 */
public class ExecutionMain {
    public static void main(String[] args) {
        String s = new CommandHelloWorld("World").execute();
        System.out.println(s);

        //s = new com.kxw.Hystrix.github.observable.CommandHelloWorld("World").
    }
}
