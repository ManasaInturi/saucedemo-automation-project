package Utils;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Plugin(name = "LogCaptureAppender", category = "Core", elementType = Appender.ELEMENT_TYPE)
public class LogCaptureAppender extends AbstractAppender {

    private static final ThreadLocal<List<String>> threadLocalLogs = ThreadLocal.withInitial(ArrayList::new);

    protected LogCaptureAppender(String name, Layout<? extends Serializable> layout) {
        super(name, null, layout, false);
    }

    @Override
    public void append(LogEvent event) {
        threadLocalLogs.get().add(event.getMessage().getFormattedMessage());
    }

    public static List<String> getLogMessages() {
        return new ArrayList<>(threadLocalLogs.get());
    }

    public static void clearLogs() {
        threadLocalLogs.get().clear();
    }

    @PluginFactory
    public static LogCaptureAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout) {
        if (name == null) {
            LOGGER.error("No name provided for LogCaptureAppender");
            return null;
        }
        return new LogCaptureAppender(name, layout);
    }
}