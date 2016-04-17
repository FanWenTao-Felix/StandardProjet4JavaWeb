package com.kxw.Hystrix.example;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class CallbackCommand extends HystrixCommand<String> {
    private final String name;

    public CallbackCommand(String name) {
        //最少配置:指定命令组名(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑封装在run()方法中
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }

    //调用实例
    public static void main(String[] args) throws Exception {
        //注册观察者事件拦截
        Observable<String> fs = new CallbackCommand("World").observe();
        //注册结果回调事件
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
            }
        });
        //注册完整执行生命周期事件
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError完成之后最后回调
                System.out.println("execute onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                // 当产生异常时回调
                System.out.println("onError " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                // 获取结果后回调
                System.out.println("onNext: " + v);
            }
        });

    }
}
/* 运行结果
call execute result=Hello observe-hystrix thread:hystrix-HelloWorldGroup-3
onNext: Hello observe-hystrix thread:hystrix-HelloWorldGroup-3
execute onCompleted
*/