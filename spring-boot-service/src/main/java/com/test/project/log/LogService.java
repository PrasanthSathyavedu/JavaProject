package com.test.project.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private static final Logger logger = LogManager.getLogger(LogService.class);

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message, Exception ex) {
        logger.error(message, ex);
    }
}
