package com.kxw.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kingsonwu on 15/11/2.
 */

@RestController
@RequestMapping("/common")
//@Scope("prototype")
public class CommonController {

    private int i = 0;

    @RequestMapping("/sb")
    public String stringBuilder() throws InterruptedException {

        StringBuilder sb = new StringBuilder();
        System.out.println("enter ...");
        if (i == 0) {
            System.out.println("enter ... kxw");
            i++;
            sb.append("kxw");
            //Thread.sleep(10000);
            System.out.println("kxw:" + sb.length());
            System.out.println(i);
            System.out.println(Thread.currentThread().getId());
        } else {
            System.out.println("enter ... mm");
            sb.append("mm");
            System.out.println("mm:" + sb.length());
            System.out.println(i);
            System.out.println(Thread.currentThread().getId());
        }
        System.out.println("exit ...");
        /** 只要是在方法内定义使用StringBuilder,完全不会有线程安全问题.
         * 因为每一个线程都会自己在堆里创建一个对象,这个类本身以后线程安全问题,但是这种写法不会线程安全问题
         * 虽然这个controller是个单例,但是StringBuilder没有方法之外定义,因此不会只有一个 */

        //用jmeter并发访问验证

        return sb.toString();
    }

    @RequestMapping("/kxw")
    public String kxw() {
        return "kxw2";
    }

}