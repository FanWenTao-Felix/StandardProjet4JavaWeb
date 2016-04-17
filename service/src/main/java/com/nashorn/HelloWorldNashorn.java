package com.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

/**
 * Created by kingsonwu on 15/11/17.
 */
public class HelloWorldNashorn {

    public static void main(String[] args) throws FileNotFoundException, ScriptException {

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL defaultPropertyURL = cl.getResource("./js/hello.js");

        System.out.println(defaultPropertyURL.getPath());

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        FileReader fr = new FileReader(new File(defaultPropertyURL.getPath()));
        engine.eval(fr);

    }
}
