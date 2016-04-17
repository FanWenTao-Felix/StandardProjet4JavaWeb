package com.kxw.Hystrix.github;

import com.kxw.Hystrix.github.helloworld.CommandHelloWorld;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * Created by kingson.wu on 2016/3/8.
 */
public class TestExecuton {

    @Test
    public void testSynchronous() {
        assertEquals("Hello World!", new CommandHelloWorld("World").execute());
        assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
    }

    @Test
    public void testAsynchronous1() throws Exception {
        assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
        assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
    }

    @Test
    public void testAsynchronous2() throws Exception {

        Future<String> fWorld = new CommandHelloWorld("World").queue();
        Future<String> fBob = new CommandHelloWorld("Bob").queue();

        assertEquals("Hello World!", fWorld.get());
        assertEquals("Hello Bob!", fBob.get());
    }
}
