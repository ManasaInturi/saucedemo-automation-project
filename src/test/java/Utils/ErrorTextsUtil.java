package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ErrorTextsUtil {

    private static final Properties errorTexts;

    static {
        try {
            errorTexts = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
                    + File.separator + "src" + File.separator + "test" + File.separator + "resources"
                    + File.separator + "errorTexts.properties");
            errorTexts.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load errorTexts.properties file", e);
        }
    }

    public static String getErrorText(String key) {
        return errorTexts.getProperty(key);
    }
}