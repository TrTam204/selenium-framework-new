package framework.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader instance;
    private static final Properties props = new Properties();

    private ConfigReader() {
        try {
            props.load(new FileInputStream("src/test/resources/config-dev.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) instance = new ConfigReader();
        return instance;
    }

    public int getRetryCount() {
        return Integer.parseInt(props.getProperty("retry.count", "1"));
    }
}