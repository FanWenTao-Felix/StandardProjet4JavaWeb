package com.kxw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kingson.wu on 2015/9/25.
 */
public class TestLogger {

    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);

    public void test(){
        if(logger.isDebugEnabled()){
            logger.debug("test");
        }
    }

}
