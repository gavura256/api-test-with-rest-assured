package org.gavura.log;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@UtilityClass
public class Log {
    private static Logger loggerInstance;

    private static Logger getLogger() {
        if (Objects.isNull(loggerInstance)) {
            loggerInstance = LoggerFactory.getLogger("Test logger");
        }
        return loggerInstance;
    }

    public static void printLog(String message) {
        getLogger().info(message);
    }
}

