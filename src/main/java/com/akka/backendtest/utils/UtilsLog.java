package com.akka.backendtest.utils;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.akka.backendtest.utils.Constants.*;


public class UtilsLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsLog.class);
    public static void customLog(String level, String message){

        switch (level){
            case LEVEL_ERROR:
                if(LOGGER.isErrorEnabled()){
                    LOGGER.error(message);
                }
                break;
            case LEVEL_WARN:
                if(LOGGER.isWarnEnabled()){
                    LOGGER.warn(message);
                }
                break;
            case LEVEL_INFO:
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info(message);
                }
                break;
            case LEVEL_DEBUG:
                if(LOGGER.isDebugEnabled()){
                    LOGGER.debug(message);
                }
                break;
        }

    }
}
