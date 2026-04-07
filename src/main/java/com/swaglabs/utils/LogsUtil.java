package com.swaglabs.utils;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogsUtil {

    public static final String LOGS_PATH =  "test-outputs/logs" ;

    private LogsUtil() {}

    public static Logger logger() {
        return LoggerFactory.getLogger(
                Thread.currentThread().getStackTrace()[2].getClassName()
        );
    }

    public static void info(String message) {
        logger().info(message);
    }
    public static void info(String message, Object... args) {
        logger().info(message, args);
    }

    public static void warn(String message) {
        logger().warn(message);
    }

    public static void error(String message) {
        logger().error(message);
    }
    public static void error(String message,String s) {
        logger().error(message,s);
    }

    public static void debug(String message) {
        logger().debug(message);
    }

    public static void trace(String message) {
        logger().trace(message);
    }


}