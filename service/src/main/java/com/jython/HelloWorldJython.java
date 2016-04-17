package com.jython;

import org.python.util.PythonInterpreter;

import java.net.URL;
import java.util.Properties;

/**
 * Created by kingsonwu on 15/11/17.
 * {<a href='http://bugs.jython.org/issue2355'>@link</a>}
 */
public class HelloWorldJython {

    public static void main(String[] args) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL defaultPropertyURL = cl.getResource("./jython/hello.py");

        System.out.println(defaultPropertyURL.getPath());
        Properties props = new Properties();
        props.put("python.home", "/Users/kingsonwu/.m2/repository/org/python/jython/2.7.0");
        //props.put("python.home","/WEB-INF/lib");
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
        props.put("python.import.site", "false");

        Properties preprops = System.getProperties();

        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interp = new PythonInterpreter();

        interp.execfile(defaultPropertyURL.getPath());
    }
}
