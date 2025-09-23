package com.automationExercises.utils.logs;

import com.automationExercises.utils.Report.allureConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class logsManager {
    public static final String LOGS_PATH = allureConstants.USER_DIR + "/test-output/Logs";


        private static Logger logger ()
        {
            return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
        }

        public static void info(String... message) // info("error message",e.getMessage());
        {
            logger().info(String.join(" ", message));
        }
        public static void error(String... message)
        {
            logger().error(String.join(" ", message));
        }
        public static void warn(String... message)
        {
            logger().warn(String.join(" ", message));
        }
        public static void fatal(String... message)
        {
            logger().fatal(String.join(" ", message));
        }
        public static void debug(String... message)
        {
            logger().debug(String.join(" ", message));
        }


}
